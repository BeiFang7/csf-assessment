package sg.nus.edu.iss.csf.assessment.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
  private String movieName;
  private String name;
  private Integer rating;
  private String comment;

  public String getMovieName() {
    return movieName;
  }
  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getRating() {
    return rating;
  }
  public void setRating(Integer rating) {
    this.rating = rating;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }

  public static Comment createComment(Document doc){
    Comment c = new Comment();
    c.setMovieName(doc.getObjectId("movieName").toString());
    c.setName(doc.getString("name"));
    c.setRating(doc.getInteger("rating"));
    c.setComment(doc.getString("comment"));
    return c;
  }

  public JsonObject toJSON(){
    return Json.createObjectBuilder()
              .add("movieName", getMovieName())
              .add("name",getName())
              .add("rating",getRating())
              .add("comment", getComment())
              .build();
  }

  public Document toDocument(Comment c){
    Document doc = new Document();
    doc.put("movieName",c.getMovieName());
    doc.put("name",c.getName());
    doc.put("rating",c.getRating());
    doc.put("comment", c.getComment());
    return doc;
  }

  
  
}
