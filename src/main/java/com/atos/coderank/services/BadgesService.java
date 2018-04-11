package com.atos.coderank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.entities.BadgesEntity;
import com.atos.coderank.repositories.BadgesRepository;

@Service("badgesService")
public class BadgesService {

	@Autowired
	@Qualifier("badgesRepository")
	private BadgesRepository br;
	
	public List<BadgesEntity> findAll(){
		return this.br.findAll();
	}
	
	public BadgesEntity findById(Long id) {
		return this.br.findByBadgeId(id);
	}
	
	public BadgesEntity findByName(String name) {
		return this.br.findByName(name);
	}
	
	public BadgesEntity saveOrUpdate(BadgesEntity badge) {
		return this.br.saveAndFlush(badge);
	}
}
