package com.atos.coderank.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.atos.coderank.components.RoleConverter;
import com.atos.coderank.components.UserConverter;
import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.models.UserModel;
import com.atos.coderank.repositories.UserRepository;

@Service("userService")
public class UserService{

	@Autowired
	@Qualifier("userRepository")
	private UserRepository ur;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter uc;
	
	@Autowired
	@Qualifier("roleConverter")
	private RoleConverter rc;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		
	public UserModel findByDas(String das) {
		UserEntity user = ur.findByDasIgnoreCase(das);
		return this.uc.entity2model(user, true);
	}

	public UserModel saveOrUpdate(UserModel user) {
		UserEntity ue = this.ur.findByDasIgnoreCase(user.getDas());
		UserModel updatedUser = new UserModel();
		
		if(null == ue) { //Es un usuario nuevo
			//Valores por defecto
			ue = this.uc.model2entity(user, false);
			ue.setPassword(this.passwordEncoder.encode("eqalizer0"));
			ue.setPhoto(user.getPhoto()); //TODO Default image
			ue.setCreatedDate(new Date());
			ue.setEnabled(false);
			ue.setLocked(false);			
		} else {
			//Editar usuario
			ue.setPassword(user.getPassword() == null ? ue.getPassword() : user.getPassword());
			ue.setName(user.getName() == null ? ue.getName() : user.getName());
			ue.setEmail(user.getEmail() == null ? ue.getEmail() : user.getEmail());
			ue.setPhoto(user.getPhoto() == null ? ue.getPhoto() : user.getPhoto());
			ue.setCreatedDate(user.getCreatedDate() == null ? ue.getCreatedDate() : user.getCreatedDate());
			ue.setLockedDate(user.getLockedDate() == null ? ue.getLockedDate() : user.getLockedDate());
			ue.setEnabled(user.getEnabled() == null ? ue.isEnabled() : user.getEnabled());
			ue.setLocked(user.getLocked() == null ? ue.isLocked() : user.getLocked());
			ue.setRole(user.getRole() == null ? ue.getRole() : this.rc.model2entity(user.getRole()));
		}
		
		updatedUser = this.uc.entity2model(this.ur.saveAndFlush(ue), false);

		return updatedUser;
	}

	public List<UserModel> findAll() {
		List<UserEntity> list = this.ur.findAll();				
		return this.uc.entityList2modelList(list, false);
	}




}
