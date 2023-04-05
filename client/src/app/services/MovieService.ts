import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, lastValueFrom } from "rxjs";
import { Review } from "../models/review";
import { Comment } from "../models/comment";

@Injectable()
export class MovieService{

  private MOVIE_REVIEW_API_URL = "/api";

  constructor(private httpClient: HttpClient){}

  getMovieReviews(movieName: string): Promise<any> {
    const queryParams = new HttpParams()
        .set('query',movieName);
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json;charset=utf-8'
    );

    return firstValueFrom(this.httpClient.get<Review[]>(this.MOVIE_REVIEW_API_URL+"/search",{params:queryParams, headers:headers}))
  }

  postComment(c:Comment): Promise<any>{
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json;charset=utf-8'
    );
    const body = JSON.stringify(c);
    console.log('>>> post comment:', c);
    //to match path same as Springboot Controller
    return lastValueFrom(
      this.httpClient.post<Comment>(
        this.MOVIE_REVIEW_API_URL + '/comment',
        body,
        { headers: headers }
      )
    );
  }
}