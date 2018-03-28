package com.atos.coderank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.repositories.BadgesRepository;

@Service("badgesService")
public class BadgesService {

	@Autowired
	@Qualifier("badgesRepository")
	private BadgesRepository br;
	
	
}
