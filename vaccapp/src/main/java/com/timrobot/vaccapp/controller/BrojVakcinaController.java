package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.BrojVakcina;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.services.BrojVakcinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/broj-vakcina")
public class BrojVakcinaController {

    @Autowired
    private BrojVakcinaService brojVakcinaService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<BrojVakcina> getAll() {
        return brojVakcinaService.getAll();
    }

}
