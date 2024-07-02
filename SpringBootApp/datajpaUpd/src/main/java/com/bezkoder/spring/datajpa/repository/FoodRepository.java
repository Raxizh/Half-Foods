package com.bezkoder.spring.datajpa.repository;
import java.util.List;
import java.util.UUID; //may not need
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bezkoder.spring.datajpa.model.Food;


public interface FoodRepository extends JpaRepository<Food, Long>{
	List<Food> findAll();
	Food findById(String id);
	List<Food> findByPrice(String price);
	Food findByFoodname(String foodname);
	List<Food> findByKeto(String keto);
	List<Food> findByPaleo(String paleo);
	List<Food> findByAtkins(String atkins);
	List<Food> findByDukan(String dukan);

	

}
