package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Korisnik;
import com.timrobot.vaccapp.models.Termin;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TerminService {
    private final String folderId = "/db/vacc-app/termini";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    public Termin getXmlAsObject(String documentId) {
        String xmlString = dataAccessLayer
                .getDocument(folderId, documentId)
                .get();

        return (Termin) mapper.convertToObject(xmlString, "termin", Termin.class);
    }

    public Termin saveXmlFromText(Termin termin) {
        String documentId = UUID.randomUUID() + ".xml";
        dataAccessLayer.saveDocument(termin, folderId, documentId, Termin.class);
        return termin;
    }

    public EntityList<Termin> getAll() {
        ArrayList<Termin> termins = (ArrayList<Termin>) dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Termin) mapper.convertToObject(s, "termin", Termin.class))
                .collect(Collectors.toList());

        return new EntityList<>(termins);

    }

    public Optional<Termin> getSlobodanTermin() {
        ArrayList<Termin> termini = (ArrayList<Termin>) dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(t -> (Termin) mapper.convertToObject(t, "termin", Termin.class))
                .collect(Collectors.toList());

        Comparator<Termin> terminDateComparator = Comparator
                .comparing(termin -> termin
                        .getDatumVreme()
                        .toGregorianCalendar()
                        .getTime());
        return termini
                .stream()
                .min(terminDateComparator);
    }
}
