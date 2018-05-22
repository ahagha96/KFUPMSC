package agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses;

/**
 * Created by User-Sai on 1/14/2018.
 */

public class DisplayInfo {

    private ChampionshipId championTitle;
    private RoundId roundTitle;
    private FirstTeam firstTeam;
    private SecondTeam secondTeam;
    private String date;
    private String firstTeamLogo;
    private String secondTeamLogo;

    private String firstTeamName;
    private String secondTeamName;

    public DisplayInfo(ChampionshipId championTitle, RoundId roundTitle, FirstTeam firstTeamName,
                       SecondTeam secondTeamName, String date) {
        this.championTitle = championTitle;
        this.roundTitle = roundTitle;
        this.firstTeamName = firstTeamName.getName();
        this.secondTeamName = secondTeamName.getName();
        this.date = date;
        this.firstTeamLogo = firstTeamName.getLogo();
        this.secondTeamLogo = secondTeamName.getLogo();
    }

    public String getChampionTitle() {
        return championTitle.getName();
    }

    public String getTitle(){
        return getChampionTitle()+" "+getRoundTitle();
    }

    public String getRoundTitle() {
        return roundTitle.getName();
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public String getDate() {
        return date;
    }

    public String getFirstTeamLogo() {
        return firstTeamLogo;
    }

    public String getSecondTeamLogo() {
        return secondTeamLogo;
    }
}
