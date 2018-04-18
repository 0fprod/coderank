package com.atos.coderank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atos.coderank.entities.RoleEntity;
import com.atos.coderank.repositories.RoleRepository;

@Service("roleService")
public class RoleService {

	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository rr;
		
	public RoleEntity findById(Long roleId) {
		return this.rr.findByRoleId(roleId);
	}
	
	public List<RoleEntity> findAll(){		
		return this.rr.findAll();
	}

	public RoleEntity findByName(String name) {		
		return this.rr.findByName(name);
	}
	
	public RoleEntity saveOrUpdate(RoleEntity role) {
		RoleEntity re = this.rr.findByRoleId(role.getRoleId());
		if(null == re) {
			re = new RoleEntity();
			re.setName(role.getName());			
		} else {
			re.setName(role.getName());			
		}
		return this.rr.saveAndFlush(re);
	}
}
