package com.timrobot.vaccapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Izvestaj;
import com.timrobot.vaccapp.models.Obrazac;
import com.timrobot.vaccapp.services.SaglasnostService;
import com.timrobot.vaccapp.utility.PdfUtil;
import com.timrobot.vaccapp.utility.XHtmlUtil;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api/saglasnost")
public class SaglasnostController {
    @Autowired
    private SaglasnostService saglasnostService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Obrazac> getAll() {
        return saglasnostService.getAll();
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Obrazac> getAllForUser(@PathVariable String id) {
        return saglasnostService.getAllForUser(id);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> putSaglanost(@RequestBody Obrazac obrazac) {
        try {
            return ResponseEntity.ok(saglasnostService.putSaglasnost(obrazac));
        }
        catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }


    }

    @PostMapping(value = "/imunizuj", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> imunizujGradjanina(@RequestBody Obrazac obrazac) {
        try {
            return ResponseEntity.ok(saglasnostService.imunizujGradjanina(obrazac));
        }
        catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/metadata-json/{id}")
    public ResponseEntity<?> getJsonMetadataForDocument(@PathVariable String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = saglasnostService.getAllMetadataForDocumentForJSON(id);
            byte[] buf = mapper.writeValueAsBytes(map);

            return ResponseEntity
                    .ok()
                    .contentLength(buf.length)
                    .contentType(
                            MediaType.parseMediaType("application/octet-stream"))
                    .header("Content-Disposition", "attachment; filename=\"metadata.json\"")
                    .body(new InputStreamResource(new ByteArrayInputStream(buf)));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/metadata-rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getRDFMetadataForDocument(@PathVariable String id) {
        try {
            String rdf = saglasnostService.getAllMetadataForDocumentInRDF(id);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"metadata.rdf\"")
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(rdf);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Autowired
    private XMLMapper xmlMapper;

    @GetMapping(value = "/{id}/show", produces = MediaType.APPLICATION_XML_VALUE)
    public String getSaglasnostXHTML(@PathVariable String id) {
        Obrazac saglasnost = saglasnostService.getXmlAsObject(id);
        String xml = xmlMapper.convertToXml(saglasnost, Obrazac.class);
        ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Obrazac.class);
        int n = xhtml.available();
        byte[] bytes = new byte[n];
        xhtml.read(bytes, 0, n);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @GetMapping(value = "/{id}/xhtml", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadSaglasnostXHTML(@PathVariable String id, HttpServletResponse response) {
        try {
            Obrazac saglasnost = saglasnostService.getXmlAsObject(id);
            String xml = xmlMapper.convertToXml(saglasnost, Obrazac.class);
            ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Obrazac.class);
            org.apache.commons.io.IOUtils.copy(xhtml, response.getOutputStream());
            response.setContentType("application/html");
            response.setHeader("Content-Disposition", "attachment; filename=\"saglasnost.html\"");
            response.flushBuffer();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadSaglasnostPDF(@PathVariable String id, HttpServletResponse response) {
        try {
            Obrazac saglasnost = saglasnostService.getXmlAsObject(id);
            String xml = xmlMapper.convertToXml(saglasnost, Obrazac.class);
            ByteArrayInputStream pdf = (new PdfUtil()).generatePDF(xml, Obrazac.class);
            org.apache.commons.io.IOUtils.copy(pdf, response.getOutputStream());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"saglasnost.pdf\"");
            response.flushBuffer();
        } catch (IOException | SAXException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
