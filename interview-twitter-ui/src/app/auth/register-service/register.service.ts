import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

const ENDPOINT_BASE = '/api/register';
const BASE_URL = environment.rest_url;

@Injectable()
export class RegisterService {
  constructor(private http: HttpClient) {
  }

  registerUser(user, pass, name) {
    this.http.post(BASE_URL + ENDPOINT_BASE, {username: user, password: pass, fullName: name}, {
      headers: new HttpHeaders({
        'X-Skip-Interceptor': ''
      })
    }).subscribe();
  }
}
