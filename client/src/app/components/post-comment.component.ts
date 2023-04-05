import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../services/MovieService';
import { Subscription } from 'rxjs';
import { Comment } from '../models/comment';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css'],
})
export class PostCommentComponent implements OnInit {

  commentForm!: FormGroup;
  movieName!: string;
  id!: string;
  queryParams$!: Subscription;
  commentParam!: any;

  constructor(private fb: FormBuilder, private router: Router, private activatedRoute:ActivatedRoute, private movieSvc: MovieService) {}

  ngOnInit(): void {
    this.commentForm = this.createCommentForm();
    this.queryParams$ = this.activatedRoute.queryParams.subscribe((queryParams)=>{
      this.movieName = queryParams['movieName'];
      console.log("movieName:", this.movieName);
      this.commentParam=queryParams;
      console.log("commentParam:",this.commentParam)


    })
  }

  createCommentForm(): FormGroup {
    this.commentForm = this.fb.group({
      name: this.fb.control<string>('', [
        Validators.required,
        Validators.minLength(3),
        // Validators.nonWhiteSpace,
        //to ensure name of user dont start and end with any blank spaces
        Validators.pattern(/^(?!\s)(?!.*\s$)[\w\s]+$/),
      ]),
      rating: this.fb.control<string>('',[Validators.required, Validators.min(1),Validators.max(5)]),
      comment: this.fb.control<string>('',[Validators.required]),
    });
    return this.commentForm;
  }

  isCommentFormInvalid(ctrlName: string): boolean {
    const ctrl = this.commentForm.get(ctrlName) as FormControl;
    return ctrl.invalid && !ctrl.pristine;
  }

  back() {
    this.router.navigate(['/search'], {
      queryParams: { query: this.commentParam.query },
    });
  }

  postComment() {
    const c = {} as Comment;
    const nameFormVal = this.commentForm?.value['name'];
    const ratingFormVal = this.commentForm?.value['rating'];
    const commentFormVal = this.commentForm?.value['comment'];
    
    c.movieName = this.movieName;
    c.name= nameFormVal;
    c.rating=ratingFormVal;
    c.comment = commentFormVal;
    
    this.movieSvc.postComment(c);
    this.router.navigate(['/search'], { queryParams: { query: this.commentParam.query } });
    console.log(">>> post Comment:",c)
  }
}
