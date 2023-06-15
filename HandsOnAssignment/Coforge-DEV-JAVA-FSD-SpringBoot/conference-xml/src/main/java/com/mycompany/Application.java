package com.mycompany;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.service.SpeakerService;

public class Application {

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

//		SpeakerService service = new SpeakerServiceImpl();

		SpeakerService service = appContext.getBean("speakerService", SpeakerService.class);
		System.out.println(service.findAll().get(0).getFirstName());

	}

}
