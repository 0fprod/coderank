package com.atos.coderank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.RankingEntity;
import com.atos.coderank.repositories.RankingRepository;

@Service("rankingService")
public class RankingService {

	@Autowired
	@Qualifier("rankingRepository")
	private RankingRepository rr;

	public RankingEntity saveOrUpdate(RankingEntity rank) {
		return this.rr.saveAndFlush(rank);
	}

	public void updateRanking(ProjectEntity project) {
		//TODO GetTop10 and insert;
		RankingEntity re = project.getRanking();
		re.setProject(project);
		re.setPosition(0);
		
		this.saveOrUpdate(re);
	}
}
