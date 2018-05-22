package agha.kfupmscapp.Activities.ChampionsActivity.API.ChampionClasses;

/**
 * Created by User-Sai on 1/17/2018.
 */

public class ChampionInfo {

    private String title;
    private String championName;
    private String championLogo;
    private String bestPlayerName;
    private String bestPlayerLogo;
    private String bestKeeperName;
    private String bestKeeperLogo;
    private String scorerName;
    private String scorerLogo;

    public ChampionInfo(String title, String championName, String championLogo, String bestPlayerName,
                        String bestPlayerLogo, String bestKeeperName, String bestKeeperLogo,
                        String scorerName, String scorerLogo) {
        this.title = title;
        this.championName = championName;
        this.championLogo = championLogo;
        this.bestPlayerName = bestPlayerName;
        this.bestPlayerLogo = bestPlayerLogo;
        this.bestKeeperName = bestKeeperName;
        this.bestKeeperLogo = bestKeeperLogo;
        this.scorerName = scorerName;
        this.scorerLogo = scorerLogo;
    }

    public String getChampionName() {
        return championName;
    }

    public String getTitle() {
        return title;
    }

    public String getChampionLogo() {
        return championLogo;
    }

    public String getBestPlayerName() {
        return bestPlayerName;
    }

    public String getBestPlayerLogo() {
        return bestPlayerLogo;
    }

    public String getBestKeeperName() {
        return bestKeeperName;
    }

    public String getBestKeeperLogo() {
        return bestKeeperLogo;
    }

    public String getScorerName() {
        return scorerName;
    }

    public String getScorerLogo() {
        return scorerLogo;
    }
}
