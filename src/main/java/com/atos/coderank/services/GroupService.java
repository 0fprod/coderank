package com.atos.coderank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.components.GroupConverter;
import com.atos.coderank.components.UserConverter;
import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.models.GroupModel;
import com.atos.coderank.models.UserModel;
import com.atos.coderank.repositories.GroupRepository;

@Service("groupService")
public class GroupService {

	@Autowired
	@Qualifier("groupRepository")
	private GroupRepository gr;
	
	@Autowired
	@Qualifier("groupConverter")
	private GroupConverter gc;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter uc;
	
	
	public GroupModel saveOrUpdate(GroupModel group) {
		GroupEntity ge = this.gr.findByGroupId(group.getGroupId());
		
		if(null == ge) {
			//Grupo nuevo
			ge = new GroupEntity();
			ge.setName(group.getName());
			ge.setDescription(group.getDescription());
			//TODO projectConverter
			//ge.setProject(group.getProject() == null ? null : this.pc.model2entity(group, false));			
			ge = this.gr.saveAndFlush(ge);
		} else {
			//Editar grupo
			ge.setName(group.getName() == null ? ge.getName() : group.getName());
			ge.setDescription(group.getDescription() == null ? ge.getDescription() : group.getDescription());
			//Add users
			for(UserModel user : group.getUsers())
				ge.addUser(this.uc.model2entity(user, false));
			
			ge = this.gr.saveAndFlush(ge);
		}
		
		return this.gc.entity2model(ge, false);
	}


	public List<GroupModel> findAll() {
		List<GroupEntity> groups = this.gr.findAll();		
		return this.gc.entityList2modelList(groups, true);
	}


	public GroupModel findByGroupId(Long id) {
		return this.gc.entity2model(this.gr.findByGroupId(id), true);
	}
	
	public GroupModel findByName(String name) {
		return this.gc.entity2model(this.gr.findByName(name), true);

	}
}
