package com.UserTask.mvc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserTask.mvc.Controller.UserCotrollerInterface.UserTaskController;
import com.UserTask.mvc.Entities.UserTask;
import com.UserTask.mvc.services.UserTaskImpl;

@RestController
@RequestMapping("/usertask/")
public class UserTaskControllerImpl implements UserTaskController {
	@Autowired
	private UserTaskImpl userTaskImpl;

	@Override
	@PostMapping("addtask")
	@CrossOrigin
	public ResponseEntity<UserTask> addTask(@RequestBody UserTask userTask) {
		// userTask.getUser().setUserId(UserId);
		System.out.println(userTask.getUser().getUserId());
		UserTask savedUserTask = userTaskImpl.createTask(userTask);
		if (savedUserTask != null) {
			return new ResponseEntity<UserTask>(savedUserTask, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<UserTask>(savedUserTask, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("secured/gettask/{userId}")
	@CrossOrigin
	public ResponseEntity<List<UserTask>> getCurrentTask(@PathVariable("userId") int id) {
		System.out.println("Login page");
		List<UserTask> listUserTask = userTaskImpl.getCurrentDayTask(id);
		if (!listUserTask.isEmpty()) {
			return new ResponseEntity<List<UserTask>>(listUserTask, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<UserTask>>(listUserTask, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("gettingtask")
	@CrossOrigin
	public ResponseEntity<List<UserTask>> gettingtask() {

		return new ResponseEntity<List<UserTask>>(userTaskImpl.getAllData(), HttpStatus.OK);
	}
}
