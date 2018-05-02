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

	/**
	 * Get the top 10 projects and insert the given on in order
	 * @param project
	 */
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

		if (currentRanking.isEmpty()) {
			re.setPosition(1);
			re.setProject(project);
			this.saveOrUpdate(re);
		} else {

			for (int i = 0; i < currentRanking.size(); i++) {
				if (re.getCalification() > currentRanking.get(i).getCalification()) {
					re.setPosition(i + 1);	
					re.setProject(project);
					this.saveOrUpdate(re);

					for (int k = i; k < currentRanking.size(); k++) {
						// TODO send an email to the SQM from currentRanking.getProject() saying that his position has changed
						// TODO What happens if the position exceeds the top10
						currentRanking.get(k).setPosition(currentRanking.get(k).getPosition() + 1);
						this.saveOrUpdate(currentRanking.get(k));
					}

					break;
				}
			}

		}

	}

	public void deleteByProjectId(ProjectEntity project) {
		this.rr.deleteById(project.getProjectId());
	}

}
