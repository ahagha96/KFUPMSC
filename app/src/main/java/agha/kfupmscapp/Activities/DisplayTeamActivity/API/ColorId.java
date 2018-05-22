
package agha.kfupmscapp.Activities.DisplayTeamActivity.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ColorId {

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
    @SerializedName("materialize_name")
    @Expose
    public String materializeName;
    @SerializedName("hex")
    @Expose
    public String hex;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ColorId() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param hex
     * @param materializeName
     * @param createdAt
     * @param name
     */
    public ColorId(Integer id, String createdAt, String updatedAt, String name, String materializeName, String hex) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.materializeName = materializeName;
        this.hex = hex;
    }

}
