package com.atos.coderank.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atos.coderank.components.RoleConverter;
import com.atos.coderank.components.UserConverter;
import com.atos.coderank.entities.RoleEntity;
import com.atos.coderank.entities.UserEntity;
import com.atos.coderank.models.UserModel;
import com.atos.coderank.repositories.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService{

	@Autowired
	@Qualifier("userRepository")
	private UserRepository ur;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter uc;
	
	@Autowired
	@Qualifier("roleConverter")
	private RoleConverter rc;
	
	@Override
	public UserDetails loadUserByUsername(String das) throws UsernameNotFoundException {
		UserEntity ue = this.ur.findByDasIgnoreCase(das);
		List<GrantedAuthority> auths = buildAuthorities(ue.getRole());
		return buildUser(ue, auths);
	}
	
	
	private User buildUser(UserEntity ue, List<GrantedAuthority> authorities) {		
		return new User(ue.getDas(), ue.getPassword(), ue.isEnabled(), true, true, !ue.isLocked(), authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities(RoleEntity role){
		List<GrantedAuthority> auths = new ArrayList<>();	
		auths.add(new SimpleGrantedAuthority(role.getName()));		
		return auths;
	}
	
	public UserModel findByDas(String das) {
		UserEntity user = ur.findByDasIgnoreCase(das);
		return this.uc.entity2model(user, true);
	}

	public UserModel saveOrUpdate(UserModel user) {
		UserEntity ue = this.ur.findByDasIgnoreCase(user.getDas());
		
		if(null == ue) { //Es un usuario nuevo
			//Valores por defecto
			ue = new UserEntity();
			ue.setPassword("$2a$10$SFpgpUZ/0z6V5xUOgHxqqe58WOekIW0IqX2eqXxxo7h6r9XJuJRZS");
			ue.setName(user.getName());
			ue.setEmail(user.getEmail());
			ue.setPhoto(user.getPhoto());
			ue.setCreatedDate(new Date());
			ue.setEnabled(false);
			ue.setLocked(false);
			ue.setRole(this.rc.model2entity(user.getRole())); //El role no puede ser null
			ue = this.ur.saveAndFlush(this.uc.model2entity(user, false));
		} else {
			//Editar usuario
			ue.setPassword(user.getPassword() == null ? ue.getPassword() : user.getPassword());
			ue.setName(user.getName() == null ? ue.getName() : user.getName());
			ue.setEmail(user.getEmail() == null ? ue.getEmail() : user.getEmail());
			ue.setPhoto(user.getPhoto() == null ? ue.getPhoto() : user.getPhoto());
			ue.setCreatedDate(user.getCreatedDate() == null ? ue.getCreatedDate() : user.getCreatedDate());
			ue.setLockedDate(user.getLockedDate() == null ? ue.getLockedDate() : user.getLockedDate());
			ue.setEnabled(user.getEnabled() == null ? ue.isEnabled() : user.isEnabled());
			ue.setLocked(user.isLocked() == null ? ue.isLocked() : user.isLocked());
			ue.setRole(user.getRole() == null ? ue.getRole() : this.rc.model2entity(user.getRole()));
			ue = this.ur.saveAndFlush(ue);
		}
		
		UserModel um = this.uc.entity2model(ue, false);
		
		return um;
	}

	public List<UserModel> findAll() {
		List<UserEntity> list = this.ur.findAll();				
		return this.uc.entityList2modelList(list, false);
	}




}
