package com.inventory.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.inventory.model.InvCommonResponse;
import com.inventory.service.impl.MyUserDetailsService;
import com.inventory.util.JWTUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter 
{
	private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JWTUtil jWTUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		
		log.info("Inside doFilterInternal.. ");
		final String authHeader = request.getHeader("Authorization");
		String jwt = null;
		String username = null;
		
		//response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		//response. Status(HttpStatus.OK);
		/*
		 * response.setHeader("Access-Control-Allow-Origin",
		 * request.getHeader("Origin"));
		 * request.setAttribute("Access-Control-Allow-Origin", "http://localhost:4200");
		 * response.setHeader("Access-Control-Allow-Credentials", "true");
		 * response.setHeader("Access-Control-Allow-Methods",
		 * "POST, GET, OPTIONS, DELETE"); response.setHeader("Access-Control-Max-Age",
		 * "3600"); response.setHeader("Access-Control-Allow-Headers",
		 * "Authorization, Content-Type, Accept, X-Requested-With, remember-me");
		 */
		
		try 
		{
			log.info("Checking Token..." + authHeader);
			if(authHeader != null && authHeader.startsWith("Bearer "))
			{
				jwt = authHeader.substring(7);
				username = jWTUtil.extractUsername(jwt);
				System.out.println("Username JWT : " + username);
			}
			
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
				if(jWTUtil.validateToken(jwt, userDetails))
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			InvCommonResponse commonResponse = new InvCommonResponse();
			System.out.println("doFilterInternal Resp : " + e.getMessage());
			commonResponse.setMsg("Invalid token");
			commonResponse.setStatus(0);
		}
		
	}
	
	
	
}
