package com.atos.coderank.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.repositories.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository ur;

	@Autowired
	@Qualifier("roleService")
	private RoleService rs;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserEntity findByDas(String das) {
		UserEntity user = ur.findByDasIgnoreCase(das);
		return user;
	}

	public UserEntity saveOrUpdate(UserEntity user) {
		UserEntity ue = this.ur.findByDasIgnoreCase(user.getDas());

		if (null == ue) { // Es un usuario nuevo
			// NotNull info
			ue = new UserEntity();
			ue.setDas(user.getDas());
			ue.setName(user.getName());
			ue.setEmail(user.getEmail());
			// DefaultInfo
			ue.setRole(this.rs.findByName("ROLE_DEV"));
			ue.setPassword(this.passwordEncoder.encode("eqalizer0"));
			ue.setPhoto(user.getPhoto()); // TODO Default image
			ue.setCreatedDate(new Date());
			ue.setEnabled(false);
			ue.setLocked(false);
		} else {
			// Editar usuario
			ue.setPassword(user.getPassword() == null ? ue.getPassword() : user.getPassword());
			ue.setName(user.getName() == null ? ue.getName() : user.getName());
			ue.setEmail(user.getEmail() == null ? ue.getEmail() : user.getEmail());
			ue.setPhoto(user.getPhoto() == null ? ue.getPhoto() : user.getPhoto());
			ue.setCreatedDate(user.getCreatedDate() == null ? ue.getCreatedDate() : user.getCreatedDate());
			ue.setLockedDate(user.getLockedDate() == null ? ue.getLockedDate() : user.getLockedDate());
			ue.setEnabled(user.isEnabled() == null ? ue.isEnabled() : user.isEnabled());
			ue.setLocked(user.isLocked() == null ? ue.isLocked() : user.isLocked());
			ue.setRole(user.getRole() == null ? ue.getRole() : user.getRole());
		}

		return this.ur.saveAndFlush(ue);
	}

	public List<UserEntity> findAll() {
		return this.ur.findAll();
	}

}
