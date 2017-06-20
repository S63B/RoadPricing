import { Injectable } from '@angular/core';
import { Headers, Response, Http } from '@angular/http';

@Injectable()
export class HttpService {

  constructor(private http: Http) {
  }

  public extractData(res: Response) {
    let body = res.text();
    return JSON.parse(body).entity || {};
  }

  public get(url) {
    console.log(url);
    return this.http.get(url, {
      headers: this.setHeaders()
    });
  }

  public post(url, body?) {
    console.log(url);
    return this.http.post(url, body, {
      headers: this.setHeaders()
    });
  }

  public customPost(url, body, options) {
    console.log(url);
    return this.http.post(url, body, options);
  }

  public put(url, body?) {
    console.log(url);
    return this.http.put(url, body, {
      headers: this.setHeaders(),
    });
  }


  private setHeaders(): Headers {
    const headers: Headers = new Headers();
    // TODO change for actual username and password / base64 encoded string of 'username:password'
    headers.append('Authorization', `Basic ${btoa('admin:admin')}`);
    return headers;
  }
}
