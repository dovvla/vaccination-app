package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Potvrda;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.stream.Collectors;

public class PotvrdaOVakcinacijiService {
    private final String folderId = "/db/vacc-app/potvrda-o-vakcinaciji";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    public EntityList<Potvrda> getAll() {

        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Potvrda) mapper.convertToObject(s, "potvrda_o_vakcinaciji", Zahtev.class))
                .collect(Collectors.toList()));
    }

    public EntityList<Potvrda> getAllForUser(String id) {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Potvrda) mapper.convertToObject(s, "potvrda_o_vakcinaciji", Potvrda.class))
                .filter(potvrda -> potvrda
                        .getPodaciPacijenta()
                        .getJMBG()
                        .equals(id))
                .collect(Collectors.toList()));
    }

    public Potvrda createPotvrda(Potvrda potvrda) {
        String documentId = UUID
                                    .randomUUID() + ".xml";

        potvrda.setSifraPotvrde(documentId);

        dataAccessLayer.saveDocument(potvrda, folderId, documentId, Potvrda.class);

        return potvrda;

    }
}
