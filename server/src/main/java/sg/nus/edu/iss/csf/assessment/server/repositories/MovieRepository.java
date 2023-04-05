package sg.nus.edu.iss.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.csf.assessment.server.models.Comment;

import static sg.nus.edu.iss.csf.assessment.server.Constants.*;

@Repository
public class MovieRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//
	/*
    db.comments.find({movieName: ? }).count()    
	*/
	public int countComments(String movieName) {
		Criteria criteria = Criteria.where(FIELD_MOVIE_NAME).is(movieName);
		Query query = Query.query(criteria);
               
		Long commentCount = mongoTemplate.count(query, COLLECTION_COMMENTS);
    Integer result = commentCount.intValue();        
		return result;
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	//
	/*
	db.comments.insert({
		"movieName": ?,
  	"name": ?,
		"rating": ?,
		"comment": ?
	})
	 */
	public Document insertComment(Comment c){
		Document doc = c.toDocument(c);
    return mongoTemplate.insert(doc,COLLECTION_COMMENTS);
  }

	
}
