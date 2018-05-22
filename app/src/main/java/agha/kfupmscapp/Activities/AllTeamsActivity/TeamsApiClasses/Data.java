
package agha.kfupmscapp.Activities.AllTeamsActivity.TeamsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("color")
    @Expose
    private Object color;
    @SerializedName("champs_no")
    @Expose
    private Integer champsNo;
    @SerializedName("color_hex")
    @Expose
    private Object colorHex;
    @SerializedName("color_id")
    @Expose
    private String colorId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
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
    public Data(Integer id, String createdAt, String updatedAt, String name, String logo, Object color, Integer champsNo, Object colorHex, String colorId) {
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
        return "http://kfupmsc.com/storage/"+logo;
    }

    public Integer getId() {
        return id;
    }
}
