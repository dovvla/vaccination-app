package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Korisnik;
import com.timrobot.vaccapp.models.Obrazac;
import com.timrobot.vaccapp.services.KorisnikService;
import com.timrobot.vaccapp.services.SaglasnostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/saglasnost")
public class SaglasnostController {
    @Autowired
    private SaglasnostService saglasnostService;


    @Autowired
    private KorisnikService korisnikService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Obrazac> getAll() {
        return saglasnostService.getAll();
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Obrazac> getAllForUser(@PathVariable String id) {
        return saglasnostService.getAllForUser(id);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN') or hasAuthority('ZDRAVSTENI_RADNIK') or hasAuthority('SLUZBENIK')")
    public ResponseEntity<?> putSaglanost(@RequestBody Obrazac obrazac) {
        try {
            Authentication auth = SecurityContextHolder
                    .getContext().getAuthentication();
            Korisnik korisnik = korisnikService.getKorisnikByEmail( ((User) auth.getPrincipal()).getUsername());
            if(obrazac.getPodaciOPacijentu().getDrzavljanstvo().getJMBG()==null) {saglasnostService.popuniKorisnika(obrazac, korisnik);}
            return ResponseEntity.ok(saglasnostService.putSaglasnost(obrazac));
        }
        catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }


    }

    @PostMapping(value = "/imunizuj", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('ZDRAVSTENI_RADNIK')")

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
    public Map<String, String> getJsonMetadataForDocument(@PathVariable String id) {
        try {
            return saglasnostService.getAllMetadataForDocumentForJSON(id);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/metadata-rdf/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public String getRDFMetadataForDocument(@PathVariable String id) {
        try {
            return saglasnostService.getAllMetadataForDocumentInRDF(id);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
