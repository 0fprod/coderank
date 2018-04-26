package com.atos.coderank.services;

import java.util.List;

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

	public List<RankingEntity> findTop10ByOrderByCalificationDesc() {
		return this.rr.findTop10ByOrderByCalificationDesc();
	}

	public void insertIntoRanking(ProjectEntity project) {
		List<RankingEntity> currentRanking = this.rr.findTop10ByOrderByCalificationDesc();

		// Remove if the project is contained bye the currentRanking
		for (RankingEntity rank : currentRanking) {
			if (rank.getProject().getProjectId().equalsIgnoreCase(project.getProjectId())) {
				currentRanking.remove(rank);
				break;
			}
		}

		RankingEntity re = project.getRanking();
		boolean inserted = false;
		// Insertion in order
		for (int i = 0; i < currentRanking.size(); i++) {

			if (!inserted && re.getCalification() < currentRanking.get(i).getCalification()) {
				re.setPosition(i);
				inserted = true;
				re.setProject(project);
				this.saveOrUpdate(re);
				continue;
			}

			// Move everyone +1 position
			if (inserted) {
				currentRanking.get(i).setPosition(currentRanking.get(i).getPosition() + 1);
				//TODO send an email to the SQM from currentRanking.getProject() saying that his position has changed
				this.saveOrUpdate(currentRanking.get(i));
			}
		}

	}

	public void deleteByProjectId(ProjectEntity project) {
		this.rr.deleteById(project.getProjectId());
	}

}
