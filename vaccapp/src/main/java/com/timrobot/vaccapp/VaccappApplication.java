package com.timrobot.vaccapp;

import com.timrobot.vaccapp.services.DemoService;
import com.timrobot.vaccapp.services.DemoServiceImpl;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
                .info(new Info().title(apiTitle).version("v1"));
    }
}
