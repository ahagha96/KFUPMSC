
package agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Matches {

    @SerializedName("current_page")
    @Expose
    public Integer currentPage;
    @SerializedName("data")
    @Expose
    public List<Data> data = null;
    @SerializedName("from")
    @Expose
    public Integer from;
    @SerializedName("last_page")
    @Expose
    public Integer lastPage;
    @SerializedName("next_page_url")
    @Expose
    public Object nextPageUrl;
    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("per_page")
    @Expose
    public Integer perPage;
    @SerializedName("prev_page_url")
    @Expose
    public Object prevPageUrl;
    @SerializedName("to")
    @Expose
    public Integer to;
    @SerializedName("total")
    @Expose
    public Integer total;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Matches() {
    }

    /**
     * 
     * @param total
     * @param to
     * @param lastPage
     * @param nextPageUrl
     * @param prevPageUrl
     * @param path
     * @param data
     * @param perPage
     * @param from
     * @param currentPage
     */
    public Matches(Integer currentPage, List<Data> data, Integer from, Integer lastPage, Object nextPageUrl, String path, Integer perPage, Object prevPageUrl, Integer to, Integer total) {
        super();
        this.currentPage = currentPage;
        this.data = data;
        this.from = from;
        this.lastPage = lastPage;
        this.nextPageUrl = nextPageUrl;
        this.path = path;
        this.perPage = perPage;
        this.prevPageUrl = prevPageUrl;
        this.to = to;
        this.total = total;
    }

    public List<Data> getData() {
        return data;
    }

    public Integer getNextPage() {
        return currentPage+1;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }
}
