package agha.kfupmscapp.Utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User-Sai on 1/13/2018.
 */

// retrofit object that will be used in calling

public class ApiClient {

    public static final String BASE_URL = "http://kfupmsc.com/api/v1/";
    public static Retrofit retrofit = null ;

    public static Retrofit getApiClient(){
        if (retrofit == null)
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }
}
