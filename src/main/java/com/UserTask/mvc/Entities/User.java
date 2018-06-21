package com.UserTask.mvc.Entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERID")
	private int userId;

	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "MOBILENO")
	private String userMobileNumber;

	@Column(name = "CITY")
	private String userCity;

	@Column(name = "ZIPCODE")
	private int zipcode;

	@Column(name = "USERCREATEDON")
	private String userCreatedOn;

	@Column(name = "STATUS", columnDefinition = "tinyint(1) default 1")
	private int userStatus;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserTask> userTasks = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE_T", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> role = new LinkedHashSet<Role>();

	public User() {
	}

	public User(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.userMobileNumber = user.getUserMobileNumber();
		this.userCity = user.getUserCity();
		this.zipcode = user.getZipcode();
		this.userCreatedOn = user.getUserCreatedOn();
		this.userTasks = user.getUserTasks();
		this.role = user.getRole();
	}

	
	
	
	public User(int userId, String userName, String email, String password, String userMobileNumber, String userCity,
			int zipcode, String userCreatedOn, int userStatus, List<UserTask> userTasks, Set<Role> role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userMobileNumber = userMobileNumber;
		this.userCity = userCity;
		this.zipcode = zipcode;
		this.userCreatedOn = userCreatedOn;
		this.userStatus = userStatus;
		this.userTasks = userTasks;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public List<UserTask> getUserTasks() {
		return userTasks;
	}

	public void setUserTasks(List<UserTask> userTasks) {
		this.userTasks = userTasks;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserCreatedOn() {
		return userCreatedOn;
	}

	public void setUserCreatedOn(String userCreatedOn) {
		this.userCreatedOn = userCreatedOn;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

}
