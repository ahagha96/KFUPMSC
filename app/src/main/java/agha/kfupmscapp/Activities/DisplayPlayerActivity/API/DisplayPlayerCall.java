package agha.kfupmscapp.Activities.DisplayPlayerActivity.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User-Sai on 2/1/2018.
 */

public interface DisplayPlayerCall {

    @GET("teams/{teamID}/players/{playerID}")
    Call<Player> getPlayer(@Path("teamID") int teamID, @Path("playerID") int playerID) ;
}
