package agha.kfupmscapp.Activities.GroupsActivity.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User-Sai on 1/22/2018.
 */

public interface GroupApiCall {

    @GET("groups")
    Call<ArrayList<Group>> getGroups();
}
