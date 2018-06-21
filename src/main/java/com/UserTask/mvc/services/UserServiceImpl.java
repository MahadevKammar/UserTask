package com.UserTask.mvc.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.UserTask.mvc.Entities.CustomerUserDetails;
import com.UserTask.mvc.Entities.User;
import com.UserTask.mvc.Entities.UserTask;
import com.UserTask.mvc.dao.UserDAO;
import com.UserTask.mvc.services.serivceInterfaces.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public String hellowrold() {
		return "Hello world";
	}

	@Override
	public User postData(User user) {
		if (!userDao.existsByemail(user.getEmail())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setUserCreatedOn(getCurrentDateandTime());
			return userDao.save(user);
		} else {
			return null;
		}
	}

	@Override
	public User GetData(String email, String password) {
		String pass = userDao.findByMail(email);
		System.out.println(pass);
		if (passwordEncoder.matches(password, pass) && pass != null) {
			return userDao.findByemailAndPassword(email, pass);
		} else {
			return null;
		}
	}

	public String getCurrentDateandTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	@Override
	public User getUserById(int id) {
		return userDao.findByuserId(id);

	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		Optional<User> user = userDao.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return user.map(CustomerUserDetails::new).get();

	}
	
	
	public  List<User> getAllData(int id){
		ArrayList<User> al=new ArrayList<User>();		
		for(User ut:userDao.findByIds(id)){
			//System.out.println(ut);
			al.add(ut);
		}
		/*for(User u:userDao.findSal()){
			System.out.println("Name:"  +u.getUserName() );
		}*/
		return al;
	}
	
	

}
