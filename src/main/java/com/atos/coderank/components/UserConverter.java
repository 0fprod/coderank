package com.atos.coderank.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.models.UserModel;

@Component("userConverter")
public class UserConverter {

	@Autowired
	@Qualifier("roleConverter")
	private RoleConverter rc;

	@Autowired
	@Qualifier("groupConverter")
	private GroupConverter gc;

	public UserEntity model2entity(UserModel user, boolean include_groups) {
		UserEntity ue = new UserEntity();

		ue.setDas(user.getDas());
		ue.setName(user.getName());
		ue.setEmail(user.getEmail());
		ue.setPassword(user.getPassword());
		ue.setPhoto(user.getPhoto());
		ue.setCreatedDate(user.getCreatedDate());
		ue.setLockedDate(user.getLockedDate());
		ue.setEnabled(user.getEnabled());
		ue.setLocked(user.getLocked());
		ue.setGroups((include_groups) ? this.gc.modelList2entityList(user.getGroups(), include_groups) : null);
		ue.setRole(rc.model2entity(user.getRole()));
		// TODO badges

		return ue;
	}

	public UserModel entity2model(UserEntity user, boolean include_groups) {
		UserModel um = new UserModel();

		um.setDas(user.getDas());
		um.setName(user.getName());
		um.setEmail(user.getEmail());
		um.setPassword(user.getPassword());
		um.setPhoto(user.getPhoto());
		um.setCreatedDate(user.getCreatedDate());
		um.setLockedDate(user.getLockedDate());
		um.setEnabled(user.isEnabled());
		um.setLocked(user.isLocked());
		um.setGroups((include_groups) ? this.gc.entityList2modelList(user.getGroups(), include_groups) : null);
		um.setRole(rc.entity2model(user.getRole()));
		// TODO badgesConverter

		return um;
	}

	/**
	 * Convierte una lista de userModel a userEntity
	 * @param users
	 * @param include_groups
	 * @return
	 */
	public List<UserEntity> modelList2entityList(List<UserModel> users, boolean include_groups) {
		List<UserEntity> list = new ArrayList<>();

		for (UserModel user : users)
			list.add(model2entity(user, !include_groups));

		return list;
	}

	/**
	 * Convierte una lista de userEntity a userModel
	 * @param users
	 * @param include_groups
	 * @return
	 */
	public List<UserModel> entityList2modelList(List<UserEntity> users, boolean include_groups) {
		List<UserModel> list = new ArrayList<>();

		for (UserEntity user : users)
			list.add(entity2model(user, !include_groups));

		return list;
	}

}
