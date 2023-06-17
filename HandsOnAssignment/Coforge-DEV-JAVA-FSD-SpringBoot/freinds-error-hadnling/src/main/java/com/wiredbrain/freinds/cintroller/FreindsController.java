package com.wiredbrain.freinds.cintroller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wiredbrain.freinds.model.Friend;
import com.wiredbrain.freinds.service.FreindsService;
import com.wiredbrain.freinds.util.ErrorMessage;
import com.wiredbrain.freinds.util.FieldErrorMessage;

@RestController
public class FreindsController {

	@Autowired
	FreindsService freindsService;

	@PostMapping("/friend")
	Friend create(@RequestBody Friend friend) {
		if (friend.getId() == 0 && friend.getFirstName() != null && friend.getLastName() != null)
			return freindsService.save(friend);
		else
			throw new ValidationException("freind can not be created ");
	}

	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class)
	 * List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
	 * 
	 * List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
	 * 
	 * List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream()
	 * .map(fieldError -> new FieldErrorMessage(fieldError.getField(),
	 * fieldError.getDefaultMessage())) .collect(Collectors.toList()); return
	 * fieldErrorMessages;
	 * 
	 * }
	 */
	
//	@ExceptionHandler(ValidationException.class)
//	ResponseEntity<String> exceptionHandler(ValidationException e) {
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	ErrorMessage exceptionHandler(ValidationException e) {
	return new ErrorMessage("400", e.getMessage());
}

	@GetMapping("/friend")
	Iterable<Friend> read() {
		return freindsService.findAll();
	}

	@PutMapping("/friend")
	ResponseEntity<Friend> update(@RequestBody Friend friend) {
		if (freindsService.findById(friend.getId()).isPresent())
			return new ResponseEntity<>(freindsService.save(friend), HttpStatus.OK);
		else
			return new ResponseEntity<>(friend, HttpStatus.BAD_REQUEST);

	};

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

	// http://localhost:8080/friend/search?last=dev
	// http://localhost:8080/friend/search?first=Ada
	// http://localhost:8080/friend/search?first=Ada&last=dev
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
