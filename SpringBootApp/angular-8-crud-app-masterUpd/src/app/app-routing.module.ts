import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { UserService } from './services/user.service';
import { HomeComponent } from './components/home/home.component';
import { UserhomeComponent } from './components/userhome/userhome.component';


const routes: Routes = [
  //{ path: '', redirectTo: 'registeruser', pathMatch: 'full' },
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  {	path: 'login', component: LoginComponent},
  { path: 'registeruser', component: AddUserComponent},
  { path: 'userhome', component: UserhomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],//{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

//If not running incognito, clear cache before running 
