package com.atos.coderank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEnctrypTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String pw = "eqalizer0";
		String encoded = pe.encode(pw);
		System.out.println(encoded);
		System.out.println(pe.matches(pw, "$2a$10$vgLKRJutjxiU5C4cacHmGOarMuDZNEnjvjMDMgH1BD3oeSuprRbAi"));
		//$2a$12$zCfTFQD0hxr6j/TLvL1i.uUj03b2z.Vr9oYH4XS6iMFmAPTECxPaW
		//$2a$12$mALXvW6jNRBIYii2hSTXLux7q8445FrcGFafrhW/ZgUYdJPlRA9aO

	}

}
