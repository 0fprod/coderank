package com.atos.coderank.beans;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestComponent
public class PasswordEncodingTest {

	@Test
	public void encodePassword() {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String pw = "eqalizer0";
		assertTrue(pe.matches(pw, pe.encode(pw)));
	}

}
