import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations' // For show/hide password in login.component.ts/html
import { MatInputModule } from '@angular/material/input';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { HomeComponent } from './components/home/home.component';
import { UserhomeComponent } from './components/userhome/userhome.component';

const routes : Routes = 
	[{path: "users", component: LoginComponent}]
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddUserComponent,
    HomeComponent,
    UserhomeComponent
  ],
  imports: [
	RouterModule.forRoot(routes),
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
  
})
export class AppModule { }
