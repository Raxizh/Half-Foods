import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

/* Currently used as a template for authentication, connection to database goes below */
export class AuthenticationService {

  constructor() { }

  isLoggedIn() { //Gets and sets user's session storage
    let status = false;
    if(sessionStorage.getItem('isLoggedIn') == "false") {
		status = false;
	}
    else if(sessionStorage.getItem('isLoggedIn') == "true") {
		status = true;
	}
	else {
		status = false;
	}
	return status;
  }


}