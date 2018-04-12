package com.atos.coderank.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.atos.coderank.entities.BadgesEntity;
import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.UserEntity;

@Component("jsonSerializers")
public class JsonSerializers {

	/**
	 * Serialize GroupEntity
	 * 
	 * @param user
	 * @return
	 */
	public List<Map<String, String>> groupEntitySerializer(UserEntity user) {
		List<Map<String, String>> list = new ArrayList<>();

		if (user.getGroups().size() > 0)
			for (GroupEntity g : user.getGroups()) {
				Map<String, String> map = new HashMap<>();
				map.put("id", g.getGroupId().toString());
				map.put("name", g.getName());
				list.add(map);
			}

		return list;
	}

	/**
	 * Serializes ProjectEntity
	 * 
	 * @param g
	 * @return
	 */
	public Map<String, String> projectEntitySerializer(GroupEntity g) {
		Map<String, String> map = new HashMap<>();

		if (g.getProject() != null) {
			map.put("id", g.getProject().getProjectId());
			map.put("key", g.getProject().getKey());
			map.put("url", g.getProject().getUrl());
			map.put("name", g.getProject().getName());
		}

		return map;
	}

	/**
	 * 
	 * @param badges
	 * @return
	 */
	public List<Map<String, Object>> badgeListSerializer(UserEntity user) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<BadgesEntity> badges = user.getBadges();

		if(null != badges)
			for (BadgesEntity b : badges) {
				Map<String, Object> map = new HashMap<>();
				map.put("badgeId", b.getBadgeId());
				map.put("name", b.getName());
				map.put("img", b.getImage());
				list.add(map);				
			}

		return list;
	}

}
