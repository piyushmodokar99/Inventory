package com.inventory.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class InvUtil 
{
	public static void main(String[] args) 
	{
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		bCryptPasswordEncoder.encode("A");
		
		System.out.println("piyush : " + bCryptPasswordEncoder.encode("piyush"));
		System.out.println("editor : " + bCryptPasswordEncoder.encode("editor"));
		System.out.println("admin  : " + bCryptPasswordEncoder.encode("admin"));
	}
}
