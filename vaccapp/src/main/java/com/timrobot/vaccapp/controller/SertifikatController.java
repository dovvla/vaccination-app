package com.timrobot.vaccapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timrobot.vaccapp.models.Obrazac;
import com.timrobot.vaccapp.models.Sertifikat;
import com.timrobot.vaccapp.services.PotvrdaOVakcinacijiService;
import com.timrobot.vaccapp.services.SertifikatService;
import com.timrobot.vaccapp.utility.PdfUtil;
import com.timrobot.vaccapp.utility.XHtmlUtil;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api/sertifikat")
public class SertifikatController {

    @Autowired
    private SertifikatService sertifikatService;

    @GetMapping("/metadata-json/{id}")
    public ResponseEntity<?> getJsonMetadataForDocument(@PathVariable String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = sertifikatService.getAllMetadataForDocumentForJSON(id);
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
            String rdf = sertifikatService.getAllMetadataForDocumentInRDF(id);
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
    public String getSertifikatXHTML(@PathVariable String id) {
        Sertifikat sertifikat = sertifikatService.getXmlAsObject(id);
        String xml = xmlMapper.convertToXml(sertifikat, Sertifikat.class);
        ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Sertifikat.class);
        int n = xhtml.available();
        byte[] bytes = new byte[n];
        xhtml.read(bytes, 0, n);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @GetMapping(value = "/{id}/xhtml", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadSertifikatXHTML(@PathVariable String id, HttpServletResponse response) {
        try {
            Sertifikat sertifikat = sertifikatService.getXmlAsObject(id);
            String xml = xmlMapper.convertToXml(sertifikat, Sertifikat.class);
            ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Sertifikat.class);
            org.apache.commons.io.IOUtils.copy(xhtml, response.getOutputStream());
            response.setContentType("application/html");
            response.setHeader("Content-Disposition", "attachment; filename=\"sertifikat.html\"");
            response.flushBuffer();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadSertifikatPDF(@PathVariable String id, HttpServletResponse response) {
        try {
            Sertifikat sertifikat = sertifikatService.getXmlAsObject(id);
            String xml = xmlMapper.convertToXml(sertifikat, Sertifikat.class);
            ByteArrayInputStream pdf = (new PdfUtil()).generatePDF(xml, Sertifikat.class);
            org.apache.commons.io.IOUtils.copy(pdf, response.getOutputStream());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"sertifikat.pdf\"");
            response.flushBuffer();
        } catch (IOException | SAXException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
