package agha.kfupmscapp.Activities.NewsActivity.API;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.MainActivity.API.NewsPOJO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User-Sai on 1/14/2018.
 */

public interface NewsActivityApiCalls {

    @GET("news")
    Call<ArrayList<NewsPOJO>> getNews();

}
