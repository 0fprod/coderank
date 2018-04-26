package com.atos.coderank.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.atos.coderank.entities.BadgesEntity;
import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.UserEntity;

@Component("jsonSerializers")
public class JsonSerializers {

	/**
	 * Serialize GroupEntity from a given UserEntity
	 * 
	 * @param user
	 * @return List(Map(id, name))
	 */
	public List<Map<String, String>> groupEntitySerializer(UserEntity user) {
		List<Map<String, String>> list = new ArrayList<>();

		if (!user.getGroups().isEmpty())
			for (GroupEntity g : user.getGroups()) {
				Map<String, String> map = new HashMap<>();
				map.put("id", g.getGroupId().toString());
				map.put("name", g.getName());
				list.add(map);
			}

		return list;
	}

	/**
	 * Serializes ProjectEntity from a given GroupEntity
	 * 
	 * @param g
	 * @return Map(id, key, url, name)
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
	 * Serializes a BadgeEntity from a given UserEntity
	 * 
	 * @param badges
	 * @return List(Map(badgeId, name, img))
	 */
	public List<Map<String, Object>> badgeListSerializer(UserEntity user) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<BadgesEntity> badges = user.getBadges();

		if (null != badges)
			for (BadgesEntity b : badges) {
				Map<String, Object> map = new HashMap<>();
				map.put("badgeId", b.getBadgeId());
				map.put("name", b.getName());
				map.put("img", b.getImage());
				list.add(map);
			}

		return list;
	}

	/**
	 * Serializes a BadgeEntity from a given ProjectEntity
	 * 
	 * @param badges
	 * @return List(Map(badgeId, name, img))
	 */
	public List<Map<String, Object>> badgeListSerializer(ProjectEntity project) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<BadgesEntity> badges = project.getBadges();

		if (null != badges)
			for (BadgesEntity b : badges) {
				Map<String, Object> map = new HashMap<>();
				map.put("badgeId", b.getBadgeId());
				map.put("name", b.getName());
				map.put("img", b.getImage());
				list.add(map);
			}

		return list;
	}

	/**
	 * Serializes a GroupEntity from a given ProjectEntity
	 * 
	 * @param project
	 * @return Map(id, name, description)
	 */
	public Map<String, String> groupEntitySerializer(ProjectEntity project) {
		Map<String, String> map = new HashMap<>();

		if (project.getGroup() != null) {
			GroupEntity group = project.getGroup();
			map.put("id", group.getGroupId().toString());
			map.put("name", group.getName());
			map.put("description", group.getDescription());
		}

		return map;
	}
}
