package agha.kfupmscapp.Activities.DisplayTeamActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.DisplayTeamActivity.API.DisplayMatchInfo;
import agha.kfupmscapp.Activities.DisplayTeamActivity.API.DisplayPlayerInfo;
import agha.kfupmscapp.Activities.DisplayTeamActivity.API.DisplayTeamApiCalls;
import agha.kfupmscapp.Activities.DisplayTeamActivity.API.TeamInfo;
import agha.kfupmscapp.Activities.DisplayTeamActivity.API.TeamMatch;
import agha.kfupmscapp.Activities.DisplayTeamActivity.API.TeamMember;
import agha.kfupmscapp.Activities.DisplayTeamActivity.Adapters.MatchesAdapter;
import agha.kfupmscapp.Activities.DisplayTeamActivity.Adapters.PlayersAdapter;
import agha.kfupmscapp.R;
import agha.kfupmscapp.Utilities.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayTeam extends AppCompatActivity {

    @BindView(R.id.display_team_image)
    ImageView teamImage;

    @BindView(R.id.display_team_name)
    TextView teamName;

    @BindView(R.id.display_team_title)
    TextView teamNameTitle;

    @BindView(R.id.champion_number)
    TextView champNo;

    @BindView(R.id.goals_number)
    TextView goalsNo;

    @BindView(R.id.team_number)
    TextView teamMembersNo;

    @BindView(R.id.players_rv)
    RecyclerView playersRecyclerView;

    @BindView(R.id.matches_rv)
    RecyclerView matchesRecyclerView;

    @BindView(R.id.display_team_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rl)
    RelativeLayout relativeLayout;

    private PlayersAdapter playersAdapter;
    private MatchesAdapter matchesAdapter;
    private ArrayList<TeamMember> members ;
    private ArrayList<DisplayPlayerInfo> playersInfo;
    private ArrayList<DisplayMatchInfo> matchesInfo;
    private int teamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_team);
        ButterKnife.bind(this);

        // init variables
        init();

        // get info
        new GetTeamInfo().execute();
        // get players
        new GetPlayers().execute();
        // get matches
        new GetMatchesInfo().execute();
        // swipe listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                members.clear();
                playersInfo.clear();
                matchesInfo.clear();
                // get info
                new GetTeamInfo().execute();
                // get players
                new GetPlayers().execute();
                // get matches
                new GetMatchesInfo().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void init() {
        // get teamID
        Intent intent = getIntent();
        teamID = intent.getIntExtra("teamID",0);

        members = new ArrayList<>();
        playersInfo = new ArrayList<>();
        matchesInfo = new ArrayList<>();
        playersAdapter = new PlayersAdapter(playersInfo,this);
        matchesAdapter = new MatchesAdapter(matchesInfo,this);
        playersRecyclerView.setLayoutManager(new LinearLayoutManager(DisplayTeam.this,LinearLayoutManager.HORIZONTAL,true));
        matchesRecyclerView.setLayoutManager(new LinearLayoutManager(DisplayTeam.this,LinearLayoutManager.HORIZONTAL,true));
        playersRecyclerView.setAdapter(playersAdapter);
        matchesRecyclerView.setAdapter(matchesAdapter);
    }


    // get players async inner class
    private class GetPlayers extends AsyncTask<Void,Void,Void> {

        private DisplayTeamApiCalls apiInterface ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // define apiInteface
            apiInterface = ApiClient.getApiClient().create(DisplayTeamApiCalls.class);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<ArrayList<TeamMember>> call = apiInterface.getTeamPlayers(teamID);
            call.enqueue(new Callback<ArrayList<TeamMember>>() {
                @Override
                public void onResponse(Call<ArrayList<TeamMember>> call, Response<ArrayList<TeamMember>> response) {
                    // assign news array list to response array list
                    members = response.body();
                    // display number of players
                    teamMembersNo.setText(String.valueOf(members.size()));
                    // fill players array
                    for (int i = 0 ; i<members.size() ; i++){
                        playersInfo.add(new DisplayPlayerInfo(members.get(i).getName(),
                                members.get(i).getProfilePic(),members.get(i).getPosition(),
                                members.get(i).getTeamId(),members.get(i).getId()));
                    }
                    // refresh adapter
                    playersAdapter.notifyDataSetChanged();

                }
                @Override
                public void onFailure(Call<ArrayList<TeamMember>> call, Throwable t) {
                    Log.e("ErrorGetPlayers","Error");
                }
            });

            return null;
        }
    }

    // get team info
    private class GetTeamInfo extends AsyncTask<Void,Void,Void> {

        private DisplayTeamApiCalls apiInterface ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // define apiInteface
            apiInterface = ApiClient.getApiClient().create(DisplayTeamApiCalls.class);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<TeamInfo> call = apiInterface.getTeamInfo(teamID);
            call.enqueue(new Callback<TeamInfo>() {
                @Override
                public void onResponse(Call<TeamInfo> call, final Response<TeamInfo> response) {
                    Picasso.with(DisplayTeam.this).load(response.body().getLogo()).into(teamImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            teamNameTitle.setText(response.body().getName());
                            teamName.setText(response.body().getName());
                            champNo.setText(String.valueOf(response.body().getChampsNo()));
                            goalsNo.setText(String.valueOf(response.body().getGoalsNo()));
                        }

                        @Override
                        public void onError() {

                        }
                    });
                }
                @Override
                public void onFailure(Call<TeamInfo> call, Throwable t) {
                    Log.e("ErrorGetTeamInfo","Error");
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

    // get matches
    private class GetMatchesInfo extends AsyncTask<Void,Void,Void> {

        private DisplayTeamApiCalls apiInterface ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // define apiInteface
            apiInterface = ApiClient.getApiClient().create(DisplayTeamApiCalls.class);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<ArrayList<TeamMatch>> call = apiInterface.getTeamMatches(teamID);
            call.enqueue(new Callback<ArrayList<TeamMatch>>() {
                @Override
                public void onResponse(Call<ArrayList<TeamMatch>> call, final Response<ArrayList<TeamMatch>> response) {
                    // fill array to display
                    for (int i = 0 ; i<response.body().size() ; i++){
                        matchesInfo.add(new DisplayMatchInfo(response.body().get(i).getDate(),
                                response.body().get(i).getTitle(),
                                response.body().get(i).getFirstTeamName(),
                                response.body().get(i).getSecondTeamName(),
                                response.body().get(i).getFirstTeamGoals(),
                                response.body().get(i).getSecondTeamGoals(),
                                response.body().get(i).getFirstTeamLogo(),
                                response.body().get(i).getSecondTeamLogo(),
                                response.body().get(i).getFirstTeamDescription(),
                                response.body().get(i).getSecondTeamDescription()));
                    }
                    matchesAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<ArrayList<TeamMatch>> call, Throwable t) {
                    Log.e("ErrorGetTeamMatches","Error");
                }
            });

            return null;
        }
    }
}
