package com.mycompany.service;

import java.util.List;

import com.mycompany.model.Speaker;
import com.mycompany.repository.HibernateSpeakerRepositoryImpl;
import com.mycompany.repository.SpeakerRepository;

public class SpeakerServiceImpl implements SpeakerService {

	//private SpeakerRepository repository = new HibernateSpeakerRepositoryImpl();

	private SpeakerRepository repository;
	
	//Constructor Injection
	public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
		this.repository = speakerRepository;
	}
	
	@Override
	public List<Speaker> findAll() {
		return repository.findAll();

	}

	//Setter Injection
	public void setRepository(SpeakerRepository repository) {
		this.repository = repository;
	}

	
	


}