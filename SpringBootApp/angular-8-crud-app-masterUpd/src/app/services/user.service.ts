import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http'
import { Router } from "@angular/router"
import {ErrorHandlerService} from "./error-handler.service";


const baseUrl = 'http://localhost:8080/api/users';

@Injectable({
  providedIn: 'root'
})

/* This class returns/gets data from the angular side/backend (springboot)
   Directly corresponds to the UserController/UserService class on the backend
 */
export class UserService {
	authenticated = false;
	email = "";
	
	

  constructor(private http: HttpClient, private router: Router, private errorHandlerService: ErrorHandlerService, ) { }
  
  login(email: string, password: string) { 
	  let requestBody: object = {
		  email: email,
		  password: password
	  }
	  
	         this.http.post(baseUrl + "/logins", requestBody, {responseType: "text"}).subscribe(
            {
                next: (response: string) => {
					this.router.navigateByUrl(baseUrl);
					this.authenticated = true;
					this.email = email;
					sessionStorage.setItem('isLoggedIn', "true")
					alert("You have successfully logged in!")
					this.router.navigateByUrl('http://localhost:8081/userhome')
                },
                error: (error: HttpErrorResponse) => {
					alert("Username or Password is Incorrect")
					sessionStorage.setItem('isLoggedIn', "false")
					//this.errorHandlerService.catchError(error); //WILL PRINT ERROR MESSAGE IF INCORRECT LOGIN
					this.router.navigateByUrl('http://localhost:8081/user')
					this.authenticated = false;
				}
            }
        );
    }
    
    logout(){
		 if(confirm("Are you sure you want to log out?")) {
			this.http.get(baseUrl + "/logout", {responseType: "text"}).subscribe(
				{
					next: (response:string) => {
						alert("You have successfully logged out!");
						sessionStorage.setItem('isLoggedIn', "false");
						this.router.navigateByUrl("/home");
					}
				}
			);
			this.router.navigateByUrl("/home");
			return true;
			}
		else{
			return false;
		}
			
	}
	
	getUser() {
		return this.email;
	}
	
  
  getAll() {
	  return this.http.get(baseUrl);
	  //return this.http.get(`${baseUrl}/${id}`);
  }
  get(id) {
	  return this.http.get(`${baseUrl}/${id}`);
  }
  create(data) {
	  return this.http.post(baseUrl + '/registeruser', data);
  }
  
  /*login(username: string, password: string) {
	  return this.http.get(`${baseUrl}/auth/${username}`);
  }*/
  
}
