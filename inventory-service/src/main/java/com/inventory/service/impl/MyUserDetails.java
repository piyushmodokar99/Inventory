package com.inventory.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.inventory.entities.InvRoles;
import com.inventory.entities.InvUsers;

public class MyUserDetails implements UserDetails 
{
	private InvUsers invUsers;
	
	private Integer uid;
	
	
	public MyUserDetails(InvUsers invUsers, MyUserDetailsService myUserDetailsService) {
		this.invUsers = invUsers;
		this.uid = myUserDetailsService.getUid();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		List<InvRoles> invRoles = invUsers.getInvRoles();
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		for(InvRoles roles: invRoles)
		{
			authorities.add(new SimpleGrantedAuthority(roles.getIrName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() 
	{
		return invUsers.getIuPassword();
	}

	@Override
	public String getUsername() {
		return invUsers.getIuUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Need to add data from InvUsers
		return (invUsers.getIuIsEnabled() == 1 ? true : false );
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	
}
