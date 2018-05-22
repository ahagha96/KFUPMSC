
package agha.kfupmscapp.Activities.DisplayTeamActivity.API;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamInfo {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("color")
    @Expose
    public Object color;
    @SerializedName("champs_no")
    @Expose
    public Integer champsNo;
    @SerializedName("color_hex")
    @Expose
    public Object colorHex;
    @SerializedName("color_id")
    @Expose
    public ColorId colorId;
    @SerializedName("goals_no")
    @Expose
    public Integer goalsNo;
    @SerializedName("home_matches")
    @Expose
    public List<HomeMatch> homeMatches = null;
    @SerializedName("away_matches")
    @Expose
    public List<AwayMatch> awayMatches = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TeamInfo() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param homeMatches
     * @param logo
     * @param goalsNo
     * @param champsNo
     * @param color
     * @param createdAt
     * @param colorId
     * @param name
     * @param awayMatches
     * @param colorHex
     */
    public TeamInfo(Integer id, String createdAt, String updatedAt, String name, String logo, Object color, Integer champsNo, Object colorHex, ColorId colorId, Integer goalsNo, List<HomeMatch> homeMatches, List<AwayMatch> awayMatches) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.logo = logo;
        this.color = color;
        this.champsNo = champsNo;
        this.colorHex = colorHex;
        this.colorId = colorId;
        this.goalsNo = goalsNo;
        this.homeMatches = homeMatches;
        this.awayMatches = awayMatches;
    }

    public String getLogo() {
        return "http://kfupmsc.com/storage/"+logo;
    }

    public Integer getGoalsNo() {
        return goalsNo;
    }

    public Integer getChampsNo() {
        return champsNo;
    }

    public String getName() {
        return name;
    }
}
