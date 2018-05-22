package agha.kfupmscapp.Activities.DisplayPlayerActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.DisplayPlayerActivity.API.DisplayPlayerCall;
import agha.kfupmscapp.Activities.DisplayPlayerActivity.API.Player;
import agha.kfupmscapp.Activities.DisplayTeamActivity.API.DisplayTeamApiCalls;
import agha.kfupmscapp.R;
import agha.kfupmscapp.Utilities.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayPlayerActivity extends AppCompatActivity {

    @BindView(R.id.rl)
    RelativeLayout relativeLayout;

    @BindView(R.id.player_image)
    CircleImageView playerImage;

    @BindView(R.id.player_name)
    TextView playerName;

    @BindView(R.id.position)
    TextView position;

    @BindView(R.id.goals)
    TextView goals;

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.team_name)
    TextView teamName;

    private Player playerObject;
    private DisplayPlayerCall apiInterface ;
    private int teamID;
    private int playerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_player);
        ButterKnife.bind(this);

        // extract teamId and playerId
        Intent intent = getIntent();
        teamID = intent.getIntExtra("teamID",0);
        playerID = intent.getIntExtra("playerID",0);
        Log.e("teamID",teamID+"");
        Log.e("playerID",playerID+"");

        // get player's info
        new GetPlayerInfo().execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetPlayerInfo().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private class GetPlayerInfo extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // init
            apiInterface = ApiClient.getApiClient().create(DisplayPlayerCall.class);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Call<Player> call = apiInterface.getPlayer(teamID,playerID);
            call.enqueue(new Callback<Player>() {
                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    playerObject = response.body();
                    // bind
                    Picasso.with(DisplayPlayerActivity.this).load(playerObject.getProfilePic()).noFade().into(playerImage);
                    playerName.setText(playerObject.getName());
                    teamName.setText(playerObject.getTeamId().getName());
                    goals.setText(String.valueOf(playerObject.getGoalsNo()));
                    if (playerObject.getPosition() != null){
                        switch (playerObject.getPosition()){
                            case 1:
                                position.setText("مهاجم");
                                break;
                            case 2:
                                position.setText("وسط");
                                break;
                            case 3:
                                position.setText("مدافع");
                                break;
                            case 4:
                                position.setText("حارس");
                                break;
                        }
                    } else {
                        position.append("-");
                    }
                }
                @Override
                public void onFailure(Call<Player> call, Throwable t) {
                    Log.e("ErrorGetPlayerInfo","Error");
                    //show red snack bar
                    Snackbar snackbar = Snackbar.make(relativeLayout, "تأكد بأنك متصل بالإنترنت ومن ثم قم بتحديث الصفحة",Snackbar.LENGTH_LONG);
                    // get snackbar view
                    View snackbarView = snackbar.getView();
                    int snackbarTextId = android.support.design.R.id.snackbar_text;
                    TextView textView = (TextView)snackbarView.findViewById(snackbarTextId);
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                    textView.setTextColor(getResources().getColor(R.color.colorWhite));
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    snackbar.show();
                }
            });

            return null;
        }
    }


}
