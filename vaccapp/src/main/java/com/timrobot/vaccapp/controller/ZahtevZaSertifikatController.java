package com.timrobot.vaccapp.controller;

import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Korisnik;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.models.Sertifikat;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.services.KorisnikService;
import com.timrobot.vaccapp.services.ZahtevZaSertifikatService;
import com.timrobot.vaccapp.utility.XHtmlUtil;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.exist.debugger.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/zahtev-za-sertifikat")
public class ZahtevZaSertifikatController {

    @Autowired
    private ZahtevZaSertifikatService zahtevZaSertifikatService;
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN') or hasAuthority('ZDRAVSTVENI_RADNIK') or hasAuthority('SLUZBENIK')")

    public EntityList<Zahtev> getAll() {
        return zahtevZaSertifikatService.getAll();
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN') or hasAuthority('ZDRAVSTVENI_RADNIK') or hasAuthority('SLUZBENIK')")

    public EntityList<Zahtev> getAllForUser(@PathVariable String id) {
        return zahtevZaSertifikatService.getAllForUser(id);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN') or hasAuthority('ZDRAVSTVENI_RADNIK') or hasAuthority('SLUZBENIK')")
    public ResponseEntity<?> createZahtevZaZeleni(@RequestBody Zahtev zahtev) {
        try {
            Authentication auth = SecurityContextHolder
                    .getContext().getAuthentication();
            Korisnik korisnik = korisnikService.getKorisnikByEmail((String) auth.getPrincipal());
            if (zahtev.getPodaciOPodnosiocu() == null || zahtev.getPodaciOPodnosiocu().getIme() == null) {
                zahtevZaSertifikatService.popuniKorisnika(zahtev, korisnik);
            }

            return ResponseEntity.ok(zahtevZaSertifikatService.createZahtev(zahtev));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }

    @GetMapping(value = "/neobradjeni", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public EntityList<Zahtev> getAllNeobradjen() {
        return zahtevZaSertifikatService.getAllNeobradjen();
    }

    @GetMapping(value = "/odbij/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public ResponseEntity<?> odbijZahtev(@PathVariable String id, @RequestParam String razlog) {
        if (!zahtevZaSertifikatService.odbijZahtev(id, razlog))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/prihvati/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public ResponseEntity<?> prihvatiZahtev(@PathVariable String id) {
        try {
            if (!zahtevZaSertifikatService.prihvatiZahtev(id)) {
            }
            // throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    private XMLMapper xmlMapper;

    @GetMapping(value = "/{id}/show", produces = MediaType.APPLICATION_XML_VALUE)
    public String getZahtevXHTML(@PathVariable String id) {
        Zahtev zahtev = zahtevZaSertifikatService.getXmlAsObject(id);
        String xml = xmlMapper.convertToXml(zahtev, Zahtev.class);
        ByteArrayInputStream xhtml = XHtmlUtil.generateHTML(xml, Zahtev.class);
        int n = xhtml.available();
        byte[] bytes = new byte[n];
        xhtml.read(bytes, 0, n);
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
