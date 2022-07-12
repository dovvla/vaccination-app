package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Obrazac;
import com.timrobot.vaccapp.services.SaglasnostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
