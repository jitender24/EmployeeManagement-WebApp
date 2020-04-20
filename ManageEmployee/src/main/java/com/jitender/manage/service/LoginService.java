package com.jitender.manage.service;


import org.springframework.stereotype.Service;



@Service
public class LoginService  {

	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("jitender") && password.equals("12345");
	}
	

}
