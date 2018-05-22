package agha.kfupmscapp.Activities.DevTeamActivity.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User-Sai on 1/26/2018.
 */

public interface DevTeamCall {

    @GET("devTeam")
    Call<ArrayList<Developer>> getDevelopers();
}
