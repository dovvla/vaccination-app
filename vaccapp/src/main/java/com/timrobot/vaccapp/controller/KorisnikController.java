package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.exceptions.ResourceAlreadyExistsException;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Korisnik;
import com.timrobot.vaccapp.services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN') or hasAuthority('ZDRAVSTENI_RADNIK') or hasAuthority('SLUZBENIK')")
    public EntityList<Korisnik> getAllKorisnici() {
        return korisnikService.getAll();
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN') or hasAuthority('ZDRAVSTENI_RADNIK') or hasAuthority('SLUZBENIK')")
    public Korisnik  getMe() throws Exception {
        Authentication auth = SecurityContextHolder
                .getContext().getAuthentication();
        return korisnikService.getKorisnikByEmail((String) auth.getPrincipal());
    }

}
