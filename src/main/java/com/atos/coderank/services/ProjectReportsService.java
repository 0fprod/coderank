package com.atos.coderank.services;

import java.util.List;

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

	public List<ProjectReportsEntity> findAll() {
		return this.prr.findAll();
	}
	
	public List<ProjectReportsEntity> findAllBySQMDas(String das) {
		return this.prr.findAllBySQMDas(das);
	}

	public ProjectReportsEntity findById(Long id) {
		return this.prr.findByReportId(id);
	}
}
