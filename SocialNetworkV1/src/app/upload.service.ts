import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private http:HttpClient) { }

  upload(file: File): Observable<any> {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    const req = new HttpRequest('POST', 'http://localhost:5555/file/upload', formdata, {reportProgress: true, responseType: 'text'});
    return this.http.request(req);
  }

  download(key: string): Observable<any>{
    return this.http.get('http://localhost:5555/file/download');
  }

  getAllImages(key: string): Observable<any>{
    return this.http.get('http://localhost:5555/file/all');
  }
}

