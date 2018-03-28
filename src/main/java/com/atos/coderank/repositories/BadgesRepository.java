package com.atos.coderank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.BadgesEntity;

@Repository("badgesRepository")
public interface BadgesRepository extends JpaRepository<BadgesEntity, Long>{

	abstract BadgesEntity findByBadgeId(Long id);
	
	abstract BadgesEntity findByName(String name);
	
	abstract List<BadgesEntity> findByBadgeIdOrderByBadgeIdAsc(Long id);
	
	
}
