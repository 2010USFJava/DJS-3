import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { ActivatedRoute,Router } from '@angular/router';
import { User } from '../user';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  user: User = new User();

  constructor(private _httpService: HttpService, private _route: ActivatedRoute, private _router: Router, private cookieService: CookieService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log("in loging onSubmit")
    this._httpService.getLogin(this.username, this.password).subscribe(
      data => {
        console.log(data);
        this.user = new  User();
        this.cookieService.set('cookie', `${this.user.id}`)
        console.log(this.cookieService.get('cookie'));
      }
    )
  }
}
