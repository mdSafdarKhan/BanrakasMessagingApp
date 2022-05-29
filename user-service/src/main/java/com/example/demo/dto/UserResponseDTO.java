package com.example.demo.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {

	private int userId;
	private String name;
	private String email;
	private LocalDateTime lastLoginTime;

	public UserResponseDTO() {
		super();
	}

	public UserResponseDTO(String name, String email, LocalDateTime lastLoginTime) {
		super();
		this.name = name;
		this.email = email;
		this.lastLoginTime = lastLoginTime;
	}

	public UserResponseDTO(int userID, String name, String email, LocalDateTime lastLoginTime) {
		super();
		this.userId = userID;
		this.name = name;
		this.email = email;
		this.lastLoginTime = lastLoginTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}
