import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TweetModel} from "../../models/tweet.model";
import {Observable} from "rxjs/Observable";
import {environment} from '../../../environments/environment';

const ENDPOINT_BASE = '/api/tweets';
const BASE_URL = environment.rest_url;

@Injectable()
export class TweetService {

  constructor(private http: HttpClient) {
  }

  fetch(): Observable<TweetModel[]> {
    return this.http.get<TweetModel[]>(BASE_URL + ENDPOINT_BASE);
  }

  fetchForUser(username: string) {
    return this.http.get<TweetModel[]>(BASE_URL + ENDPOINT_BASE + '/' + username);
  }

  create(tweetContent: string) {
    return this.http.post<TweetModel>(BASE_URL + ENDPOINT_BASE, tweetContent);
  }
}
