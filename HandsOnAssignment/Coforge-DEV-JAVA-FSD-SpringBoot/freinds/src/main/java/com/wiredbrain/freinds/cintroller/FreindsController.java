package com.wiredbrain.freinds.cintroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wiredbrain.freinds.model.Friend;
import com.wiredbrain.freinds.service.FreindsService;

@RestController
public class FreindsController {

	@Autowired
	FreindsService freindsService;

	@PostMapping("/friend")
	Friend create(@RequestBody Friend friend) {
		return freindsService.save(friend);
	}

	@GetMapping("/friend")
	Iterable<Friend> read() {
		return freindsService.findAll();
	}

	@PutMapping("/friend")
	Friend update(@RequestBody Friend friend) {
		return freindsService.save(friend);
	}

	@DeleteMapping("/friend/{id}")
	void delete(@PathVariable Integer id) {
		freindsService.deleteById(id);
	}

	// http://localhost:8080/friend/1
	@GetMapping("/friend/{id}")
	Optional<Friend> findById(@PathVariable Integer id) {
		return freindsService.findById(id);
	}

//	//http://localhost:8080/friend/search?first=Ada&last=dev
//	@GetMapping("/friend/search")
//	Iterable<Friend> findByQuery(@RequestParam("first") String firstName, @RequestParam("last") String lastName) {
//		return freindsService.findByFirstNameAndLastName(firstName, lastName);
//	}

	//http://localhost:8080/friend/search?last=dev
	//http://localhost:8080/friend/search?first=Ada
	//http://localhost:8080/friend/search?first=Ada&last=dev
	@GetMapping("/friend/search")
	Iterable<Friend> findBySearchOption(@RequestParam(value = "first", required = false) String firstName,
			@RequestParam(value = "last", required = false) String lastName) {
		if (firstName != null && lastName != null) {
			return freindsService.findByFirstNameAndLastName(firstName, lastName);
		} else if (firstName != null)
			return freindsService.findByFirstName(firstName);
		else if (lastName != null)
			return freindsService.findByFirstName(lastName);
		else
			return freindsService.findAll();
	}

}
