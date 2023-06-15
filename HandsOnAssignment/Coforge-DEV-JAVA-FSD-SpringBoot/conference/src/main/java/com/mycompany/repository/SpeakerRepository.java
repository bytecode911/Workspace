package com.mycompany.repository;

import java.util.List;

import com.mycompany.model.Speaker;

public interface SpeakerRepository {

	List<Speaker> findAll();

}