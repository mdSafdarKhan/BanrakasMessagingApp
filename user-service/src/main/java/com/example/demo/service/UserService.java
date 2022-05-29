package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserCreationDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.error.UserCreationException;
import com.example.demo.error.UserNotFoundException;

public interface UserService {

	
	UserResponseDTO getUserByID(int userId) throws UserNotFoundException;
	UserResponseDTO getUserByEmail(String email) throws UserNotFoundException;
	List<UserResponseDTO> getUsers();
	List<UserResponseDTO> getUserByName(String userName);
	boolean createUser(UserCreationDTO user) throws UserCreationException;
	UserResponseDTO updateUser(int userID, UserCreationDTO user) throws UserNotFoundException;
	boolean deleteUser(int userID) throws UserNotFoundException;
	
}
