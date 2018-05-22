package agha.kfupmscapp.Activities.DisplayTeamActivity.API;

/**
 * Created by User-Sai on 1/21/2018.
 */

public class DisplayPlayerInfo {

    private String name;
    private String logo;
    private Integer position;
    private Integer playerID;
    private Integer teamID;

    public DisplayPlayerInfo(String name, String logo, Integer position, Integer teamID, Integer playerID) {
        this.name = name;
        this.logo = logo;
        this.position = position;
        this.playerID = playerID;
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public Integer getTeamID() {
        return teamID;
    }
}
