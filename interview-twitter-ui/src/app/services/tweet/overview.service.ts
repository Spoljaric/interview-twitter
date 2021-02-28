import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {UserOverviewModel} from '../../models/useroverview.model';

const ENDPOINT_BASE = '/api/overview';
const BASE_URL = environment.rest_url;

@Injectable()
export class OverviewService {

  constructor(private http: HttpClient) {
  }

  fetchUserOverview(username: string) {
    return this.http.get<UserOverviewModel>(BASE_URL + ENDPOINT_BASE + '/' + username);
  }


}
