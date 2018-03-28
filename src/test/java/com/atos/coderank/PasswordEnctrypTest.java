package com.atos.coderank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEnctrypTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("eqalizer0"));
	}

}
