package com.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.model.AuthRequest;
import com.inventory.model.AuthResponse;
import com.inventory.service.ILoginService;
import com.inventory.service.impl.MyUserDetails;
import com.inventory.service.impl.MyUserDetailsService;
import com.inventory.util.JWTUtil;

@RestController
@CrossOrigin
public class LoginController 
{
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;  
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JWTUtil jWTUtil;
	
	@Autowired
	ILoginService loginService;
	
	@RequestMapping({"/hello"})
	public String hello()
	{
		MyUserDetails userDetailsService = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("UID :: " + userDetailsService.getUid());
		return "Welcome Piyush";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) throws Exception
	{
		String userRole = null;
		try 
		{
			log.info("Inside Login : Username " + authRequest.getUsername());
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), 
							authRequest.getPassword()));
			log.info("User loged in as " + auth.getAuthorities());
			for(GrantedAuthority a : auth.getAuthorities())
			{
				System.out.println(a);
				userRole = a.toString();
			}
		} 
		catch (BadCredentialsException e) 
		{
			//e.printStackTrace();
			throw new Exception("Username or Pass is incorrect", e);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());
		String jwt = jWTUtil.generateToken(userDetails, userRole);
		return ResponseEntity.ok(new AuthResponse(1, jwt, "tempSessionId", authRequest.getUsername()));
	}
}
