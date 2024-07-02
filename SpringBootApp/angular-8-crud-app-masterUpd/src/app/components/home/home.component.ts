import { Component, OnInit } from '@angular/core';
import { UserService } from "../../services/user.service";
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private userService : UserService, private authService: AuthenticationService) {
	  authService.isLoggedIn(); //May not be necessary
   }

  ngOnInit() {
  }

}
