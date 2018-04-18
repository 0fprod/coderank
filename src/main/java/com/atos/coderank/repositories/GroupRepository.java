package com.atos.coderank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.coderank.entities.GroupEntity;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<GroupEntity, Long>{

	abstract GroupEntity findByGroupId(Long groupId);
	
	abstract List<GroupEntity> findAll();
	
	abstract GroupEntity findByName(String name);
	
}
