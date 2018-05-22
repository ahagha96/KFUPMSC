package agha.kfupmscapp.Activities.MatchesActivity.API;


import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.Matches;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User-Sai on 1/16/2018.
 */

public interface MatchesActivityApiCalls {

    @GET("matches")
    Call<Matches> getNextMatches(@Query("page") int pageNum);

    @GET("results")
    Call<Matches> getPreviousMatches(@Query("page") int pageNum);

}
