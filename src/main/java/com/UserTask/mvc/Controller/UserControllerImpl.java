package com.UserTask.mvc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UserTask.mvc.Controller.UserCotrollerInterface.UserInterace;

import com.UserTask.mvc.Entities.Role;
import com.UserTask.mvc.Entities.User;
import com.UserTask.mvc.services.UserServiceImpl;

@RestController
public class UserControllerImpl implements UserInterace {

	@Autowired
	private UserServiceImpl userservice;


	@Override
	@PostMapping(value = "/saveuser")
	@CrossOrigin
	public ResponseEntity<User> postUser(@RequestBody User user) {
		System.out.println("sdfdsf");
		System.out.println(user.getEmail());
		User userResponse = userservice.postData(user);
		if (userResponse != null) {
			System.out.println("user:" +userResponse.getEmail());
			return new ResponseEntity<User>(userResponse, HttpStatus.CREATED);
		} else {
			System.out.println("Not getting output");
			return new ResponseEntity<>(userResponse, HttpStatus.NOT_ACCEPTABLE);
		}

	}

	
	
	@Override
	@GetMapping(value = "/login")
	@CrossOrigin
	public ResponseEntity<User> getUser(@RequestParam String email, @RequestParam String password) {
		User user = userservice.GetData(email, password);
		if (user != null) {
			for (Role u : user.getRole()) {
				System.out.println(u.getRole());
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping(value = "/getuser/{userId}")
	@CrossOrigin
	public ResponseEntity<User> getUserObj(@PathVariable("userId") int UserId) {
		User user = userservice.getUserById(UserId);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
	}

}

/*
 * @GetMapping("/getdataafterjoin/{userId}") public ResponseEntity<List<User>>
 * getalldata(@PathVariable("userId") int id){ List<User>
 * ul=userservice.getAllData(id); return new ResponseEntity<List<User>>(ul,
 * HttpStatus.OK); }
 * 
 * @PostMapping("/postdata") public ResponseEntity<User>
 * postalldata(@RequestBody User user){ System.out.println(user.getEmail());
 * System.out.println(user.getPassword()); return new ResponseEntity<User>(user,
 * HttpStatus.OK); }
 * 
 * 
 * @GetMapping("/helloworld") public ResponseEntity<String> hello() { String str
 * = userservice.hellowrold(); return new ResponseEntity<String>(str,
 * HttpStatus.OK); }
 * 
 * @GetMapping(value = "/helloworld/json", produces =
 * MediaType.APPLICATION_JSON_VALUE) public Hello hellojson() { return hello; }
 * 
 * @CrossOrigin
 * 
 * @PostMapping(value = "/savehello", consumes =
 * MediaType.APPLICATION_JSON_VALUE, produces =
 * MediaType.APPLICATION_JSON_VALUE) public Hello postUser(@RequestBody Hello
 * helloObj) { return helloObj; }
 */
