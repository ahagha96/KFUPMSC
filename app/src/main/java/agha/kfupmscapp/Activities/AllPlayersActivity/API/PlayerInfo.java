package agha.kfupmscapp.Activities.AllPlayersActivity.API;

/**
 * Created by User-Sai on 1/17/2018.
 */

public class PlayerInfo {

    private String image;
    private String name;
    private Integer teamID;
    private Integer playerID;

    public PlayerInfo(String image, String name, Integer teamID, Integer playerID) {
        this.image = image;
        this.name = name;
        this.playerID = playerID;
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public Integer getPlayerID() {
        return playerID;
    }
}
