import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { LandingPageComponent } from '../landing-page/landing-page.component';

const routes: Routes = [{path: '',pathMatch: 'full', redirectTo: 'landing-page'}, 
                        {path: 'landing-page', component: LandingPageComponent},
                        {path: 'register', component: RegisterComponent},
                      {path: 'login', component: LoginComponent}];

@NgModule({

  declarations: [],
  imports: [RouterModule.forRoot(routes),
    CommonModule
  ],
  exports: [RouterModule]
    
})
export class AppRoutingModule { }
