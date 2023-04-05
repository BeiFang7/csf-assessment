package sg.nus.edu.iss.csf.assessment.server.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import sg.nus.edu.iss.csf.assessment.server.models.Comment;
import sg.nus.edu.iss.csf.assessment.server.models.Review;
import sg.nus.edu.iss.csf.assessment.server.services.MovieService;

// @CrossOrigin(origins="*")
@Controller
@RequestMapping(path="/api")
public class MovieController {

  // TODO: Task 3, Task 4, Task 8

  @Autowired
  private MovieService movieSvc;

  @GetMapping(
    path="/search", 
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces=MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> getMovieReviewsByName(@RequestParam (required=true) String query){
    System.out.println(">>> search movie reviews by name...");
    
    JsonArray result = null;
    List<Review> reviewList = this.movieSvc.searchReviews(query);

    for(Review r: reviewList){
      int count = this.movieSvc.countComments(r.getTitle());
      r.setCommentCount(count);
    }
    
    JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
    for(Review r : reviewList){
      arrBuilder.add(r.toJSON());
    }
    result = arrBuilder.build();
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
  }

  //to match path same as Angular service
  @PostMapping(path="/comment"
  , consumes="application/json"
  , produces="application/x-www-form-urlencoded"
  )
  public ResponseEntity<String> saveComment(@RequestBody Comment comment){
    System.out.println(">>> save comment...: "+comment);
    Comment c = new Comment();
    c.setMovieName(comment.getMovieName());
    c.setName(comment.getName());
    c.setRating(comment.getRating());
    c.setComment(comment.getComment());
    Document sc = this.movieSvc.insertComment(c);
    int count = this.movieSvc.countComments(comment.getMovieName());
    System.out.println(">>> count:"+count);
    

    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(sc.toString());
    
  }
  

  
}
