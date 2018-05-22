package agha.kfupmscapp.Activities.ChampionsActivity.API;

import agha.kfupmscapp.Activities.ChampionsActivity.API.ChampionClasses.Champion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User-Sai on 1/17/2018.
 */

public interface ChampionActivityApiCalls {

    @GET("champions")
    Call<Champion> getNextMatches(@Query("page") int pageNum);

}
