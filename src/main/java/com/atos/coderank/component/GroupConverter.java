package com.atos.coderank.component;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.coderank.entity.Group;
import com.atos.coderank.entity.User;
import com.atos.coderank.model.GroupModel;
import com.atos.coderank.model.UserModel;

@Component("groupConverter")
public class GroupConverter {
	
	@Autowired
	private UserConverter uc;

	/*public GroupModel entity2model(Group group) {
		Set<UserModel> um = new HashSet<>();
		
		for(User user : group.getUser()) 
			um.add(uc.entity2model(user));
			
		return new GroupModel(group.getId(), group.getName(), group.getDescription(), um);
	}
	
	public Group model2entity(GroupModel group) {
		Set<User> g = new HashSet<>();
		
		for(UserModel um : group.getUser())
			g.add(uc.model2entity(um));
		
		return new Group(group.getId(), group.getName(), group.getDescription(), g);
	}*/
}

