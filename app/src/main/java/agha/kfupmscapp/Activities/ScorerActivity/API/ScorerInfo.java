package agha.kfupmscapp.Activities.ScorerActivity.API;

/**
 * Created by User-Sai on 1/17/2018.
 */

public class ScorerInfo {

    private int rank;
    private String scorerName;
    private String scorerImage;
    private String teamName;
    private String teamImage;
    private int goals;
    private int playerID;
    private int teamID;

    public ScorerInfo(int rank, String scorerName, String scorerImage, String teamName, String teamImage, int goals, int playerID
    , int teamID) {
        this.rank = rank;
        this.scorerName = scorerName;
        this.scorerImage = scorerImage;
        this.teamName = teamName;
        this.teamImage = teamImage;
        this.goals = goals;
        this.playerID = playerID;
        this.teamID = teamID ;
    }

    public int getRank() {
        return rank;
    }

    public String getScorerName() {
        return scorerName;
    }

    public String getScorerImage() {
        return scorerImage;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public int getGoals() {
        return goals;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getTeamID() {
        return teamID;
    }
}
