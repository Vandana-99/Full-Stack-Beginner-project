package com.vandana.full.stack.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vandana.full.stack.application.exception.UserNotFoundException;
import com.vandana.full.stack.application.model.User;
import com.vandana.full.stack.application.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/user")
	User newUser(@RequestBody User newuser) {
		return userRepository.save(newuser);
		
	}
	
	@GetMapping("/users")
	List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	User getUserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException(id));
	}
	
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User updateUser, @PathVariable Long id) {
		return userRepository.findById(id)
				.map(user-> {
					user.setUsername(updateUser.getUsername());
					user.setName(updateUser.getName());
					user.setEmail(updateUser.getEmail());
					return u serRepository.save(user); 
				}).orElseThrow(()-> new UserNotFoundException(id));
				
				
	}
	
	@DeleteMapping("user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "User with id" + id+" has been deleted success";
	}
	
}
