package com.inventory.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil 
{
	private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);
	private String SECRET_KEY = "12345";
	
	public String extractUsername(String token) throws Exception
	{
		log.info("Extracting username from token : " + token);
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token)
	{
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) 
	{
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) 
	{
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	public boolean isTokenExpired(String token)
	{
		log.info("Checking Expiry of Token... ");
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(UserDetails userDetails, String userRole)
	{
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("role", userRole);
		log.info("Generating new token..");
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String username) 
	{
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		
	}
	
	public boolean validateToken(String token, UserDetails userDetails) throws Exception
	{
		final String username = extractUsername(token);
		log.info("Validating token... ");
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
}
