package agha.kfupmscapp.Activities.DisplayTeamActivity.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User-Sai on 1/21/2018.
 */

public interface DisplayTeamApiCalls {

    @GET("teams/{team}/players")
    Call<ArrayList<TeamMember>> getTeamPlayers(@Path("team") int teamID) ;

    @GET("teams/{teamId}/matches")
    Call<ArrayList<TeamMatch>> getTeamMatches(@Path("teamId") int teamID);

    @GET("teams/{teamId}")
    Call<TeamInfo> getTeamInfo(@Path("teamId") int teamID);
}
