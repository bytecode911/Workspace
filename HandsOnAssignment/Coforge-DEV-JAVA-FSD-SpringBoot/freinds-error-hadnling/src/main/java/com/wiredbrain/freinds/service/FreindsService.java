package com.wiredbrain.freinds.service;

import org.springframework.data.repository.CrudRepository;

import com.wiredbrain.freinds.model.Friend;

public interface FreindsService extends CrudRepository<Friend, Integer> {

	Iterable<Friend> findByFirstNameAndLastName(String firstName,String lastName);// give model object with String
	
	Iterable<Friend> findByFirstName(String firstName);// give model object with String
	Iterable<Friend> findByLastName(String lastName);// give model object with String
}
