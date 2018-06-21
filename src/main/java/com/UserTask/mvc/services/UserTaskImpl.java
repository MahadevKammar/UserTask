package com.UserTask.mvc.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserTask.mvc.Entities.UserTask;
import com.UserTask.mvc.dao.UserTaskDao;
import com.UserTask.mvc.services.serivceInterfaces.UserTaskService;

@Service
public class UserTaskImpl implements UserTaskService {
	@Autowired
	private UserTaskDao userTaskDao;

	@Override
	public UserTask createTask(UserTask userTask) {
		
		try {
			return userTaskDao.save(userTask);
		} catch (Exception e) {
			System.out.println("Failed to create task");
			return null;
		}
	}

	@Override
	public List<UserTask> getCurrentDayTask(int userId) {
		System.out.println(getCurrentDateandTime());
		List<UserTask> list = userTaskDao.findByPresentDate(getCurrentDateandTime()).stream()
				.filter((ut) -> ut.getUser().getUserId() == userId).collect(Collectors.toList());
		return list;
	}

	public String getCurrentDateandTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public  List<UserTask> getAllData(){
		ArrayList<UserTask> al=new ArrayList<UserTask>();
		al=(ArrayList<UserTask>) userTaskDao.findAll();
/*//		for(UserTask ut:userTaskDao.findAll()){
//			System.out.println(ut);
//			al.add(ut);
//		}
*/		return al;
	}

}
