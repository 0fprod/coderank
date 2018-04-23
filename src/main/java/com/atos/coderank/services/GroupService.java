package com.atos.coderank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.repositories.GroupRepository;
import com.atos.coderank.repositories.ProjectRepository;

@Service("groupService")
public class GroupService {

	@Autowired
	@Qualifier("groupRepository")
	private GroupRepository gr;
	
	@Autowired
	@Qualifier("projectRepository")
	private ProjectRepository pr;

	public GroupEntity saveOrUpdate(GroupEntity group) {
		GroupEntity ge = this.gr.findByGroupId(group.getGroupId());

		if (null == ge) {
			// New group
			ge = new GroupEntity();
			ge.setName(group.getName().replaceAll(" ", "-").toLowerCase());
			ge.setDescription(group.getDescription());

		} else {
			// Edit group
			ge.setName(group.getName() == null ? ge.getName() : group.getName());
			ge.setDescription(group.getDescription() == null ? ge.getDescription() : group.getDescription());
			ge.setUsers(group.getUsers() == null ? ge.getUsers() : group.getUsers());						 
		}
		
		if (group.getSerializedProject().get("projectKey") != null) {
			ProjectEntity project = this.pr.findByProjectId(group.getSerializedProject().get("projectKey").toString());
			ge.setProject(project == null ? ge.getProject() : project);
			//project.setGroup(ge);
		}

		return this.gr.saveAndFlush(ge);
	}

	public List<GroupEntity> findAll() {
		return this.gr.findAll();
	}

	public GroupEntity findByGroupId(Long id) {
		return this.gr.findByGroupId(id);
	}

	public GroupEntity findByName(String name) {
		return this.gr.findByName(name);

	}

	public void delete(GroupEntity entity) {
		this.gr.delete(entity);
	}
}
