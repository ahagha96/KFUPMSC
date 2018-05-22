package agha.kfupmscapp.Activities.MainActivity.API;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User-Sai on 1/13/2018.
 */

public class TeamsPOJO {

    @SerializedName("id")
    private Integer id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("name")
    private String name;

    @SerializedName("logo")
    private String logo;

    @SerializedName("color")
    private Object color;

    @SerializedName("champs_no")
    private Integer champsNo;

    @SerializedName("color_hex")
    private Object colorHex;

    @SerializedName("color_id")
    private String colorId;

    @SerializedName("pivot")
    private Pivot pivot;

    public TeamsPOJO(Integer id, String createdAt, String updatedAt,
                     String name, String logo, Object color,
                     Integer champsNo, Object colorHex, String colorId,
                     Pivot pivot) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.logo = logo;
        this.color = color;
        this.champsNo = champsNo;
        this.colorHex = colorHex;
        this.colorId = colorId;
        this.pivot = pivot;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return "http://www.kfupmsc.com/storage/"+logo;
    }
}
