package agha.kfupmscapp.Activities.GroupsActivity.API;

import java.util.List;

/**
 * Created by User-Sai on 1/22/2018.
 */

public class DisplayGroupInfo {

    private String letter;
    private List<Team> arrayList;

    public DisplayGroupInfo(String letter, List<Team> arrayList) {
        this.letter = letter;
        this.arrayList = arrayList;
    }

    public String getLetter() {
        return letter;
    }

    public List<Team> getArrayList() {
        return arrayList;
    }
}
