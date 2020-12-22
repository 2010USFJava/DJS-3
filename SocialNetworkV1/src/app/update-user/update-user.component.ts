import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { ActivatedRoute, Router} from '@angular/router';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  id:number;
  user:User;

  constructor(private route: ActivatedRoute, private router: Router, private httpService: HttpService) { }

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

  updateUser(){
    this.httpService.updateUser(this.id, this.user).subscribe(
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
    this.router.navigate(['/users']);
  }

}
