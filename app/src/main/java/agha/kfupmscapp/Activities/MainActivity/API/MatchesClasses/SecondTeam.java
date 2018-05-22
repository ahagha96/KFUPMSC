
package agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecondTeam {

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
    public String logo;
    @SerializedName("color")
    @Expose
    public Object color;
    @SerializedName("champs_no")
    @Expose
    public Integer champsNo;
    @SerializedName("color_hex")
    @Expose
    public Object colorHex;
    @SerializedName("color_id")
    @Expose
    public String colorId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SecondTeam() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param logo
     * @param champsNo
     * @param color
     * @param createdAt
     * @param colorId
     * @param name
     * @param colorHex
     */
    public SecondTeam(Integer id, String createdAt, String updatedAt, String name, String logo, Object color, Integer champsNo, Object colorHex, String colorId) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.logo = logo;
        this.color = color;
        this.champsNo = champsNo;
        this.colorHex = colorHex;
        this.colorId = colorId;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return "http://www.kfupmsc.com/storage/"+logo;
    }
}
