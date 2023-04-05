import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { MovieService } from './services/MovieService';
import { SearchReviewComponent } from './components/search-review.component';
import { MovieReviewsListComponent } from './components/movie-reviews-list.component';
import { PostCommentComponent } from './components/post-comment.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchReviewComponent,
    MovieReviewsListComponent,
    PostCommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [MovieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
