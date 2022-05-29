package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserCreationDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.error.UserCreationException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.service.impl.UserServiceImpl;

@RefreshScope
@RestController
@RequestMapping("/users")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired UserServiceImpl userService;
	
	@Value("${hello.world}")
	private String helloWorld;
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getUsers() {
		log.info(helloWorld);
		List<UserResponseDTO> users = userService.getUsers();
		return new ResponseEntity<List<UserResponseDTO>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> getUserByID(@PathVariable("userId") int userId) throws UserNotFoundException{
		UserResponseDTO user = userService.getUserByID(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> createUser(@Valid @RequestBody UserCreationDTO user) throws UserCreationException{
		boolean created = userService.createUser(user);
		return new ResponseEntity<Boolean>(created, HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("userId") int userId, @Valid @RequestBody UserCreationDTO user) throws UserNotFoundException{
		UserResponseDTO updatedUser = userService.updateUser(userId, user);
		return new ResponseEntity<UserResponseDTO>(updatedUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("userId") int userId) throws UserNotFoundException{
		boolean created = userService.deleteUser(userId);
		return new ResponseEntity<Boolean>(created, HttpStatus.OK);
	}
}
