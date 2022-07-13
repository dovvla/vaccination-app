package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.*;
import com.timrobot.vaccapp.services.BrojVakcinaService;
import com.timrobot.vaccapp.services.PotvrdaOVakcinacijiService;
import com.timrobot.vaccapp.services.SaglasnostService;
import com.timrobot.vaccapp.services.SertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SaglasnostService saglasnostService;

    @Autowired
    private PotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @Autowired
    private SertifikatService sertifikatService;

    @GetMapping(value = "/regular", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Object> regularSearch(@RequestParam String query) {
        try {
            List<Obrazac> saglasnosti = saglasnostService.regularSearchSaglasnost(query);
            List<Potvrda> potvrde = potvrdaOVakcinacijiService.regularSearchPotvrda(query);
            List<Sertifikat> sertifikati = sertifikatService.regularSearchSertifikat(query);
            List<Object> rezultati = new ArrayList<>();
            rezultati.addAll(saglasnosti);
            rezultati.addAll(potvrde);
            rezultati.addAll(sertifikati);
            return new EntityList<>(rezultati);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
