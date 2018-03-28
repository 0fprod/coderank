package com.atos.coderank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.RoleEntity;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	abstract RoleEntity findByRoleId(Long role_id);
	
	abstract List<RoleEntity> findAll();

	abstract RoleEntity findByName(String name);
}
