package com.atos.coderank.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.components.RoleConverter;
import com.atos.coderank.entities.RoleEntity;
import com.atos.coderank.models.RoleModel;
import com.atos.coderank.repositories.RoleRepository;

@Service("roleService")
public class RoleService {

	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository rr;
	
	@Autowired
	@Qualifier("roleConverter")
	private RoleConverter rc;
	
	public RoleModel findById(Long role_id) {
		RoleEntity re = this.rr.findByRoleId(role_id);
		
		if(null == re)
			return null;
		
		RoleModel role = new RoleModel();
		role.setRoleId(re.getRoleId());
		role.setName(re.getName());
		return role;
	}
	
	public List<RoleModel> findAll(){
		List<RoleEntity> rel = this.rr.findAll();
		List<RoleModel> rml = new ArrayList<>();
		for(RoleEntity r : rel)
			rml.add(this.rc.entity2model(r));
		
		return rml;
	}

	public RoleModel findByName(String name) {
		RoleEntity re = this.rr.findByName(name);
		return this.rc.entity2model(re);
	}
	
	public RoleModel saveOrUpdate(RoleModel role) {
		RoleEntity re = this.rc.model2entity(role);		
		return this.rc.entity2model(this.rr.saveAndFlush(re));
	}
}
