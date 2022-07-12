package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.services.ZahtevZaSertifikatService;
import org.exist.debugger.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/zahtev-za-sertifikat")
public class ZahtevZaSertifikatController {

    @Autowired
    private ZahtevZaSertifikatService zahtevZaSertifikatService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Zahtev> getAll() {
        return zahtevZaSertifikatService.getAll();
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Zahtev> getAllForUser(@PathVariable String id) {
        return zahtevZaSertifikatService.getAllForUser(id);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createZahtevZaZeleni(@RequestBody Zahtev zahtev) {
        try {
            return ResponseEntity.ok(zahtevZaSertifikatService.createZahtev(zahtev));
        }
        catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }

    @GetMapping(value = "/neobradjeni", produces = MediaType.APPLICATION_XML_VALUE)
//    @PreAuthorize("hasRole('SLUZBENIK')")
    public EntityList<Zahtev> getAllNeobradjen() {
        return zahtevZaSertifikatService.getAllNeobradjen();
    }

    @GetMapping(value = "/odbij/{id}", produces = MediaType.APPLICATION_XML_VALUE)
//    @PreAuthorize("hasRole('SLUZBENIK')")
    public ResponseEntity<?> odbijZahtev(@PathVariable String id, @RequestParam String razlog) {
        if (!zahtevZaSertifikatService.odbijZahtev(id, razlog))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
