package com.bezkoder.spring.datajpa.service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.bezkoder.spring.datajpa.model.Food;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.FoodRepository;

@Service
@Transactional
public class FoodService {
	private final FoodRepository foodRepository;
	
	@Autowired
	public FoodService(FoodRepository foodRepository) { this.foodRepository = foodRepository;}
	public List<Food> getAll() {
		//System.out.print("food service findall");
		return this.foodRepository.findAll();
		}
	public Food findByFoodname(String foodname) {return this.foodRepository.findByFoodname(foodname);}
	

}
