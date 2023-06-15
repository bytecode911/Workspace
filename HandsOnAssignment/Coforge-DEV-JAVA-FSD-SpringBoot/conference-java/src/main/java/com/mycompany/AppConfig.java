package com.mycompany;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.mycompany.repository.HibernateSpeakerRepositoryImpl;
import com.mycompany.repository.SpeakerRepository;
import com.mycompany.service.SpeakerService;
import com.mycompany.service.SpeakerServiceImpl;

@Configuration
public class AppConfig {

	@Bean(name = "speakerService")
		@Scope(value = BeanDefinition.SCOPE_SINGLETON)
	//@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public SpeakerService getSpeakerService() {
		//return new SpeakerServiceImpl();
		
		//Setter Injection
		//SpeakerServiceImpl serviceImpl=new SpeakerServiceImpl();
	//	serviceImpl.setRepository(getSpeakerRepository());
		
		//Constructor Injectionton
		SpeakerServiceImpl serviceImpl=new SpeakerServiceImpl(getSpeakerRepository());
		return serviceImpl;
	}
	
	@Bean(name = "speakerRepository")
	public SpeakerRepository getSpeakerRepository() {
		return new HibernateSpeakerRepositoryImpl();
	}

}
