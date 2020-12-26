import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { LandingPageComponent } from '../landing-page/landing-page.component';
import { UserDetailsComponent } from '../user-details/user-details.component';
import { CreateUserComponent } from '../create-user/create-user.component';
import { UserListComponent} from '../user-list/user-list.component';
import { UpdateUserComponent } from '../update-user/update-user.component';
import { HomepageComponent } from '../homepage/homepage.component';
import { ProfileComponent } from '../profile/profile.component';

const routes: Routes = [{path: '',pathMatch: 'full', redirectTo: 'landing-page'}, 
                        {path: 'landing-page', component: LandingPageComponent},
                        {path: 'register', component: RegisterComponent},
                        {path: 'login', component: LoginComponent},
                        {path: 'homepage/users', component: UserListComponent},
                        {path: 'add', component: CreateUserComponent},
                        {path: 'posts/update/:id', component: UpdateUserComponent},
                        {path: 'homepage/update/:id', component: UpdateUserComponent},
                        {path: 'details/:id', component: UserDetailsComponent},
                        {path: 'homepage', component: HomepageComponent},
                        {path: 'posts', component: HomepageComponent},
                        {path: 'posts/profile', component: ProfileComponent},
                        {path: 'homepage/profile', component: ProfileComponent},
                        {path: 'posts/logout', component: LandingPageComponent}                  
                      ];

@NgModule({

  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
    
})
export class AppRoutingModule { }
