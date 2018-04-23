package com.atos.coderank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.components.JsonSerializers;
import com.atos.coderank.entities.GroupEntity;
import com.atos.coderank.services.GroupService;

@RestController
@RequestMapping("api/private/groups")
public class GroupController {

	@Autowired
	@Qualifier("groupService")
	private GroupService gs;

	@Autowired
	@Qualifier("jsonSerializers")
	private JsonSerializers js;

	@GetMapping("")
	public ResponseEntity<List<GroupEntity>> findAll() {
		List<GroupEntity> list = this.gs.findAll();

		for (int i = 0; i < list.size(); i++)
			list.get(i).setSerializedProject(this.js.projectEntitySerializer(list.get(i)));

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{groupid}")
	public ResponseEntity<GroupEntity> findByGroupId(@PathVariable String groupid) {
		Long id = Long.parseLong(groupid);
		GroupEntity group = this.gs.findByGroupId(id);
		HttpStatus status = HttpStatus.OK;

		if (null == group)
			status = HttpStatus.NOT_FOUND;
		else
			group.setSerializedProject(this.js.projectEntitySerializer(group));

		return new ResponseEntity<>(group, status);
	}

	@PostMapping("/save")
	public ResponseEntity<GroupEntity> saveOrUpdate(@RequestBody GroupEntity group) {
		GroupEntity gm = this.gs.saveOrUpdate(group);
		HttpStatus status = HttpStatus.OK;
		
		if (null == gm)
			status = HttpStatus.CONFLICT;
		else {
			gm.setSerializedProject(this.js.projectEntitySerializer(group));
		}

		return new ResponseEntity<>(gm, status);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody GroupEntity group) {
		String response = "The group with id '" + group.getGroupId() + "' has been deleted.";
		HttpStatus status = HttpStatus.OK;
		GroupEntity groupToDelete = this.gs.findByGroupId(group.getGroupId());

		if (null == groupToDelete) {
			response = "Cannot delete the group with id '" + group.getGroupId() + "'. It doesnt exist.";
			status = HttpStatus.NOT_FOUND;
		} else {
			this.gs.delete(groupToDelete);
		}

		return new ResponseEntity<>(response, status);
	}

}
