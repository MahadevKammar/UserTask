package com.UserTask.mvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.UserTask.mvc.Entities.User;

public interface UserDAO extends CrudRepository<User, Integer> {
	//Checking user exist or not while registering
	boolean existsByemail(String email);
	
	//checking email and password while logging in
	User findByemailAndPassword(String email,String password);	
	
	//Fetching password by given email for authentication
	@Query("select password from User where email=:email")
	String findByMail(@Param("email") String email);
	
	Optional<User> findByEmail(String email);

	//fetching user by id
	User findByuserId(int id);
	
	
	public final static String product_ordered ="Select distinct u from User u Join u.userTasks od Where od.user.userId = :id";

	@Query(product_ordered)
	public List<User> findByIds(@Param("id") int id);
	
	public final static String product_ordered1 ="Select userName from User u group by u.userId";

	@Query(product_ordered1)
	public List<User> findSal();
}
