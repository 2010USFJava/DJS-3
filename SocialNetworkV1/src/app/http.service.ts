import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  private baseUrl = 'http://localhost:5555/thetea';
  constructor(private http:HttpClient) { }

  getUser(id:number):Observable<any>{
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createUser(user:Object):Observable<Object>{
    console.log(user);
    return this.http.post(`${this.baseUrl}/add`, user);
  }

  updateUser(id:number, value:any):Observable<Object>{
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteUser(id:number):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType:'text'});
  }

  getUsersList():Observable<any>{
    return this.http.get(`${this.baseUrl}/users`);
  }

  getUser(username:string, password:string):Observable<Object>{
    return this.http.post(`${this.baseUrl}/${username}`, password);
  }
}
