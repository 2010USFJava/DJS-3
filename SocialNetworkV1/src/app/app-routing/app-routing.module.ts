import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { LandingPageComponent } from '../landing-page/landing-page.component';
import { UserDetailsComponent } from '../user-details/user-details.component';
import { CreateUserComponent } from '../create-user/create-user.component';
import { UserListComponent} from '../user-list/user-list.component';
import { UpdateUserComponent } from '../update-user/update-user.component';

const routes: Routes = [{path: '',pathMatch: 'full', redirectTo: 'landing-page'}, 
                        {path: 'landing-page', component: LandingPageComponent},
                        {path: 'register', component: RegisterComponent},
                        {path: 'login', component: LoginComponent},
                        {path: 'users', component: UserListComponent},
                        {path: 'add', component: CreateUserComponent},
                        {path: 'update/:id', component: UpdateUserComponent},
                        {path: 'details/:id', component: UserDetailsComponent}
                      ];

@NgModule({

  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
    
})
export class AppRoutingModule { }
