package com.UserTask.mvc.Controller.UserCotrollerInterface;

import org.springframework.http.ResponseEntity;

import com.UserTask.mvc.Entities.User;

public interface UserInterace {
	
	ResponseEntity<User> postUser(User user);
	ResponseEntity<User> getUser(String email,String password);
	ResponseEntity<User> getUserObj(int UserId);
    /* default void method(){
    	 System.out.println();
     }
	void method();*/
}
