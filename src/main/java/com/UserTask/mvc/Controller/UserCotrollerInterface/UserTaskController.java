package com.UserTask.mvc.Controller.UserCotrollerInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.UserTask.mvc.Entities.UserTask;

public interface UserTaskController {
	ResponseEntity<UserTask> addTask(UserTask userTask);

	ResponseEntity<List<UserTask>> getCurrentTask(int id);

}
