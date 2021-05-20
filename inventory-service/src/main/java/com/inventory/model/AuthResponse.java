package com.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content = Include.NON_NULL)
public class AuthResponse 
{
	private Integer status;
	private String token;
	private String sessionId;
	private String username;
	
	
	public AuthResponse() 
	{
		
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public AuthResponse(Integer status, String token, String sessionId, String username) {
		super();
		this.status = status;
		this.token = token;
		this.sessionId = sessionId;
		this.username = username;
		
	}

	

	

}
