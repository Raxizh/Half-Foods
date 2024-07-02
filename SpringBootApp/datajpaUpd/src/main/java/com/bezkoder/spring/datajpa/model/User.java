package com.bezkoder.spring.datajpa.model;
import javax.persistence.*;
import java.util.UUID;



@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //AutoIncrement
	private long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="userId")
	private UUID userId;
	
	@Column(name="phone_num")
	private String phone_num;
	
	@Column(name="password")
	private String password;
	
	@Column(name="calcount")
	private String calcount;
	
	@Column(name="admin")
	private boolean admin;
	
	@Column(name="diet")
	private String diet;
	
	
	
	public User() {}

	public User(String email, String phone_num, String password, String calcount, UUID userId, boolean admin, String diet) {
		this.email = email;
		this.phone_num = phone_num;
		this.password = password;
		this.calcount = calcount;
		this.userId = userId;
		this.admin = admin;
		this.diet = diet;
	}
	
	public long getId() { return id; }
	
	public String getEmail() { return email;}
	public void setEmail(String username) {this.email = username;}
	
	public UUID getUserId() { return userId;}
	public void setUserId(UUID userId) { this.userId = userId;}

	
	public String getPhone_num() { return phone_num;}
	public void setPhone_num(String phone_num) { this.phone_num = phone_num;}

	
	public String getPassword() { return password;}
	public void setPassword(String password) {this.password = password;}
	
	public String getCalcount() { return calcount;}
	public void setCalcount(String calcount) {this.calcount = calcount;}
	
	public boolean getAdmin() { return admin;}
	public void setAdmin(boolean admin) { this.admin = admin; }
	
	public String getDiet() { return diet;}
	public void setDiet(String diet) { this.diet = diet; }
	
	
	@Override
	public String toString() {
		return "User [id=" + id +", phone_num=" + phone_num + ", password=" + password + ", calcount=" + calcount + ", diet=" + diet + "]";
	}
	
	

}


