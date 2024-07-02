import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthenticationService} from "./authentication.service";

@Injectable({
    providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{

    constructor(private authenticationService: AuthenticationService) {}

    /**
     * Intercepts all outgoing api requests and adds the context path prefix. The request is cloned, the clone is
     * modified, then the clone is sent as requests are immutable once transportation begins.
     * @param request The intercepted request.
     * @param next Handler for sending the newly cloned request.
     */
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (request.url.startsWith("/")) {
            request = request.clone({
                //url: `/message-forum${request.url}`
                url: `/tutorials${request.url}`
            });
        } else {
            request = request.clone({
                //url: `/message-forum/${request.url}`
                url: `/tutorials/${request.url}`
            });
        }
        return next.handle(request);
    }

}
