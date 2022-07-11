package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.services.ObrazacInteresovanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interesovanje")
public class ObrazacInteresovanjaController {

    @Autowired
    private ObrazacInteresovanjaService obrazacInteresovanjaService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<ObrazacInteresovanja> getAll() {
        return obrazacInteresovanjaService.getAll();
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<ObrazacInteresovanja> getAllForUser(@PathVariable String id) {
        return obrazacInteresovanjaService.getAllForUser(id);
    }

    @GetMapping(value = "/date/{startDate}/{endDate}", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<ObrazacInteresovanja> getAllForDateRange(@PathVariable String startDate, @PathVariable String endDate) {
        return obrazacInteresovanjaService.getAllForDateRange(startDate, endDate);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createObrazacInteresovanja(@RequestBody ObrazacInteresovanja obrazacInteresovanja) {
        try {
            return ResponseEntity.ok(obrazacInteresovanjaService.createObrazacInteresovanja(obrazacInteresovanja));
        }
        catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }
}
