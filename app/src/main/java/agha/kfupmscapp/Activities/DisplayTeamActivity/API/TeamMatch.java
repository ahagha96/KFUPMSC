
package agha.kfupmscapp.Activities.DisplayTeamActivity.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamMatch {

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
    public TeamMatch() {
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
    public TeamMatch(Integer id, String createdAt, String updatedAt, FirstTeam firstTeam, SecondTeam secondTeam, String firstTeamDescription, String secondTeamDescription, Integer firstTeamGoals, Integer secondTeamGoals, Integer isFinished, String stadium, RoundId roundId, String date, Object pointsCounted, ChampionshipId championshipId) {
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

    public Integer getFirstTeamGoals() {
        return firstTeamGoals;
    }

    public Integer getSecondTeamGoals() {
        return secondTeamGoals;
    }

    public String getFirstTeamName() {
        return firstTeam.getName();
    }

    public String getSecondTeamName() {
        return secondTeam.getName();
    }

    public String getFirstTeamLogo() {
        return firstTeam.getLogo();
    }

    public String getSecondTeamLogo() {
        return secondTeam.getLogo();
    }

    public RoundId getRoundId() {
        return roundId;
    }

    public String getTitle() { return getChampionshipId().getName()+ " " +getRoundId().getName() ; }

    public String getDate() {
        return date;
    }

    public ChampionshipId getChampionshipId() {
        return championshipId;
    }

    public String getFirstTeamDescription() {
        return firstTeamDescription;
    }

    public String getSecondTeamDescription() {
        return secondTeamDescription;
    }
}
