import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { HttpService } from '../http.service';
import { ActivatedRoute,Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username:string;
  password:string;
  user:User;

  constructor(private _httpService: HttpService, private _route: ActivatedRoute, private _router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.user.username = this.username;
    if (this.user.password==this.password) {
      
    }
  }

}
