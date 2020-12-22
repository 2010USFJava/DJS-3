import { Component, OnInit } from '@angular/core';
import { UserDetailsComponent} from '../user-details/user-details.component';
import { HttpService } from '../http.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: Observable<User[]>

  constructor(private httpService: HttpService, private router:Router) { }

  ngOnInit(){
    this.reloadData();
  }

  reloadData(){
    this.users = this.httpService.getUsersList();
  }

  deleteUser(id:number){
    this.httpService.deleteUser(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  userDetails(id:number){
    this.router.navigate(['details', id]);
  }

  updateUser(id:number){
    this.router.navigate(['update', id]);
  }

}
