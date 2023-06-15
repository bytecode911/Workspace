package com.mycompany.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mycompany.model.Speaker;
import com.mycompany.repository.HibernateSpeakerRepositoryImpl;
import com.mycompany.repository.SpeakerRepository;

@Service("speakerService")
@Scope()
@Profile("dev")
//@Profile("prod")
public class SpeakerServiceImpl implements SpeakerService {

	// private SpeakerRepository repository = new HibernateSpeakerRepositoryImpl();
	private SpeakerRepository repository;

	public SpeakerServiceImpl() {
		System.out.println("SpeakerServiceImpl no args contrsuctor");
	}

	// Constructor Injection
	@Autowired
	public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
		System.out.println("SpeakerServiceImpl repository contrsuctor");
		this.repository = speakerRepository;
	}

	@Override
	public List<Speaker> findAll() {
		return repository.findAll();

	}

	@PostConstruct
	private void initialize() {
		System.out.println("we are called after the constructors");
	}

	// Setter Injection
//	@Autowired
	public void setRepository(SpeakerRepository repository) {
		System.out.println("SpeakerServiceImpl  setter");
		this.repository = repository;
	}

}
