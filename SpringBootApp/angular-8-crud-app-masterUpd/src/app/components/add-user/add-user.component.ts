import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import {RouterModule, Routes,ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
	user = {
		email: '',
		phone_num: '',
		password: '',
		calcount: '',
		diet: ''
	};
	submitted = false;

  constructor(private userService : UserService, public router: Router, public activatedRoute: ActivatedRoute) { }

  ngOnInit() {
  }
  
  redirect() {
	  this.router.navigate(['/userhome']);
  }
  
  saveUser() {
	  var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
	  if(this.user.email.length < 5 ) {
		  alert("Invalid email")
	  }
	  else if (this.user.email.length > 40) {
		  alert("Invalid email")
	  }
	  else if (this.user.phone_num.length < 12) {
		  alert("Phone number must be input in an XXX-XXX-XXXX format")
	  }
	  else if (this.user.phone_num.length > 12) {
		  alert("Phone number must be input in a XXX-XXX-XXXX format")
	  }
	  else if (this.user.password.length > 25) {
		  alert("Password cannot be greater than 24 characters")
	  }
	  else if (this.user.password.length < 8) {
		  alert("Password must be greater than 8 characters long")
	  }
	  else if (!strongRegex.test(this.user.password)){
		  alert("Password must contain at least 1 lowercase alphabetical character,1 uppercase alphabetic character, 1 numeric character and 1 special character")
	  }
	  else if (parseInt(this.user.calcount) < 500) {
		  alert("Calorie count must be greater than 500")
	  }
	  else if (parseInt(this.user.calcount) > 5000) {
		  alert("Calorie count cannot be greater than 5000")
	  }
	  else if (parseInt(this.user.calcount) == NaN) {
		  alert("Calorie count must be a number between 500 and 5000")
	  }
	  else if (this.user.calcount.length < 1) {
		  alert("Calorie count is required")
	  }
	  else if(this.user.diet == "") {
		  alert("Diet choice is required")
	  }
	  
	  
	  else {
		  const data = {
			  email: this.user.email,
			  phone_num: this.user.phone_num,
			  password: this.user.password,
			  calcount: this.user.calcount,
			  diet: this.user.diet
		  };
		  alert("Username" + this.user.email + " diet: " + this.user.diet)
		  
		  this.userService.create(data).subscribe( response => {console.log(response); this.submitted = true;}, error => {console.log(error);});
		  this.submitted=true;
		  }
  }

  }

