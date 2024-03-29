package com.facingahead.create_user;

import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation .Id;

public class CreateUserDTO {
	@Id
	private String id;
	private String username;
	private String password;
	private String name;
	
	public CreateUserDTO(){
		// blank constructor
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
