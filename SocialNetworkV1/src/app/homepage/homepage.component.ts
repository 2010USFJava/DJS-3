import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { ActivatedRoute,Router } from '@angular/router';
import { Post } from '../post';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  posts: Observable<Post[]>
  post: Post = new Post();
  submitted = false;
  id:string;

  constructor(private postService: PostService, private route: ActivatedRoute, private router: Router, private cookieService: CookieService) { }

  ngOnInit(){
    this.reloadData();
    console.log(this.cookieService.get('cookie'));
    this.cookieService.get('cookie');
    this.id = this.cookieService.get('cookie');
  }

  reloadData(){
    this.posts = this.postService.getPostsList();
    console.log(this.posts);
    console.log("in reload data");
  }
  
  deletePost(id:string){
    this.postService.deletePost(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  postDetails(id:string){
    this.router.navigate(['details', id]);
  }

  updatePost(id:string){
    this.router.navigate(['update', id]);
  }

  newPost():void{
    this.submitted=false;
    this.post=new Post();
  }

  save(){
    //this.post.userId = 2;
    console.log(this.post);
    this.postService.createPost(this.post, this.id).subscribe(
      data => {
        //this.post.likeCount = 0;
        console.log(data);
        this.cookieService.get('cookie');
        var id = this.cookieService.get('cookie');
        //const post = new FormData();
        //post.append('post', data);
        this.gotoList();
      },
      error => console.log(error));  
    //console.log("in save method");
    //this.gotoList();
        //this.post = new Post();
        //this.gotoPost();
      
      //error => console.log(error);
  }

  onSubmit(){
    console.log('in onSubmit');
    this.submitted = true;
    this.save();
  }

  gotoList(){
    console.log('in gotoList');
    this.router.navigate(['/posts']);
  }

}

