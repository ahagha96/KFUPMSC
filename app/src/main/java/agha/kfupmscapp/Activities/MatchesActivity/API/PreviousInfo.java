package agha.kfupmscapp.Activities.MatchesActivity.API;

import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.ChampionshipId;
import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.FirstTeam;
import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.RoundId;
import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.SecondTeam;

/**
 * Created by User-Sai on 1/14/2018.
 */

public class PreviousInfo {

    private ChampionshipId championTitle;
    private RoundId roundTitle;
    private FirstTeam firstTeam;
    private SecondTeam secondTeam;
    private String date;
    private String firstTeamLogo;
    private String secondTeamLogo;

    private String firstTeamName;
    private String secondTeamName;
    private int firstTeamScore;
    private int secondTeamScore;

    private String oneDes;
    private String twoDes;

    public PreviousInfo(ChampionshipId championTitle, RoundId roundTitle, FirstTeam firstTeamName,
                        SecondTeam secondTeamName, String date, int oneScore, int twoScore, String des1, String des2) {
        this.championTitle = championTitle;
        this.roundTitle = roundTitle;
        this.firstTeamName = firstTeamName.getName();
        this.secondTeamName = secondTeamName.getName();
        this.date = date;
        this.firstTeamLogo = firstTeamName.getLogo();
        this.secondTeamLogo = secondTeamName.getLogo();
        firstTeamScore = oneScore;
        secondTeamScore = twoScore;
        oneDes = des1;
        twoDes = des2;
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

    public int getFirstTeamScore() {
        return firstTeamScore;
    }

    public int getSecondTeamScore() {
        return secondTeamScore;
    }

    public String getOneDes() {
        return oneDes;
    }

    public String getTwoDes() {
        return twoDes;
    }
}
