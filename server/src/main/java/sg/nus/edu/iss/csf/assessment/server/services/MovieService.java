package sg.nus.edu.iss.csf.assessment.server.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.nus.edu.iss.csf.assessment.server.models.Comment;
import sg.nus.edu.iss.csf.assessment.server.models.Review;
import sg.nus.edu.iss.csf.assessment.server.repositories.MovieRepository;

@Service
public class MovieService {

	@Value("${movie.review.api.url}")
	//https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=<query>&api-key=<api-key>
  private String movieReviewApiUrl;

  @Value("${movie.review.api.key}")
  private String movieReviewApiKey;

  @Autowired
  private MovieRepository movieRepo;
	
	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {

		ResponseEntity<String> resp = null;
		List<Review> reviewList = null;

		String movieReviewUrl = UriComponentsBuilder
				.fromUriString(movieReviewApiUrl)
				.queryParam("query",query.trim().replaceAll(" ","+"))
				.queryParam("api-key",movieReviewApiKey.trim())
				.toUriString();
		
				System.out.println(">>> movie review url:"+ movieReviewUrl);

				RestTemplate restTemplate = new RestTemplate();
				resp = restTemplate.getForEntity(movieReviewUrl, String.class);
				System.out.println(">>> response entity: "+resp);

				try{
					reviewList = Review.create(resp.getBody());
				} catch (IOException e){
					e.printStackTrace();
					//return empty list if got error
					return new LinkedList<>();
				}
				System.out.println(">>> review list: "+reviewList);

				if(reviewList != null){
					return reviewList;
				} else {
					//return empty list if fot 
					return new LinkedList<>();
				}
	}

  public Integer countComments(String commentPayload){
    return movieRepo.countComments(commentPayload);
  }

  public Document insertComment(Comment c){
    return movieRepo.insertComment(c);
  }

}
