import { Injectable } from '@angular/core';
import { UserService } from "./user.service";
import { ActivatedRoute, Router } from "@angular/router";


@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  constructor(public userService: UserService, public router: Router, public activatedRoute: ActivatedRoute) { }
  
  logout() {
	  if(this.userService.logout()) {
		sessionStorage.setItem('isLoggedIn', 'false');
	    this.userService.authenticated = false;
	    this.router.navigate(['/login']);
	    }
  }
}
