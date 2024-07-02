package com.bezkoder.spring.datajpa.service; 
 
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.UserRepository;

@Service
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	 @Autowired
	    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}
	 public boolean validatePassword(User user, String password) {
		 String salt = user.getUserId().toString().substring(0,6);
		 return encoder.matches(salt + password, user.getPassword());
		
	 }
	 
	 //Validate password pattern?
	 
	 public void createUser(String email, String phone_num, String password, String calcount, String diet) { //added diet
		 UUID userId = UUID.randomUUID();
		 while(this.findById(userId) != null) userId = UUID.randomUUID();
		 String hashedPassword = this.encoder.encode(userId.toString().substring(0,6) + password);
		 this.userRepository.save(new User(email, phone_num, hashedPassword, calcount, userId, false, diet)); //added diet
	 }
	 
	 
	 
	 public List<User> findAllUsers() {return this.userRepository.findAll();}
	 public User findById(UUID userId) {return this.userRepository.findUserByUserId(userId);}
	 public User findByEmail(String email) { return this.userRepository.findByEmail(email);}
	 
	 
	 		
	 
	 
	 
	 
		/*public List<User> getAll() {
			System.out.println("Get all Users 11111...");
	    	return repository.findAll(Sort.by("username").ascending());	    	
	    }
		
	
		
		
	    public Optional<User> findById(long id) {
	        return repository.findById(id);
	    }
	    
	    public long save(User User) {
	        return repository.save(User)
	                             .getId();
	    }
	    
	    public void update(long id, User User) {
	        Optional<User> userr = repository.findById(id);
	        if (userr.isPresent()) {
	            User user = userr.get();
	            user.setUsername(User.getUsername());
		        user.setPassword(User.getPassword());
	            repository.save(user);
	        }
	    }
	  
	
	    public Optional<User> login(String name) {
	        return repository.findByUsername(name);
	    }

	    public void delete(long id) {
	        Optional<User> user = repository.findById(id);
	        user.ifPresent(repository::delete);
	    }*/
		

}
