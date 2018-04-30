package com.atos.coderank;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class CoderankApplication {

	private static final String BASE_PATH = "./files/";
	
	public static void main(String[] args) {
		File dir = new File(BASE_PATH);
		
		if(!dir.exists())
			dir.mkdirs();
		
		SpringApplication.run(CoderankApplication.class, args);
	}
	
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
