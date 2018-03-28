package com.atos.coderank.components;

import org.springframework.stereotype.Component;

import com.atos.coderank.entities.RoleEntity;
import com.atos.coderank.models.RoleModel;

@Component("roleConverter")
public class RoleConverter {

	public RoleEntity model2entity(RoleModel role) {
		RoleEntity re = new RoleEntity();
		re.setRoleId(role.getRoleId());
		re.setName(role.getName());
		return re;
	}
	
	public RoleModel entity2model(RoleEntity role) {
		RoleModel re = new RoleModel();
		re.setRoleId(role.getRoleId());
		re.setName(role.getName());
		return re;
	}
}
