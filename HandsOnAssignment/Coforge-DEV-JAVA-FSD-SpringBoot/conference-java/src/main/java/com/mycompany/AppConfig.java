package com.mycompany;

import java.util.Calendar;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.mycompany.repository.HibernateSpeakerRepositoryImpl;
import com.mycompany.repository.SpeakerRepository;
import com.mycompany.service.SpeakerService;
import com.mycompany.service.SpeakerServiceImpl;
import com.mycompany.util.CalendarFactory;

@Configuration
@ComponentScan("com.mycompany") // include base package and exact related to base package
public class AppConfig {

	@Bean(name = "cal")
	public CalendarFactory calFactory() {
		CalendarFactory factory = new CalendarFactory();
		factory.addDays(2);
		return factory;
	}

	@Bean
	public Calendar cal() throws Exception {
		return calFactory().getObject();
	}

	// @Bean(name = "speakerService")
	// @Scope(value = BeanDefinition.SCOPE_SINGLETON)
	// @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public SpeakerService getSpeakerService() {
		// return new SpeakerServiceImpl();
		// Setter Injection
		// SpeakerServiceImpl serviceImpl=new SpeakerServiceImpl();
		// serviceImpl.setRepository(getSpeakerRepository());
		// Constructor Injection
		// SpeakerServiceImpl serviceImpl = new
		// SpeakerServiceImpl(getSpeakerRepository());

		SpeakerServiceImpl serviceImpl = new SpeakerServiceImpl();
		return serviceImpl;
	}

	/*
	 * @Bean(name = "speakerRepository") public SpeakerRepository
	 * getSpeakerRepository() { return new HibernateSpeakerRepositoryImpl(); }
	 */

}
