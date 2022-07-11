package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.exceptions.ResourceAlreadyExistsException;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ZahtevZaSertifikatService {
    private final String folderId = "/db/vacc-app/zahtev-zeleni";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    public EntityList<Zahtev> getAll() {

        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Zahtev) mapper.convertToObject(s, "zahtev_za_sertifikat", Zahtev.class))
                .collect(Collectors.toList()));
    }

    public EntityList<Zahtev> getAllForUser(String id) {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Zahtev) mapper.convertToObject(s, "zahtev_za_sertifikat", Zahtev.class))
                .filter(zahtev -> zahtev
                        .getPodaciOPodnosiocu()
                        .getJMBG()
                        .equals(id))
                .collect(Collectors.toList()));
    }

    public Zahtev createZahtev(Zahtev zahtev) throws DatatypeConfigurationException {
        String documentId = UUID
                                    .randomUUID() + ".xml";

        dataAccessLayer.saveDocument(zahtev, folderId, documentId, Zahtev.class);

        return zahtev;

    }
}
