package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserCreationDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.error.UserCreationException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired UserRepository userRepository;

	@Override
	public UserResponseDTO getUserByID(int userId) throws UserNotFoundException{
		Optional<User> oUser = userRepository.findById(userId);
		if(oUser.isPresent()) {
			User user = oUser.get();
			return new UserResponseDTO(user.getUserId(), user.getName(), user.getEmail(), user.getLastLoginTime());
		}
		else {
			throw new UserNotFoundException("User not found with ID " + userId);
		}
	}

	@Override
	public UserResponseDTO getUserByEmail(String email) throws UserNotFoundException{
		Optional<User> oUser = userRepository.findByEmail(email);
		if(oUser.isPresent()) {
			User user = oUser.get();
			return new UserResponseDTO(user.getUserId(), user.getName(), user.getEmail(), user.getLastLoginTime());
		}
		else {
			throw new UserNotFoundException("User not found with email " + email);
		}
	}

	@Override
	public List<UserResponseDTO> getUsers() {
		return userRepository.findAll().stream().map(e->new UserResponseDTO(e.getUserId(), e.getName(), e.getEmail(), e.getLastLoginTime())).collect(Collectors.toList());
	}

	@Override
	public List<UserResponseDTO> getUserByName(String userName) {
		List<User> users = userRepository.findByName(userName);
		return users.stream().map(e->new UserResponseDTO(e.getUserId(), e.getName(), e.getName(), e.getLastLoginTime())).collect(Collectors.toList());
	}

	@Override
	public boolean createUser(UserCreationDTO user) throws UserCreationException{
		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setCreationTime(LocalDateTime.now());
		userRepository.save(newUser);
		return true;
	}

	@Override
	public UserResponseDTO updateUser(int userID, UserCreationDTO user) throws UserNotFoundException{
		Optional<User> oUser = userRepository.findById(userID);
		User oldUser = null;
		if(oUser.isPresent()) {
			oldUser = oUser.get();
			oldUser.setName(user.getName());
			oldUser.setEmail(user.getEmail());
			oldUser = userRepository.save(oldUser);
		}
		else {
			throw new UserNotFoundException("User cannot be updated with ID " + userID);
		}
		return new UserResponseDTO(oldUser.getUserId(), oldUser.getName(), oldUser.getEmail(), oldUser.getLastLoginTime());
	}

	@Override
	public boolean deleteUser(int userID) throws UserNotFoundException{
		Optional<User> oUser = userRepository.findById(userID);
		if(oUser.isPresent()) {
			User oldUser = oUser.get();
			userRepository.delete(oldUser);
		}
		else {
			throw new UserNotFoundException("User cannot be deleted with ID " + userID);
		}
		return true;
	}
	
}
