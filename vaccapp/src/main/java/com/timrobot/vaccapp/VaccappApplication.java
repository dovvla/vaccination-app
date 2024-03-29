package com.timrobot.vaccapp;

import com.timrobot.vaccapp.models.*;
import com.timrobot.vaccapp.services.*;
import com.timrobot.vaccapp.utility.PdfUtil;
import com.timrobot.vaccapp.utility.QRcodeUtils;
import com.timrobot.vaccapp.utility.XHtmlUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.xmlrpc.webserver.ServletWebServer;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class VaccappApplication {


    public static void main(String[] args) {
        DemoService demoService = new DemoServiceImpl();

//        demoService.unmarshalExample();
//        demoService.marshalExample();
//        demoService.storeInXMLDBExample();
//        demoService.retrieveFromXMLDBExample();
//        demoService.RDFExample();
//        demoService.RDFExample();

        SpringApplication.run(VaccappApplication.class, args);

        //String base64 = QRcodeUtils.writeQRCode("wow");
        //System.out.println(base64);
        //System.out.println(QRcodeUtils.readQRCode(base64));
        try {
            //InteresovanjeService interesovanjeService = new InteresovanjeService();
            //String xml = interesovanjeService.convertToXML();
            //String xml = interesovanjeService.zahtev();
            //String xml = interesovanjeService.sertifikat();
            //String xml = interesovanjeService.potvrda();
            //String xml = interesovanjeService.saglasnost();
            //String xml = interesovanjeService.izvestaj();
           // XHtmlUtil.generateHTML(xml, Sertifikat.class);
           // PdfUtil pdfUtil = new PdfUtil();
            //pdfUtil.generatePDF(xml, Sertifikat.class);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Autowired
//    private DemoService demoServiceAW;
//

    @Autowired
    private SaglasnostService saglasnostService;

    @Autowired
    private DemoService demoService;

    @Autowired
    private PotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @Autowired
    private SertifikatService sertifikatService;

//    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws TransformerException {
        demoService.unmarshalExample();
        demoService.marshalExample();
        demoService.storeInXMLDBExample();
        demoService.retrieveFromXMLDBExample();
        demoService.RDFExample();
        demoService.RDFExample();
        try {
            saglasnostService.advancedSearchSaglasnost("Srba", "Hrvotic", "Pfizer", "2021-05-04", "<http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju/iskazivanje_interesovanja_za_vakcinaciju_1>", true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object x = potvrdaOVakcinacijiService.advancedSearchPotvrda("2021-05-26", "Vakcinski punkt Novi Sad", false);
            x = null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object x = sertifikatService.advancedSearchSertifikat("Nikola", "Nikolic", "2021-05-26T18:13:51.0", "<http://tim.robot/zahtev_za_sertifikat/zahtev_za_sertifikat>", true);
            x = null;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", "vaccapp");
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(new Info()
                        .title(apiTitle)
                        .version("v1"));
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("127.0.0.1");
        mailSender.setPort(1025);

        mailSender.setUsername("tim10isamrs2021");
        mailSender.setPassword("tim10psw2021");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
