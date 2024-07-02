package com.bezkoder.spring.datajpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bezkoder.spring.datajpa.JWT.*;
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
import com.bezkoder.spring.datajpa.model.Food;
import com.bezkoder.spring.datajpa.service.FoodService;
import java.util.List;




@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(
		value="/api",
		method= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
		)


public class FoodController {
	private final FoodService foodService;
	private final JWT jwt;
	private final Logger logger = LoggerFactory.getLogger(FoodController.class);
	
	@Autowired
	public FoodController(FoodService foodService, JWT jwt) {
		System.out.println("one *********************");

		this.foodService = foodService;
		this.jwt = jwt;
	}
	
	@GetMapping("/foods/list") 
	public List<Food> getFoods () {
		return (List<Food>) this.foodService.getAll();
	}
	
	@PostMapping("/foods/getfood")
	public ResponseEntity<String> foodByName(@RequestBody String requestBody) {
		JSONObject json = new JSONObject(requestBody);
		String foodname = json.getString("foodname");
		System.out.println("*************************** foodname: " + foodname);
	    //Food food = this.foodService.findByFoodname(foodname);
	    if(this.foodService.findByFoodname(foodname) != null) {
	    	return new ResponseEntity<String>(foodname, HttpStatus.OK); 
	    }
	    else {
	    	return new ResponseEntity<>("No foodname", HttpStatus.NOT_FOUND); 

	    }
	}
	

}
