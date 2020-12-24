import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from './post';


@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = 'http://localhost:5555/theteaPost';
  //post:Post = new Post();

  constructor(private http:HttpClient) { }
  getPost(id:string):Observable<any>{
    return this.http.get(`${this.baseUrl}/posts/${id}`);
  }

  createPost(post:Object, userId:string):Observable<Object>{
    console.log(post);
    console.log("in create post");
    return this.http.post(`${this.baseUrl}/addPost/${userId}`, post);
  }

  updatePost(id:string, value:any):Observable<Object>{
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletePost(id:string):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType:'text'});
  }

  getPostsList():Observable<any>{
    console.log("in post list");
    return this.http.get(`${this.baseUrl}/posts`);
  }
}
