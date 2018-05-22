package agha.kfupmscapp.Activities.AllTeamsActivity.TeamsApiClasses;

/**
 * Created by User-Sai on 1/15/2018.
 */

public class DisplayObject {

    private String name;
    private String logo;
    private int id;

    public DisplayObject(String name, String logo, int id) {
        this.name = name;
        this.logo = logo;
        this.id = id ;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
