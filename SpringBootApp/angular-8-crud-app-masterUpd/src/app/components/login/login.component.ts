import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	hide = true;

  constructor(public router: Router, public activatedRoute: ActivatedRoute, public userService: UserService) {
	  
  }

  ngOnInit(): void {

  }
  
  // This method redirects a user if logged in
  redirect(userService: UserService) {
	  this.router.navigate(['/userhome']);
  }
  alertWrongPassword() {
	  alert("Email or password is incorrect")
  }
  
  clickMethod(email: string, password: string) {
		  this.userService.login(email, password);
  }
  
  viewpass() { // Attempt for showing/hiding passwords
	  this.hide = !this.hide;
	  
  }

}

