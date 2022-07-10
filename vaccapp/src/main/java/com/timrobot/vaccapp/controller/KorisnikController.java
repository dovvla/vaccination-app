package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.exceptions.ResourceAlreadyExistsException;
import com.timrobot.vaccapp.models.Korisnik;
import com.timrobot.vaccapp.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> saveXml(@RequestBody Korisnik korisnikXml) {
        try {
            Korisnik korisnik = korisnikService.saveXmlFromText(korisnikXml);
            return ResponseEntity.ok(korisnik);
        } catch (ResourceAlreadyExistsException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}