package agha.kfupmscapp.Activities.ScorerActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.AboutSportClubActivity.AboutClubActivity;
import agha.kfupmscapp.Activities.AllPlayersActivity.AllPlayersActivity;
import agha.kfupmscapp.Activities.AllTeamsActivity.AllTeamsActivity;
import agha.kfupmscapp.Activities.ChampionsActivity.ChampoinsActivity;
import agha.kfupmscapp.Activities.DevTeamActivity.DevTeamActivity;
import agha.kfupmscapp.Activities.DisplayPlayerActivity.DisplayPlayerActivity;
import agha.kfupmscapp.Activities.GroupsActivity.GroupsActivity;
import agha.kfupmscapp.Activities.MainActivity.MainActivity;
import agha.kfupmscapp.Activities.MatchesActivity.MatchesActivity;
import agha.kfupmscapp.Activities.NewsActivity.AllNewsActivity;
import agha.kfupmscapp.Activities.ScorerActivity.API.Scorer;
import agha.kfupmscapp.Activities.ScorerActivity.API.ScorerInfo;
import agha.kfupmscapp.Activities.ScorerActivity.API.ScorersApiCalls;
import agha.kfupmscapp.R;
import agha.kfupmscapp.Utilities.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScorerActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_menu_all_players)
    NavigationView navigationView;

    @BindView(R.id.all_players_burger)
    ImageView toggle;

    @BindView(R.id.scorers_lv)
    ListView scorerListView;

    @BindView(R.id.scorer_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    // layout inflater for
    private LayoutInflater inflater ;

    // boolean value to stop calling -- default = false
    private boolean isFinish = false;

    // array list of teams to be displayed
    private ArrayList<ScorerInfo> scorerArrayList;

    // current page of calling
    private int nextPage;

    // current rank - start at 1
    private int rank = 1;

    // listview adapter
    private ListViewAdapter listViewAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        ButterKnife.bind(this);

        // init variables
        defineVariables();
        // set properties
        setListView();
        // call for scorer - default page = 1
        new GetScorers().execute(1);
        // swipe listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // call for scorer - default page = 1
                scorerArrayList.clear();
                rank = 1 ;
                new GetScorers().execute(1);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        // side menu
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });
        // nav menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // get id
                int id = item.getItemId();
                // intent object
                Intent intent;

                switch (id){

                    case R.id.nav_menu_main:
                        intent = new Intent(ScorerActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        finish();
                        break;

                    case R.id.nav_menu_teams:
                        intent = new Intent(ScorerActivity.this, AllTeamsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        finish();
                        break;

                    case R.id.nav_menu_players:
                        intent = new Intent(ScorerActivity.this, AllPlayersActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_matches:
                        intent = new Intent(ScorerActivity.this, MatchesActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_news:
                        intent = new Intent(ScorerActivity.this, AllNewsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        finish();
                        break;

                    case R.id.nav_menu_scorers:
                        //intent = new Intent(ScorerActivity.this, ScorerActivity.class);
                        //startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_champions:
                        intent = new Intent(ScorerActivity.this, ChampoinsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_groups:
                        intent = new Intent(ScorerActivity.this, GroupsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_about:
                        intent = new Intent(ScorerActivity.this, AboutClubActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_dev:
                        intent = new Intent(ScorerActivity.this, DevTeamActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;
                }

                return true;
            }
        });

    }

    private void defineVariables() {
        inflater = LayoutInflater.from(ScorerActivity.this);
        scorerArrayList = new ArrayList<>();
        listViewAdapter = new ListViewAdapter(scorerArrayList);
    }

    // set properties
    private void setListView() {
        // default adapter
        scorerListView.setAdapter(listViewAdapter);
    }

    // grid view adapter
    private class ListViewAdapter extends BaseAdapter {

        TextView rank,playerName,teamName,goals;
        CircleImageView playerImage;
        ImageView teamImage;
        ArrayList<ScorerInfo> arrayList ;

        public ListViewAdapter(ArrayList<ScorerInfo> arrayList){
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            // inflate layout
            view = inflater.inflate(R.layout.layout_rv_scorer,null);
            // define views
            rank = (TextView)view.findViewById(R.id.scorer_card_rank);
            playerImage = (CircleImageView) view.findViewById(R.id.scorer_card_player_image);
            playerName = (TextView) view.findViewById(R.id.scorer_card_player_name);
            teamImage = (ImageView)view.findViewById(R.id.scorer_card_team_image);
            teamName = (TextView)view.findViewById(R.id.scorer_card_team_name);
            goals = (TextView)view.findViewById(R.id.scorer_card_goals);
            // bind
            rank.setText(arrayList.get(i).getRank()+"");
            playerName.setText(arrayList.get(i).getScorerName());
            teamName.setText(arrayList.get(i).getTeamName());
            goals.setText(arrayList.get(i).getGoals()+"");
            Ion.with(ScorerActivity.this)
                    .load(arrayList.get(i).getTeamImage())
                    .withBitmap()
                    .intoImageView(teamImage);
            Picasso.with(ScorerActivity.this).load(arrayList.get(i).getScorerImage()).noFade().into(playerImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ScorerActivity.this, DisplayPlayerActivity.class);
                    intent.putExtra("playerID",arrayList.get(i).getPlayerID());
                    intent.putExtra("teamID",arrayList.get(i).getTeamID());
                    startActivity(intent);
                }
            });

            // check if last
            if (reachedEndOfList(i) && isFinish == false){
                new GetScorers().execute(nextPage);
            }

            return view;
        }

        private boolean reachedEndOfList(int i) {
            return i == getCount() - 1;
        }
    }

    // get all players async
    private class GetScorers extends AsyncTask<Integer,Void,Void> {

        private ScorersApiCalls apiInterface;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // define interface
            apiInterface = ApiClient.getApiClient().create(ScorersApiCalls.class);
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            Call<Scorer> call = apiInterface.getPlayers(integers[0]);
            call.enqueue(new Callback<Scorer>() {
                @Override
                public void onResponse(Call<Scorer> call, Response<Scorer> response) {
                    // extract data from response and add them to the adapter array list
                    for (int index = 0 ; index<response.body().getData().size() ; index++){
                        scorerArrayList.add(new ScorerInfo(rank,
                                response.body().getData().get(index).getName(),
                                response.body().getData().get(index).getProfilePic(),
                                response.body().getData().get(index).getTeamId().getName(),
                                response.body().getData().get(index).getTeamId().getLogo(),
                                response.body().getData().get(index).getGoalsNo(),
                                response.body().getData().get(index).getId(),
                                response.body().getData().get(index).getTeamId().getId()));
                        rank++ ;
                    }
                    // refresh adapter and remove loading
                    listViewAdapter.notifyDataSetChanged();
                    // refresh current value
                    nextPage = response.body().getNextPage();
                    // check if last page
                    if (response.body().getCurrentPage() == response.body().getLastPage()){
                        isFinish = true ;
                    }
                }

                @Override
                public void onFailure(Call<Scorer> call, Throwable t) {
                    Log.e("ErrorGetScorer","error");
                    //show red snack bar
                    Snackbar snackbar = Snackbar.make(drawerLayout, "تأكد بأنك متصل بالإنترنت ومن ثم قم بتحديث الصفحة",Snackbar.LENGTH_LONG);
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
