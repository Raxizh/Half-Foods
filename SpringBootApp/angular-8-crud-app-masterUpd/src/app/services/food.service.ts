import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http'
import { Router } from "@angular/router"
import { ErrorHandlerService } from "./error-handler.service"; //May not need
import { Food} from ".././models/food";
import { Observable } from 'rxjs';



const baseUrl = 'http://localhost:8080/api/foods'

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  constructor(private http: HttpClient, private router: Router, private errorHandlerService: ErrorHandlerService, ) { }
  
  public getFoods(): Observable<Food[]> {
	  return this.http.get<Food[]>(baseUrl + "/list");
  }
  getFoodByName(foodname: string) {
	  let requestBody: object = {
		  foodname: foodname
		  }
		  alert(baseUrl + "/getfood");
		  this.http.post(baseUrl + "/getfood", requestBody, {responseType: "text"}).subscribe(
			  {
			  next: (response: string) => {
				  alert("response: " + response)
				  return response;
			  },
			  error: (error: HttpErrorResponse) => {
				  alert("HTTP Error Found" + error.message)
				  return null;
			  }
		  }
	  );
  }
}
