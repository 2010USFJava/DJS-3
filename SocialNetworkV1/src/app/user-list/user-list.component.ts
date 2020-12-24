import { Component, OnInit } from '@angular/core';
import { UserDetailsComponent} from '../user-details/user-details.component';
import { HttpService } from '../http.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: Observable<User[]>
  name: string;
  user: User = new User();

  constructor(private httpService: HttpService, private router:Router, private cookieService:CookieService) { }

  ngOnInit(){
    this.reloadData();
  }

  reloadData(){
    this.users = this.httpService.getUsersList();
  }

  deleteUser(id:string){
    this.httpService.deleteUser(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  userDetails(id:string){
    this.router.navigate(['details', id]);
  }

  updateUser(id:string){
    this.router.navigate(['update', id]);
  }

  searchUser(): void{
    this.httpService.getUserByName(this.name).subscribe(
      user => {
        this.users = user;
        console.log('User: ' + user);
        this.user = user;
        var array = user;
        const object = Object.assign({}, ...array);
        console.log(object); //object
        console.log(this.users); //array
        console.log(object.userId);
        // this.cookieService.set('cookie', `${object.userId}`);
        // this.id = Number(this.cookieService.get('cookie'));
        // console.log(this.id);
        // console.log(this.cookieService.get('cookie'));
      },
      error => {console.log(error);
      })
  }

  onSubmit(){
    console.log('in user-list onSubmit');
    this.searchUser();
  }

}
