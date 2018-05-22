package agha.kfupmscapp.Activities.DisplayPlayerActivity.API;

/**
 * Created by User-Sai on 2/1/2018.
 */

public class PlayerInfo {

    private String name;
    private String image;
    private int goals;
    private int position;
    private String teamName;

    public PlayerInfo(String name, String image, int goals, int position, String teamName) {
        this.name = name;
        this.image = image;
        this.goals = goals;
        this.position = position;
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getGoals() {
        return goals;
    }

    public int getPosition() {
        return position;
    }

    public String getTeamName() {
        return teamName;
    }
}
