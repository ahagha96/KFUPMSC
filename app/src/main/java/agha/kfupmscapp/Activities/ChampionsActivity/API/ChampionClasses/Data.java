
package agha.kfupmscapp.Activities.ChampionsActivity.API.ChampionClasses;

import java.util.List;
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
    public ChampionTeam championTeam;
    @SerializedName("scorer_player")
    @Expose
    public ScorerPlayer scorerPlayer;
    @SerializedName("best_player")
    @Expose
    public BestPlayer bestPlayer;
    @SerializedName("best_keeper")
    @Expose
    public BestKeeper bestKeeper;
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
    public Data() {
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
    public Data(Integer id, String createdAt, String updatedAt, String name, Object logo, String seaasonDate, Integer semester, ChampionTeam championTeam, ScorerPlayer scorerPlayer, BestPlayer bestPlayer, BestKeeper bestKeeper, List<Object> teams, Integer isCurrent) {
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

    public String getChampTitle() {
        return name;
    }

    public ChampionTeam getChampionTeam() {
        return championTeam;
    }

    public BestKeeper getBestKeeper() {
        return bestKeeper;
    }

    public BestPlayer getBestPlayer() {
        return bestPlayer;
    }

    public ScorerPlayer getScorerPlayer() {
        return scorerPlayer;
    }
}
