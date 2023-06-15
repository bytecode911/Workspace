package com.mycompany.service;

import java.util.List;

import com.mycompany.model.Speaker;
import com.mycompany.repository.SpeakerRepository;

public class SpeakerServiceImpl implements SpeakerService {

//	private SpeakerRepository repository = new HibernateSpeakerRepositoryImpl();
	private SpeakerRepository repository;

	public SpeakerServiceImpl() {
		System.out.println("Default Constructor");
	}

	public SpeakerServiceImpl(SpeakerRepository repository) {
		System.out.println("args Constructor");
		this.repository = repository;
	}

	@Override
	public List<Speaker> findAll() {
		return repository.findAll();
	}

	public void setSpeakerRepository(SpeakerRepository repository) {
		this.repository = repository;
	}

}
