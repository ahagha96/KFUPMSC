package agha.kfupmscapp.Activities.MainActivity.API;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User-Sai on 1/13/2018.
 */

public class NewsPOJO {

    @SerializedName("id")
    private int id ;

    @SerializedName("created_at")
    private String createdAt ;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("title")
    private String header ;

    @SerializedName("body")
    private String body ;

    @SerializedName("main_image")
    private String imageURL;

    @SerializedName("user_id")
    private int userID;

    public NewsPOJO(int id, String createdAt, String updatedAt, String header, String body, String imageURL, int userID) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.header = header;
        this.body = body;
        this.imageURL = imageURL;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public String getImageURL() {
        return "http://www.kfupmsc.com/storage/"+imageURL;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt(){ return createdAt; }
}
