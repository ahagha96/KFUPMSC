
package agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

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
    public FirstTeam firstTeam;
    @SerializedName("second_team")
    @Expose
    public SecondTeam secondTeam;
    @SerializedName("first_team_description")
    @Expose
    public Object firstTeamDescription;
    @SerializedName("second_team_description")
    @Expose
    public Object secondTeamDescription;
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
    public RoundId roundId;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("points_counted")
    @Expose
    public Object pointsCounted;
    @SerializedName("championship_id")
    @Expose
    public ChampionshipId championshipId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
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
    public Data(Integer id, String createdAt, String updatedAt, FirstTeam firstTeam, SecondTeam secondTeam, Object firstTeamDescription, Object secondTeamDescription, Integer firstTeamGoals, Integer secondTeamGoals, Integer isFinished, String stadium, RoundId roundId, String date, Object pointsCounted, ChampionshipId championshipId) {
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

    public FirstTeam getFirstTeam() {
        return firstTeam;
    }

    public SecondTeam getSecondTeam() {
        return secondTeam;
    }

    public RoundId getRoundId() {
        return roundId;
    }

    public String getDate() {
        return date;
    }

    public ChampionshipId getChampionshipId() {
        return championshipId;
    }

    public Integer getFirstTeamGoals() {
        return firstTeamGoals;
    }

    public Integer getSecondTeamGoals() {
        return secondTeamGoals;
    }

    public Object getFirstTeamDescription() {
        return firstTeamDescription;
    }

    public Object getSecondTeamDescription() {
        return secondTeamDescription;
    }
}
