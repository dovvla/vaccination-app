package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Izvestaj;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.services.IzvestajService;
import com.timrobot.vaccapp.utility.PdfUtil;
import com.timrobot.vaccapp.utility.XHtmlUtil;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/izvestaj")
public class IzvestajController {

    @Autowired
    private IzvestajService izvestajService;

    @Autowired
    private XMLMapper xmlMapper;

//    @GetMapping(value = "/{startDate}/{endDate}", produces = MediaType.APPLICATION_XML_VALUE)
//    public Izvestaj getIzvestajForDates(@PathVariable String startDate, @PathVariable String endDate) {
//        try {
//            return izvestajService.getOrCreateIzvestajForDateRange(startDate, endDate);
//        } catch (DatatypeConfigurationException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping(value = "/{startDate}/{endDate}", produces = MediaType.APPLICATION_XML_VALUE)
    public String getIzvestajXHTMLForDates(@PathVariable String startDate, @PathVariable String endDate) {
        try {
            Izvestaj izvestaj = izvestajService.getOrCreateIzvestajForDateRange(startDate, endDate);
            String xml = xmlMapper.convertToXml(izvestaj, Izvestaj.class);
            ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Izvestaj.class);
            int n = xhtml.available();
            byte[] bytes = new byte[n];
            xhtml.read(bytes, 0, n);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (DatatypeConfigurationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{startDate}/{endDate}/xhtml", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadIzvestajXHTMLForDates(@PathVariable String startDate, @PathVariable String endDate, HttpServletResponse response) {
        try {
            Izvestaj izvestaj = izvestajService.getOrCreateIzvestajForDateRange(startDate, endDate);
            String xml = xmlMapper.convertToXml(izvestaj, Izvestaj.class);
            ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Izvestaj.class);
            org.apache.commons.io.IOUtils.copy(xhtml, response.getOutputStream());
            response.setContentType("application/html");
            response.setHeader("Content-Disposition", "attachment; filename=\"izvestaj.html\"");
            response.flushBuffer();
        } catch (DatatypeConfigurationException | IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{startDate}/{endDate}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadIzvestajPDFForDates(@PathVariable String startDate, @PathVariable String endDate, HttpServletResponse response) {
        try {
            Izvestaj izvestaj = izvestajService.getOrCreateIzvestajForDateRange(startDate, endDate);
            String xml = xmlMapper.convertToXml(izvestaj, Izvestaj.class);
            ByteArrayInputStream pdf = (new PdfUtil()).generatePDF(xml, Izvestaj.class);
            org.apache.commons.io.IOUtils.copy(pdf, response.getOutputStream());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"izvestaj.pdf\"");
            response.flushBuffer();
        } catch (DatatypeConfigurationException | IOException | SAXException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
