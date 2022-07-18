package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.*;
import com.timrobot.vaccapp.services.BrojVakcinaService;
import com.timrobot.vaccapp.services.PotvrdaOVakcinacijiService;
import com.timrobot.vaccapp.services.SaglasnostService;
import com.timrobot.vaccapp.services.SertifikatService;
import com.timrobot.vaccapp.utility.XHtmlUtil;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//    @Autowired
//    private XMLMapper xmlMapper;
//
//    @GetMapping(value = "/regular", produces = MediaType.APPLICATION_XML_VALUE)
//    public List<String> regularSearch(@RequestParam String query) {
//        try {
//            List<Obrazac> saglasnosti = saglasnostService.regularSearchSaglasnost(query);
//            List<String> saglasnostiXHTML = saglasnosti.stream()
//                    .map(s -> {
//                        String xml = xmlMapper.convertToXml(s, Obrazac.class);
//                        ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Obrazac.class);
//                        int n = xhtml.available();
//                        byte[] bytes = new byte[n];
//                        xhtml.read(bytes, 0, n);
//                        return new String(bytes, StandardCharsets.UTF_8);
//                    })
//                    .collect(Collectors.toList());
//            List<Potvrda> potvrde = potvrdaOVakcinacijiService.regularSearchPotvrda(query);
//            List<String> potvrdeXHTML = potvrde.stream()
//                    .map(p -> {
//                        String xml = xmlMapper.convertToXml(p, Potvrda.class);
//                        ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Potvrda.class);
//                        int n = xhtml.available();
//                        byte[] bytes = new byte[n];
//                        xhtml.read(bytes, 0, n);
//                        return new String(bytes, StandardCharsets.UTF_8);
//                    })
//                    .collect(Collectors.toList());
//            List<Sertifikat> sertifikati = sertifikatService.regularSearchSertifikat(query);
//            List<String> sertifikatiXHTML = sertifikati.stream()
//                    .map(s -> {
//                        String xml = xmlMapper.convertToXml(s, Sertifikat.class);
//                        ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Sertifikat.class);
//                        int n = xhtml.available();
//                        byte[] bytes = new byte[n];
//                        xhtml.read(bytes, 0, n);
//                        return new String(bytes, StandardCharsets.UTF_8);
//                    })
//                    .collect(Collectors.toList());
//            List<String> rezultati = new ArrayList<>();
//            rezultati.addAll(saglasnostiXHTML);
//            rezultati.addAll(potvrdeXHTML);
//            rezultati.addAll(sertifikatiXHTML);
//            return rezultati;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping(value = "/saglasnost/advanced", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Obrazac> advancedSearchSaglasnost(@RequestParam String ime, @RequestParam String prezime,
                                                        @RequestParam String nazivVakcine, @RequestParam String datumIzdavanja,
                                                        @RequestParam String hrefInteresovanje, @RequestParam Boolean logicalAnd) {
        try {
            return new EntityList<>(saglasnostService.advancedSearchSaglasnost(ime, prezime, nazivVakcine, datumIzdavanja, hrefInteresovanje, logicalAnd));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/potvrda/advanced", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Potvrda> advancedSearchPotvrda(@RequestParam String datum, @RequestParam String zdravstvenaUstanova,
                                                     @RequestParam Boolean logicalAnd) {
        try {
            return new EntityList<>(potvrdaOVakcinacijiService.advancedSearchPotvrda(datum, zdravstvenaUstanova, logicalAnd));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/sertifikat/advanced", produces = MediaType.APPLICATION_XML_VALUE)
    public EntityList<Sertifikat> advancedSearchSertifikat(@RequestParam String ime, @RequestParam String prezime,
                                                           @RequestParam String datumIVremeIzdavanja,
                                                           @RequestParam String hrefZahtev, @RequestParam Boolean logicalAnd) {
        try {
            return new EntityList<>(sertifikatService.advancedSearchSertifikat(ime, prezime, datumIVremeIzdavanja, hrefZahtev, logicalAnd));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
