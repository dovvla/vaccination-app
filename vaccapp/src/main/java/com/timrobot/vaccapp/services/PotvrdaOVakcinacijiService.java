package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Obrazac;
import com.timrobot.vaccapp.models.Potvrda;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
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

    public List<Potvrda> regularSearchPotvrda(String search) throws Exception {
        String searchQuery = String.format("xquery version \"3.1\";\n" +
                "\n" +
                "declare namespace potv=\"http://tim.robot/potvrda_o_vakcinaciji\";\n" +
                "import module namespace functx=\"http://www.functx.com\";\n" +
                "\n" +
                "declare function local:search($keyword as xs:string)\n" +
                "{\n" +
                "    let $potvrde := collection(\"/db/vacc-app/potvrda-o-vakcinaciji\")\n" +
                "    let $rezultat :=\n" +
                "    (\n" +
                "        $potvrde//potv:Potvrda[contains(., $keyword)]\n" +
                "    )\n" +
                "\n" +
                " return\n" +
                "    functx:distinct-nodes($rezultat)\n" +
                "};\n" +
                "\n" +
                "local:search(\"%s\")", search);

        List<String> matchingPotvrdeXML = dataAccessLayer.executeXPathQuery(folderId, searchQuery, "http://tim.robot/potvrda_o_vakcinaciji");

        List<Potvrda> matchingPotvrde = new ArrayList<>();
        for (String XML : matchingPotvrdeXML) {
            matchingPotvrde.add((Potvrda) mapper.convertToObject(XML, "potvrda_o_vakcinaciji", Potvrda.class));
        }

        return matchingPotvrde;
    }
}
