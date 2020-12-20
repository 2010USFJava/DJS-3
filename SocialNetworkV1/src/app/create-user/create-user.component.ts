import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { User } from '../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  user: User = new User();
  submitted = false;

  constructor(private httpService:HttpService, private router:Router) { }

  ngOnInit(){
  }

  newUser():void{
    this.submitted=false;
    this.user=new User();
  }

  save(){
    this.httpService.createUser(this.user).subscribe(
      data => {
        console.log(data);
        this.user = new User();
        this.gotoList();
      },
      error => console.log(error));
  }

  onSubmit(){
    console.log('in onSubmit');
    this.submitted = true;
    this.save();
  }

  gotoList(){
    this.router.navigate(['/users']);
  }

}
