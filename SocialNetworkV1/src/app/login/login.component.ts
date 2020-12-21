import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute,Router } from '@angular/router';
import { User } from '../user';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  user: User = new User();
  login: User = new User();

  constructor(private _httpService: HttpService, private _route: ActivatedRoute, private _router: Router, private cookieService: CookieService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log("in login onSubmit")
    console.log(this.user.username)
    this._httpService.getLogin(this.user.username, this.user.password).subscribe(
      data => {
        console.log(data);
        this.login = data;
        console.log(this.login.userId)
        this.cookieService.set('cookie', `${this.login.userId}`)
        console.log(this.cookieService.get('cookie'));
      }
    )
  }
}
