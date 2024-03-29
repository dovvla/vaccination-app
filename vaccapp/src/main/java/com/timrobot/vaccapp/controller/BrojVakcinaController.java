package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.BrojVakcina;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.services.BrojVakcinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/broj-vakcina")
public class BrojVakcinaController {

    @Autowired
    private BrojVakcinaService brojVakcinaService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<BrojVakcina> getAll() {
        return brojVakcinaService.getAll();
    }

    @GetMapping(value = "/update")
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public ResponseEntity<?> updateBrojVakcina(@RequestParam String vakcina, @RequestParam Integer broj) {
        try {
            brojVakcinaService.izmeniBrojVakcina(vakcina, broj);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
