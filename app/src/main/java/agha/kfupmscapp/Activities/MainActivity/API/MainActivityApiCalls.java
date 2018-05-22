package agha.kfupmscapp.Activities.MainActivity.API;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.Matches;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User-Sai on 1/13/2018.
 */

public interface MainActivityApiCalls {

    @GET("news/latest")
    Call<ArrayList<NewsPOJO>> getLatestNews();

    @GET("matches")
    Call<Matches> getMatches();

    @GET("championships/current/teams")
    Call<ArrayList<TeamsPOJO>> getCurrentTeams();

}
