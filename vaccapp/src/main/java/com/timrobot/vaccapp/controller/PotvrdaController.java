package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Potvrda;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.services.PotvrdaOVakcinacijiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/potvrda")
public class PotvrdaController {

    @Autowired
    private PotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN') or hasAuthority('ZDRAVSTENI_RADNIK') or hasAuthority('SLUZBENIK')")

    public EntityList<Potvrda> getAll() {
        return potvrdaOVakcinacijiService.getAll();
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN') or hasAuthority('ZDRAVSTENI_RADNIK') or hasAuthority('SLUZBENIK')")

    public EntityList<Potvrda> getAllForUser(@PathVariable String id) {
        return potvrdaOVakcinacijiService.getAllForUser(id);
    }


    @GetMapping("/metadata-json/{id}")
    public Map<String, String> getJsonMetadataForDocument(@PathVariable String id) {
        try {
            return potvrdaOVakcinacijiService.getAllMetadataForDocumentForJSON(id);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/metadata-rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public String getRDFMetadataForDocument(@PathVariable String id) {
        try {
            return potvrdaOVakcinacijiService.getAllMetadataForDocumentInRDF(id);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
