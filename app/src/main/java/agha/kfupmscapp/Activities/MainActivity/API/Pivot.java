package agha.kfupmscapp.Activities.MainActivity.API;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User-Sai on 1/13/2018.
 */

public class Pivot {

    @SerializedName("championship_id")
    private Integer championshipId;

    @SerializedName("team_id")
    private Integer teamId;

    public Pivot(Integer championshipId, Integer teamId) {
        super();
        this.championshipId = championshipId;
        this.teamId = teamId;
    }

    public Integer getChampionshipId() {
        return championshipId;
    }

    public void setChampionshipId(Integer championshipId) {
        this.championshipId = championshipId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
