package agha.kfupmscapp.Activities.ScorerActivity.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User-Sai on 1/17/2018.
 */

public interface ScorersApiCalls {

    @GET("scorers")
    Call<Scorer> getPlayers(@Query("page") int pageNum);
}
