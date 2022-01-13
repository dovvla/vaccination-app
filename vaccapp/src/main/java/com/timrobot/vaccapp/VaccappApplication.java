package com.timrobot.vaccapp;

import com.timrobot.vaccapp.services.DemoService;
import com.timrobot.vaccapp.services.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
