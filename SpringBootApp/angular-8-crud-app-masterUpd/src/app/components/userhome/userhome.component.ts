import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { AuthenticationService } from '../../services/authentication.service';
import { FoodService } from '../../services/food.service';
import { Food } from '../../models/food';

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent implements OnInit {
	foods: Food[];
  constructor(public userService : UserService, public authService: AuthenticationService, public foodService: FoodService) { }

  ngOnInit(): void {
	  this.getFoods();
  }
  
  getFoods() {
	  this.foodService.getFoods().subscribe(data => {
		  this.foods = data;
	  });
  }

}
