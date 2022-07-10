package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.exceptions.ResourceAlreadyExistsException;
import com.timrobot.vaccapp.models.Korisnik;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {

    private final String folderId = "/db/vacc-app/korisnici";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    public Korisnik getXmlAsObject(String documentId) {
        String xmlString = dataAccessLayer.getDocument(folderId, documentId).get();

        return (Korisnik) mapper.convertToObject(xmlString, "korisnik", Korisnik.class);
    }

    public Korisnik saveXmlFromText(Korisnik korisnik) throws ResourceAlreadyExistsException {
        String documentId = korisnik.getEmail() + ".xml";

        Optional<String> exists = dataAccessLayer.getDocument(folderId, korisnik.getEmail());
        if (exists.isPresent()) {
            throw new ResourceAlreadyExistsException("Email vec postoji");
        }


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(korisnik.getSifra());
        korisnik.setSifra(encodedPassword);

        dataAccessLayer.saveDocument(korisnik, folderId, documentId, Korisnik.class);
//        try {
//            metadataExtractor.extractAndSave(xmlString, "/korisnici");
//        } catch (FileNotFoundException | TransformerException e) {
//            e.printStackTrace();
//        }

        return korisnik;
    }
}
