package com.UserTask.mvc.services.serivceInterfaces;

import java.util.List;

import com.UserTask.mvc.Entities.UserTask;

public interface UserTaskService {
	UserTask createTask(UserTask userTask);

	List<UserTask> getCurrentDayTask(int taskId);

}
