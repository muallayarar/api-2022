package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {

    /*
      1)Create private variables for every key
      2)Create constructors with all parameters and without any parameter
      3)Create getters and setters
      4)Create toString() method
       */

    //Create private variables
    private Integer userId;
    private String title;
    private Boolean completed;

    //Create Constructors
    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }
    public JsonPlaceHolderPojo() {

    }

    //Generate getters and setters


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //Generate toString()

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    /*
    How do u handle different key-value in response body?
    @JsonIgnoreProperties(ignoreUnknown = true)
    It comes from org.codehaus.jackson.annotate.JsonIgnoreProperties
     */
}
