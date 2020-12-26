import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { ActivatedRoute, Router} from '@angular/router';
import { HttpService } from '../http.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  user:User;
  id:string;

  constructor(private route: ActivatedRoute, private router: Router, private httpService: HttpService, private cookieService:CookieService) { }

  ngOnInit() {
    this.user = new User();
    //this.id = this.route.snapshot.params['id'];
    console.log(this.cookieService.get('cookie'));
    this.cookieService.get('cookie');
    this.id = this.cookieService.get('cookie');
    this.httpService.getUser(this.id).subscribe(
      data => {
        console.log(data)
        this.user = data;
        console.log(this.user.userId)
      },
      error => console.log(error));
  }

   updateUser(){
    this.httpService.updateUser(this.user).subscribe(
      data => {
        console.log(data);
        this.user = new User();
        this.gotoList();
      },
      error => console.log(error));
   }

  onSubmit(){
    this.updateUser();
  }

  gotoList(){
    this.ngOnInit();
  }

}
