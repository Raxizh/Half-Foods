package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.service.UserService;
import com.bezkoder.spring.datajpa.JWT.*;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(
		value="/api",
		method= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
		)

public class UserController {
	private final UserService userService;
	private final JWT jwt;
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserController(UserService userService, JWT jwt) {
		this.userService = userService;
		this.jwt = jwt;
	}
	
	@PostMapping("/users/logins")
	public ResponseEntity<String> login (@RequestBody String requestBody) {
		JSONObject json = new JSONObject(requestBody);
		String email = json.getString("email");
		String password = json.getString("password");
		System.out.println("***********************************"+ email); //DEBUG
		System.out.println("***********************************"+ password); //DEBUG
		//Verify this user exists
		User user = this.userService.findByEmail(email); //NOT SAME AS BLAKES
		if(user == null) return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
		//here you're supposed to check if account is locked
		if(userService.validatePassword(user, password)) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Set-Cookie", String.format("BearerToken=%1$s;Path=/;HttpOnly;", this.jwt.build(user.getUserId())));
			//dont know if above line works
			return new ResponseEntity<>("Login Successful.", headers, HttpStatus.OK);
		}
		return new ResponseEntity<>("Password is incorrect", HttpStatus.FORBIDDEN);
		
	}
	@PostMapping("/users/registeruser")
	public ResponseEntity<String> createUser(@RequestBody String requestBody) {
		try {
			JSONObject json = new JSONObject(requestBody);
			String email = json.getString("email");
			String phone_num = json.getString("phone_num");
			String calcount = json.getString("calcount");
			String password = json.getString("password");
			String diet = json.getString("diet"); //ADDED
			System.out.println("REGISTER***********************************"+ email); //DEBUG
			System.out.println("REGISTER***********************************"+ password); //DEBUG
			if(this.userService.findByEmail(email)!= null) {
				return new ResponseEntity<>("The provided email is already registered", HttpStatus.FORBIDDEN);
			}
			this.userService.createUser(email, phone_num, password, calcount, diet);
			return new ResponseEntity<>("HAS BEEN CREATED", HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(
                    "Unable to create new user. Try a different email address or try again later.",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
		}
	}
	
	//May need more, use blake's for reference
	@GetMapping("users/logout")
	public ResponseEntity<String> logout() {
		//Create response headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("Set-Cookie", null);
		return new ResponseEntity<>("Logout Successful.",headers,HttpStatus.OK);
	}

	
}

/*public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
		private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<User> list() {
		return (ResponseEntity<User>) userService.getAll(); // may be wrong
	}
	@GetMapping("/users/{id}") //Pay attention to the method before mapping, this is a 'get' mapping aka. fetch users by Id
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		Optional<User> userData = userRepository.findById(id);
		
		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			System.out.println("********************** " +user.getPassword() + "************************");
			User _user = userRepository.save(new User(user.getUsername(), user.getPhone_num(), user.getPassword(), user.getCalcount()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users/auth/{username}")
	public ResponseEntity<User> login(@PathVariable String username) {
		Optional<User> cat = userService.login(username);
		return cat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	

}*/
