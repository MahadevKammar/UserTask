package com.UserTask.mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.UserTask.mvc.Entities.UserTask;

public interface UserTaskDao extends CrudRepository<UserTask, Integer> {
	
	@Query("select ut from UserTask ut where startDate = :cdate")
	List<UserTask> findByPresentDate(@Param("cdate") String cdate);

}
