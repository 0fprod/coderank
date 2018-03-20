package com.atos.coderank.component;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.coderank.entity.Group;
import com.atos.coderank.entity.User;
import com.atos.coderank.model.GroupModel;
import com.atos.coderank.model.UserModel;

@Component("userConverter")
public class UserConverter {

	@Autowired
	private GroupConverter gc;
	
	/*public UserModel entity2model(User user) {
		Set<GroupModel> gm = new HashSet<>();
		
		for(Group group : user.getGroup()) 
			gm.add(gc.entity2model(group));
		
		return new UserModel(user.getDas(), user.getPassword(), user.getEmail(), user.getName(), user.getLastname(), gm );
	}
	
	public User model2entity(UserModel user) {
		Set<Group> g = new HashSet<>();
		
		for(GroupModel gm : user.getGroup()) 
			g.add(gc.model2entity(gm));
		
		return new User(user.getDas(), user.getPassword(), user.getEmail(), user.getName(), user.getLastname(), g);
	}*/
}
