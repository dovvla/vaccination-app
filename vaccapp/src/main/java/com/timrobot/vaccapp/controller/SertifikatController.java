package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.services.PotvrdaOVakcinacijiService;
import com.timrobot.vaccapp.services.SertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/sertifikat")
public class SertifikatController {

    @Autowired
    private SertifikatService sertifikatService;

    @GetMapping("/metadata-json/{id}")
    public Map<String, String> getJsonMetadataForDocument(@PathVariable String id) {
        try {
            return sertifikatService.getAllMetadataForDocumentForJSON(id);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/metadata-rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public String getRDFMetadataForDocument(@PathVariable String id) {
        try {
            return sertifikatService.getAllMetadataForDocumentInRDF(id);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
