
package agha.kfupmscapp.Activities.DisplayTeamActivity.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwayMatch {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("first_team")
    @Expose
    public Integer firstTeam;
    @SerializedName("second_team")
    @Expose
    public Integer secondTeam;
    @SerializedName("first_team_description")
    @Expose
    public String firstTeamDescription;
    @SerializedName("second_team_description")
    @Expose
    public String secondTeamDescription;
    @SerializedName("first_team_goals")
    @Expose
    public Integer firstTeamGoals;
    @SerializedName("second_team_goals")
    @Expose
    public Integer secondTeamGoals;
    @SerializedName("is_finished")
    @Expose
    public Integer isFinished;
    @SerializedName("stadium")
    @Expose
    public String stadium;
    @SerializedName("round_id")
    @Expose
    public Integer roundId;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("points_counted")
    @Expose
    public Object pointsCounted;
    @SerializedName("championship_id")
    @Expose
    public Integer championshipId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AwayMatch() {
    }

    /**
     * 
     * @param secondTeam
     * @param championshipId
     * @param pointsCounted
     * @param date
     * @param firstTeam
     * @param id
     * @param updatedAt
     * @param firstTeamGoals
     * @param firstTeamDescription
     * @param secondTeamGoals
     * @param secondTeamDescription
     * @param createdAt
     * @param stadium
     * @param isFinished
     * @param roundId
     */
    public AwayMatch(Integer id, String createdAt, String updatedAt, Integer firstTeam, Integer secondTeam, String firstTeamDescription, String secondTeamDescription, Integer firstTeamGoals, Integer secondTeamGoals, Integer isFinished, String stadium, Integer roundId, String date, Object pointsCounted, Integer championshipId) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstTeamDescription = firstTeamDescription;
        this.secondTeamDescription = secondTeamDescription;
        this.firstTeamGoals = firstTeamGoals;
        this.secondTeamGoals = secondTeamGoals;
        this.isFinished = isFinished;
        this.stadium = stadium;
        this.roundId = roundId;
        this.date = date;
        this.pointsCounted = pointsCounted;
        this.championshipId = championshipId;
    }

}
