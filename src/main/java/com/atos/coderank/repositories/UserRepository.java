package com.atos.coderank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.RoleEntity;
import com.atos.coderank.entities.UserEntity;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, String>{

	abstract UserEntity findByDasIgnoreCase(String das);
	
	abstract UserEntity findByDasAndPassword(String das, String password);
	
	abstract List<UserEntity> findByLocked(boolean locked);
	
	abstract List<UserEntity> findAll();
	
	abstract List<UserEntity> findByRole(RoleEntity role);
	
}
