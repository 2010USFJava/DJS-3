import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { ActivatedRoute,Router } from '@angular/router';
import { Post } from '../post';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  posts: Observable<Post[]>
  post: Post = new Post();
  submitted = false;

  constructor(private postService: PostService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(){
    this.reloadData();
  }

  reloadData(){
    this.posts = this.postService.getPostsList();
    console.log(this.posts);
    console.log("in reload data");
  }
  
  deletePost(id:number){
    this.postService.deletePost(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  postDetails(id:number){
    this.router.navigate(['details', id]);
  }

  updatePost(id:number){
    this.router.navigate(['update', id]);
  }

  newPost():void{
    this.submitted=false;
    this.post=new Post();
  }

  save(){
    this.post.userId = 2;
    console.log(this.post);
    this.postService.createPost(this.post,this.post.userId); 
    console.log("in save method");
        //this.post = new Post();
        //this.gotoPost();
      
      //error => console.log(error);
  }

  onSubmit(){
    console.log('in onSubmit');
    this.submitted = true;
    this.save();
  }

}

