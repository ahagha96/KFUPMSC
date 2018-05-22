
package agha.kfupmscapp.Activities.GroupsActivity.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot {

    @SerializedName("group_id")
    @Expose
    public Integer groupId;
    @SerializedName("team_id")
    @Expose
    public Integer teamId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Pivot() {
    }

    /**
     * 
     * @param groupId
     * @param teamId
     */
    public Pivot(Integer groupId, Integer teamId) {
        super();
        this.groupId = groupId;
        this.teamId = teamId;
    }

}
