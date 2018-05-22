
package agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoundId {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("round_description")
    @Expose
    public Object roundDescription;
    @SerializedName("round_color")
    @Expose
    public Object roundColor;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RoundId() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param roundColor
     * @param createdAt
     * @param name
     * @param roundDescription
     */
    public RoundId(Integer id, String name, Object roundDescription, Object roundColor, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.roundDescription = roundDescription;
        this.roundColor = roundColor;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }
}
