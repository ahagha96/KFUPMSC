package agha.kfupmscapp.Activities.DisplayTeamActivity.API;

/**
 * Created by User-Sai on 1/22/2018.
 */

public class DisplayMatchInfo {

    private String title;
    private String firstTeamName;
    private String secondTeamName;
    private int firstTeamScore;
    private int secondTeamScore;
    private String firstTeamLogo;
    private String secondTeamLogo;
    private String date;
    private String firstDes;
    private String secondDes;

    public DisplayMatchInfo(String date, String title, String firstTeamName, String secondTeamName,
                            int firstTeamScore, int secondTeamScore, String firstTeamLogo,
                            String secondTeamLogo,
                            String firstDes,
                            String secondDes) {
        this.date = date ;
        this.title = title;
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.firstTeamLogo = firstTeamLogo;
        this.secondTeamLogo = secondTeamLogo;
        this.firstDes = firstDes;
        this.secondDes = secondDes;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public int getFirstTeamScore() {
        return firstTeamScore;
    }

    public int getSecondTeamScore() {
        return secondTeamScore;
    }

    public String getFirstTeamLogo() {
        return firstTeamLogo;
    }

    public String getSecondTeamLogo() {
        return secondTeamLogo;
    }

    public String getFirstDes() {
        return firstDes;
    }

    public String getSecondDes() {
        return secondDes;
    }
}
