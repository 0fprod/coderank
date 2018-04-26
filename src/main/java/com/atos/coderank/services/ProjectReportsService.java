package com.atos.coderank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.entities.ProjectReportsEntity;
import com.atos.coderank.repositories.ProjectReportsRepository;

@Service("projectReportsService")
public class ProjectReportsService {

	@Autowired
	@Qualifier("projectReportsRepository")
	private ProjectReportsRepository prr;
	
	public ProjectReportsEntity saveOrUpdate(ProjectReportsEntity pre) {
		return this.prr.saveAndFlush(pre);
	}
}
