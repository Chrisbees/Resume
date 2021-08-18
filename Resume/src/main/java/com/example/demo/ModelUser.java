package com.example.demo;

import com.sun.istack.NotNull;

import lombok.Data;

//@Entity
@Data
public class ModelUser {
	
	@NotNull
	private String name;
	@NotNull
	private String email;
	
	private String feedback;
	
	
	
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
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
}
