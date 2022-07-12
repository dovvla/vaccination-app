package com.timrobot.vaccapp;

import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.services.DemoService;
import com.timrobot.vaccapp.services.DemoServiceImpl;
import com.timrobot.vaccapp.services.InteresovanjeService;
import com.timrobot.vaccapp.utility.QRcodeUtils;
import com.timrobot.vaccapp.utility.XHtmlUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Properties;

@SpringBootApplication
public class VaccappApplication {


    public static void main(String[] args) {
        DemoService demoService = new DemoServiceImpl();

        demoService.unmarshalExample();
        demoService.marshalExample();
        demoService.storeInXMLDBExample();
        demoService.retrieveFromXMLDBExample();
        demoService.RDFExample();

        SpringApplication.run(VaccappApplication.class, args);

        //String base64 = QRcodeUtils.writeQRCode("wow");
        //System.out.println(base64);
        //System.out.println(QRcodeUtils.readQRCode(base64));
        try {
            InteresovanjeService interesovanjeService = new InteresovanjeService();
            String xml = interesovanjeService.convertToXML();
            XHtmlUtil.generateHTML(xml, ObrazacInteresovanja.class);

        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", "homebot");
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
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
