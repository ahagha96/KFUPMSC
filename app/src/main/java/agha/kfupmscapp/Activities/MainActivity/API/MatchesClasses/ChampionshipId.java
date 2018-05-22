
package agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChampionshipId {

    @SerializedName("id")
    public Integer id;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("name")
    public String name;
    @SerializedName("logo")
    public Object logo;
    @SerializedName("seaason_date")
    public String seaasonDate;
    @SerializedName("semester")
    public Integer semester;
    @SerializedName("champion_team")
    public Integer championTeam;
    @SerializedName("scorer_player")
    public Integer scorerPlayer;
    @SerializedName("best_player")
    public Integer bestPlayer;
    @SerializedName("best_keeper")
    public Integer bestKeeper;
    @SerializedName("teams")
    public List<Object> teams = null;
    @SerializedName("is_current")
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

    public String getName() {
        return name;
    }
}
