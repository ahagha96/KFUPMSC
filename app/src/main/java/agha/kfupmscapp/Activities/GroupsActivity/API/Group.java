
package agha.kfupmscapp.Activities.GroupsActivity.API;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("championship_id")
    @Expose
    public ChampionshipId championshipId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("teams")
    @Expose
    public List<Team> teams = null;
    @SerializedName("letter")
    @Expose
    public String letter;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Group() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param teams
     * @param championshipId
     * @param letter
     * @param createdAt
     * @param name
     */
    public Group(Integer id, String name, ChampionshipId championshipId, String createdAt, String updatedAt, List<Team> teams, String letter) {
        super();
        this.id = id;
        this.name = name;
        this.championshipId = championshipId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.teams = teams;
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    public List<Team> getTeams() {
        return teams;
    }


}
