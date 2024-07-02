import { Injectable } from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class ErrorHandlerService {

    constructor() { }

    public catchError(error: HttpErrorResponse) {
        alert(`Response: ${error.status} (${error.statusText})\nMessage: ${error.error}`)
    }
}
