import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MovieService } from '../services/MovieService';
import { Review } from '../models/review';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit{
  movieName = "";
  reviews!: Review[];
  queryParams$!:Subscription
  query!:string
  
  constructor(private activatedRoute: ActivatedRoute, private movieSvc: MovieService, private router:Router){}

  ngOnInit(): void {
    console.log(">>> inside movie review component OnInit...")
    this.queryParams$ = this.activatedRoute.queryParams.subscribe(async (params)=>{
      this.query = params['query'];
      console.log(">>> query:",this.query);
      const movieList = await this.movieSvc.getMovieReviews(this.query);
      console.log(">>> get list of movies:",movieList)
      this.reviews = movieList;
      console.log(">>> reviews.length",this.reviews.length)
      this.movieName=movieList
      

    });
    
    
  }

  back(){
    this.router.navigate(['/'])
  }

  comment(movieName:string){
    this.router.navigate(['/comment'], { queryParams: { movieName } });

  }

  
}
