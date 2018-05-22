package agha.kfupmscapp.Activities.AllPlayersActivity.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User-Sai on 1/17/2018.
 */

public interface AllPlayersApiCalls {

    @GET("players/all")
    Call<Player> getPlayers(@Query("page") int pageNum);

    @GET("players/all")
    Call<Player> searchPlayer(@Query("search") String name);

}
