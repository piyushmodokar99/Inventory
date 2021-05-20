package com.inventory.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inventory.entities.InvUsers;
import com.inventory.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository; 
	
	private Integer uid;
	private String userName;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		InvUsers user = userRepository.getInvUsersByIuUsername(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("Could not found username.");
		}
		//System.out.println("User : " + user.getIuUId());
		//new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities)
		//return new User("admin", "piyush", new ArrayList<>());
		return new MyUserDetails(user, new MyUserDetailsService(user.getIuUId(), user.getIuUsername()));
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MyUserDetailsService(Integer uid, String userName) {
		super();
		this.uid = uid;
		this.userName = userName;
	}

	public MyUserDetailsService() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
