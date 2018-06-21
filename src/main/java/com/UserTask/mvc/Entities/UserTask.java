package com.UserTask.mvc.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="usertask")
public class UserTask {

	@Id
	@Column(name = "TASKNO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskNo;

	@Column(name = "TASKNAME")
	private String taskName;

	@Column(name = "STATUS",columnDefinition="tinyint(1) default 1")
	private int Status;

	@Column(name = "SRARTDATE")
	private String startDate;

	@Column(name = "ENDDATE")
	private String endDate;	
	
	@Transient
	private String cdate;	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	
	public int getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(int taskNo) {
		this.taskNo = taskNo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	
}
