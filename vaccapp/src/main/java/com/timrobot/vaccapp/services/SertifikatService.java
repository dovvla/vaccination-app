package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Sertifikat;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class SertifikatService {
    private final String folderId = "/db/vacc-app/sertifikat";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    private Date stringToDate(String date) {
        ZoneId defaultZoneId = ZoneId.systemDefault();

        //creating the instance of LocalDate using the day, month, year info
        LocalDate localDate = LocalDate.parse(date);

        //local date + atStartOfDay() + default time zone + toInstant() = Date
        return Date.from(localDate
                .atStartOfDay(defaultZoneId)
                .toInstant());
    }

    public EntityList<Sertifikat> getAllForDateRangeInclusive(String startDate, String endDate) throws DatatypeConfigurationException {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Sertifikat) mapper.convertToObject(s, "zeleni_sertifikat", Sertifikat.class))
                .filter(sertifikat -> !sertifikat.getPodaciOSertifikatu()
                        .getDatumIVreme()
                        .toGregorianCalendar()
                        .getTime()
                        .before(stringToDate(startDate)) && !sertifikat.getPodaciOSertifikatu()
                        .getDatumIVreme()
                        .toGregorianCalendar()
                        .getTime()
                        .after(stringToDate(endDate)))
                .collect(Collectors.toList()));
    }

}
