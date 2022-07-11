package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Obrazac;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SaglasnostService {
    private final String folderId = "/db/vacc-app/saglasnost";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    public EntityList<Obrazac> getAll() {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Obrazac) mapper.convertToObject(s, "saglasnost", Obrazac.class))
                .collect(Collectors.toList()));
    }

    public EntityList<Obrazac> getAllForUser(String id) {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Obrazac) mapper.convertToObject(s, "saglasnost", Obrazac.class))
                .filter(obrazac -> obrazac
                        .getPodaciOPacijentu()
                        .getDrzavljanstvo()
                        .getJMBG()
                        .equals(id))
                .collect(Collectors.toList()));
    }

    public Obrazac putSaglasnost(Obrazac obrazac) {
        String documentId = obrazac
                                    .getPodaciOPacijentu()
                                    .getDrzavljanstvo()
                                    .getJMBG()
                            + ".xml";
        dataAccessLayer.saveDocument(obrazac, folderId, documentId, Obrazac.class);
        return obrazac;
    }
}
