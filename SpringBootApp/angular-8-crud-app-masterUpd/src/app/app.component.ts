import { Component, OnInit } from '@angular/core';
import { UserService } from './services/user.service';
import { LogoutService } from './services/logout.service'; 
import { AuthenticationService } from './services/authentication.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
	
	constructor (private userService : UserService, public logoutService: LogoutService, public authService: AuthenticationService) {}
	ngOnInit(){	}
  title = 'Angular8ClientCrud';
  		loginCheck(): boolean {
		  return this.authService.isLoggedIn();
		  //May not need userService in constructor
	  }
}
