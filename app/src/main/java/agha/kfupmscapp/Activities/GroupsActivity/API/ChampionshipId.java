
package agha.kfupmscapp.Activities.GroupsActivity.API;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChampionshipId {

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
    public Object logo;
    @SerializedName("seaason_date")
    @Expose
    public String seaasonDate;
    @SerializedName("semester")
    @Expose
    public Integer semester;
    @SerializedName("champion_team")
    @Expose
    public Integer championTeam;
    @SerializedName("scorer_player")
    @Expose
    public Integer scorerPlayer;
    @SerializedName("best_player")
    @Expose
    public Integer bestPlayer;
    @SerializedName("best_keeper")
    @Expose
    public Integer bestKeeper;
    @SerializedName("teams")
    @Expose
    public List<Object> teams = null;
    @SerializedName("is_current")
    @Expose
    public Integer isCurrent;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ChampionshipId() {
    }

    /**
     * 
     * @param scorerPlayer
     * @param updatedAt
     * @param id
     * @param teams
     * @param logo
     * @param seaasonDate
     * @param createdAt
     * @param name
     * @param championTeam
     * @param isCurrent
     * @param bestKeeper
     * @param bestPlayer
     * @param semester
     */
    public ChampionshipId(Integer id, String createdAt, String updatedAt, String name, Object logo, String seaasonDate, Integer semester, Integer championTeam, Integer scorerPlayer, Integer bestPlayer, Integer bestKeeper, List<Object> teams, Integer isCurrent) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.logo = logo;
        this.seaasonDate = seaasonDate;
        this.semester = semester;
        this.championTeam = championTeam;
        this.scorerPlayer = scorerPlayer;
        this.bestPlayer = bestPlayer;
        this.bestKeeper = bestKeeper;
        this.teams = teams;
        this.isCurrent = isCurrent;
    }

}
