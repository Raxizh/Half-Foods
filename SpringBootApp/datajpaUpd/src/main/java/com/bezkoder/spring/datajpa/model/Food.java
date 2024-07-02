package com.bezkoder.spring.datajpa.model;
import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "foods")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //AutoIncrement
	private long id;
	
	@Column(name="foodname")
	private String foodname;
	
	@Column(name="price")
	private String price;
	
	@Column(name="protein")
	private String protein;
	
	@Column(name="carbs")
	private String carbs;
	
	@Column(name="fat")
	private String fat;
	
	@Column(name="keto")
	private String keto;
	
	@Column(name="paleo")
	private String paleo;
	
	@Column(name="atkins")
	private String atkins;
	
	@Column(name="dukan")
	private String dukan;
	
	public Food() {}
	public Food(String foodname, String price, String protein, String carbs, String fat, String keto, String paleo, String atkins, String dukan) {
		this.foodname = foodname;
		this.price = price;
		this.protein = protein;
		this.carbs = carbs;
		this.fat = fat;
		this.keto = keto;
		this.paleo = paleo;
		this.atkins = atkins;
		this.dukan = dukan;
	}
	
	public long getId() { return id;}
	
	public String getFoodname() {return foodname;}
	public void setFoodname(String foodname) {this.foodname = foodname;}
	
	public String getPrice() {return price;}
	public void setPrice(String price) {this.price = price;}
	
	public String getProtein() {return protein;}
	public void setProtein(String protein) {this.protein = protein;}
	
	public String getCarbs() {return carbs;}
	public void setCarbs(String carbs) {this.carbs = carbs;}
	
	public String getFat() {return fat;}
	public void setFat(String fat) {this.fat = fat;}
	
	public String getKeto() {return keto;}
	public void setKeto(String keto) {this.keto = keto;}
	
	public String getPaleo() {return paleo;}
	public void setPaleo(String paleo) {this.paleo = paleo;}
	
	public String getAtkins() {return atkins;}
	public void setAtkins(String atkins) {this.atkins = atkins;}
	
	public String getDukan() {return dukan;}
	public void setDukan(String dukan) {this.dukan = dukan;}
	
	
	@Override
	public String toString() {
		return "Food [id=" + id +", price=" + price + ", protein=" + protein + ", carbs=" + carbs + ", fat=" + fat + ", keto=" + keto + ", paleo=" + paleo + ", atkins=" + atkins + ", dukan=" + dukan + "]";
	}
	
}

