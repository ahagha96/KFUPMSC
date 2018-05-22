
package agha.kfupmscapp.Activities.DevTeamActivity.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Developer {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("twitter_account")
    @Expose
    public String twitterAccount;
    @SerializedName("email_account")
    @Expose
    public String emailAccount;
    @SerializedName("twitter_url")
    @Expose
    public Object twitterUrl;
    @SerializedName("email_url")
    @Expose
    public Object emailUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Developer() {
    }

    /**
     * 
     * @param emailUrl
     * @param updatedAt
     * @param id
     * @param twitterAccount
     * @param createdAt
     * @param image
     * @param role
     * @param twitterUrl
     * @param emailAccount
     * @param fullName
     */
    public Developer(Integer id, String createdAt, String updatedAt, String fullName, String role, String image, String twitterAccount, String emailAccount, Object twitterUrl, Object emailUrl) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fullName = fullName;
        this.role = role;
        this.image = image;
        this.twitterAccount = twitterAccount;
        this.emailAccount = emailAccount;
        this.twitterUrl = twitterUrl;
        this.emailUrl = emailUrl;
    }

    public String getImage() {
        return "http://www.kfupmsc.com/storage/"+image;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public String getTwitterAccount() {
        return twitterAccount;
    }

    public String getEmailAccount() {
        return emailAccount;
    }
}
