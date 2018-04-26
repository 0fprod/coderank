package com.atos.coderank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.RankingEntity;

@Repository("rankingRepository")
public interface RankingRepository extends JpaRepository<RankingEntity, String>{

	abstract void deleteByProjectId(String projectId);

	abstract List<RankingEntity> findTop10ByOrderByCalificationDesc();
}
