import { Component, OnInit, Input } from '@angular/core';
import { User } from '../user';
import { HttpService } from '../http.service';
import { UserListComponent } from '../user-list/user-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  id:number;
  user:User;

  constructor(private route: ActivatedRoute, private router: Router, private httpService:HttpService) { }

  ngOnInit() {
    this.user = new User();
    
    this.id = this.route.snapshot.params['id'];

    this.httpService.getUser(this.id).subscribe(
      data => {
        console.log(data)
        this.user = data;
      },
      error => console.log(error));
  }

  list(){
    this.router.navigate(['users']);
  }

}
