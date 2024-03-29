package com.facingahead.app;

import org.springframework.core.style.ToStringCreator;

public class CreateUserDTO {

	private String username;
	private String password;
	private String name;
	private Long uniqueCode;
	private String partnerUsername;
	private String email;

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

	public Long getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(Long uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public String getPartnerUsername() {
		return partnerUsername;
	}

	public void setPartnerUsername(String partnerUsername) {
		this.partnerUsername = partnerUsername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString(){
		ToStringCreator tsc = new ToStringCreator(this);
		return tsc.append("uniqueCode", this.uniqueCode)
				.append("username", this.username)
				.append("password", this.password)
				.append("name", this.name)
				.toString();
	}

}
