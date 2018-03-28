package com.atos.coderank.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.models.GroupModel;

@Component("groupConverter")
public class GroupConverter {

	@Autowired
	@Qualifier("userConverter")
	private UserConverter uc;

	public GroupEntity model2entity(GroupModel group, boolean include_users) {
		GroupEntity ge = new GroupEntity();

		ge.setGroupId(group.getGroupId());
		ge.setName(group.getName());
		ge.setDescription(group.getDescription());
		ge.setUsers((include_users) ? this.uc.modelList2entityList(group.getUsers(), include_users) : null);

		return ge;
	}

	public GroupModel entity2model(GroupEntity group, boolean include_users) {
		GroupModel gm = new GroupModel();

		gm.setGroupId(group.getGroupId());
		gm.setName(group.getName());
		gm.setDescription(group.getDescription());
		gm.setUsers((include_users) ? this.uc.entityList2modelList(group.getUsers(), include_users) : null);
		// TODO projectConverter

		return gm;
	}

	/**
	 * Convierte una lista de groupModel a groupEntity 
	 * @param groups
	 * @param include_users
	 * @return
	 */
	public List<GroupEntity> modelList2entityList(List<GroupModel> groups, boolean include_users) {
		if (null == groups)
			return null;

		List<GroupEntity> list = new ArrayList<>();

		for (GroupModel group : groups)
			list.add(model2entity(group, !include_users));

		return list;
	}

	/**
	 * Convierte una lista de GroupEntity a GroupModel
	 * @param groups
	 * @param include_users
	 * @return
	 */
	public List<GroupModel> entityList2modelList(List<GroupEntity> groups, boolean include_users) {
		if (null == groups)
			return null;

		List<GroupModel> list = new ArrayList<>();

		for (GroupEntity group : groups)
			list.add(entity2model(group, !include_users));

		return list;
	}
}
