
package agha.kfupmscapp.Activities.DisplayPlayerActivity.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("profile_pic")
    @Expose
    public String profilePic;
    @SerializedName("birth_date")
    @Expose
    public Object birthDate;
    @SerializedName("champs_no")
    @Expose
    public Integer champsNo;
    @SerializedName("university_id")
    @Expose
    public Integer universityId;
    @SerializedName("goals_no")
    @Expose
    public Integer goalsNo;
    @SerializedName("team_id")
    @Expose
    public TeamId teamId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("position")
    @Expose
    public Integer position;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Player() {
    }

    /**
     * 
     * @param position
     * @param updatedAt
     * @param id
     * @param goalsNo
     * @param champsNo
     * @param createdAt
     * @param universityId
     * @param name
     * @param profilePic
     * @param birthDate
     * @param teamId
     */
    public Player(Integer id, String name, String profilePic, Object birthDate, Integer champsNo, Integer universityId, Integer goalsNo, TeamId teamId, String createdAt, String updatedAt, Integer position) {
        super();
        this.id = id;
        this.name = name;
        this.profilePic = profilePic;
        this.birthDate = birthDate;
        this.champsNo = champsNo;
        this.universityId = universityId;
        this.goalsNo = goalsNo;
        this.teamId = teamId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public TeamId getTeamId() {
        return teamId;
    }

    public Integer getPosition() {
        return position;
    }

    public String getProfilePic() {
        return "http://kfupmsc.com/storage/"+profilePic;
    }

    public Integer getGoalsNo() {
        return goalsNo;
    }

    public String getName() {
        return name;
    }
}
