package agha.kfupmscapp.Activities.AllTeamsActivity.TeamsApiClasses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User-Sai on 1/15/2018.
 */

public interface AllTeamsApiCalls {

    @GET("teams")
    Call<TeamObject> getTeams(@Query("page") int pageNum);

    @GET("teams")
    Call<TeamObject> searchTeams(@Query("search") String name);

}
