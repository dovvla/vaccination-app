package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Izvestaj;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.services.IzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.datatype.DatatypeConfigurationException;

@RestController
@RequestMapping("/api/izvestaj")
public class IzvestajController {

    @Autowired
    private IzvestajService izvestajService;

    @GetMapping(value = "/{startDate}/{endDate}", produces = MediaType.APPLICATION_XML_VALUE)
    public Izvestaj getIzvestajForDates(@PathVariable String startDate, @PathVariable String endDate) {
        try {
            return izvestajService.getOrCreateIzvestajForDateRange(startDate, endDate);
        } catch (DatatypeConfigurationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
