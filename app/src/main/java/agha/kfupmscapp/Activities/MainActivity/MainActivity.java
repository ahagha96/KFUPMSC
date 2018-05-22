package agha.kfupmscapp.Activities.MainActivity;

import android.app.ProgressDialog;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.demono.AutoScrollViewPager;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.AboutSportClubActivity.AboutClubActivity;
import agha.kfupmscapp.Activities.AllPlayersActivity.AllPlayersActivity;
import agha.kfupmscapp.Activities.AllTeamsActivity.AllTeamsActivity;
import agha.kfupmscapp.Activities.ChampionsActivity.ChampoinsActivity;
import agha.kfupmscapp.Activities.DevTeamActivity.DevTeamActivity;
import agha.kfupmscapp.Activities.GroupsActivity.GroupsActivity;
import agha.kfupmscapp.Activities.MainActivity.API.MainActivityApiCalls;
import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.DisplayInfo;
import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.Matches;
import agha.kfupmscapp.Activities.MainActivity.API.NewsPOJO;
import agha.kfupmscapp.Activities.MainActivity.API.TeamsPOJO;
import agha.kfupmscapp.Activities.MainActivity.Adapters.TeamsRecyclerViewAdapter;
import agha.kfupmscapp.Activities.MainActivity.Adapters.ViewPagerMatchesAdapter;
import agha.kfupmscapp.Activities.MainActivity.Adapters.ViewPagerNewsAdapter;
import agha.kfupmscapp.Activities.MatchesActivity.MatchesActivity;
import agha.kfupmscapp.Activities.NewsActivity.AllNewsActivity;
import agha.kfupmscapp.Activities.ScorerActivity.ScorerActivity;
import agha.kfupmscapp.R;
import agha.kfupmscapp.Utilities.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_menu_all_players)
    NavigationView navigationView;

    @BindView(R.id.all_players_burger)
    ImageView toggle;

    @BindView(R.id.main_layout_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.viewPagerNews)
    AutoScrollViewPager viewPagerNews;

    @BindView(R.id.viewPagerMatches)
    AutoScrollViewPager viewPagerMatches;

    @BindView(R.id.main_layout_recyclerview)
    RecyclerView teamRecyclerView;

    // loading progressDialog when downloading news
    private ProgressDialog progressDialog ;

    // news view pager adapter
    ViewPagerNewsAdapter viewPagerNewsAdapter;

    // next matches view pager adapter
    ViewPagerMatchesAdapter viewPagerMatchesAdapter;

    // news array list to pass it into adapter
    ArrayList<NewsPOJO> news ;

    // teams array list to pass it into adapter
    ArrayList<TeamsPOJO> teams ;

    // matches array list to pass it into adapter
    ArrayList<DisplayInfo> displayMatches;

    // matches response array list
    Matches matches ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind butterknife
        ButterKnife.bind(this);

        // get News async
        new GetNews().execute();

        // get teams async
        new GetTeams().execute();

        // get matches async
        new GetNextMatches().execute();

        // swipe listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // get News async
                new GetNews().execute();

                // get teams async
                new GetTeams().execute();

                // get matches async
                new GetNextMatches().execute();

                // dismiss
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
                        //intent = new Intent(MainActivity.this, MainActivity.class);
                        //startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_teams:
                        intent = new Intent(MainActivity.this, AllTeamsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_players:
                        intent = new Intent(MainActivity.this, AllPlayersActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_matches:
                        intent = new Intent(MainActivity.this, MatchesActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_news:
                        intent = new Intent(MainActivity.this, AllNewsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_scorers:
                        intent = new Intent(MainActivity.this, ScorerActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_champions:
                        intent = new Intent(MainActivity.this, ChampoinsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_groups:
                        intent = new Intent(MainActivity.this, GroupsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_about:
                        intent = new Intent(MainActivity.this, AboutClubActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_dev:
                        intent = new Intent(MainActivity.this, DevTeamActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;
                }

                return true;
            }
        });


    }

    // get news async inner class
    private class GetNews extends AsyncTask<Void,Void,Void>{

        private MainActivityApiCalls apiInterface ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // define apiInteface
            apiInterface = ApiClient.getApiClient().create(MainActivityApiCalls.class);
            // show dialog
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading ...");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<ArrayList<NewsPOJO>> call = apiInterface.getLatestNews();
            call.enqueue(new Callback<ArrayList<NewsPOJO>>() {
                @Override
                public void onResponse(Call<ArrayList<NewsPOJO>> call, Response<ArrayList<NewsPOJO>> response) {
                    // define news array list
                    news = new ArrayList<>();

                    // assign news array list to response array list
                    news = response.body();

                    // view only 4 news
                    if (news.size()>4) {
                        ArrayList<NewsPOJO> copy = new ArrayList<>(news.subList(0,4));
                        viewPagerNewsAdapter = new ViewPagerNewsAdapter(MainActivity.this, copy);
                    }
                    else
                        viewPagerNewsAdapter = new ViewPagerNewsAdapter(MainActivity.this,news);

                    // create adapter
                    viewPagerNews.setAdapter(viewPagerNewsAdapter);
                    // set properites
                    viewPagerNews.setCycle(true);
                    // start scorlling
                    viewPagerNews.startAutoScroll();

                }
                @Override
                public void onFailure(Call<ArrayList<NewsPOJO>> call, Throwable t) {
                    Log.e("ErrorGetNews","Error");
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // hide dialog
            progressDialog.dismiss();
        }
    }

    // get teams async innter class
    private class GetTeams extends AsyncTask<Void,Void,Void>{

        private MainActivityApiCalls apiInterface ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // define apiInterface
            apiInterface = ApiClient.getApiClient().create(MainActivityApiCalls.class);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<ArrayList<TeamsPOJO>> call = apiInterface.getCurrentTeams();
            call.enqueue(new Callback<ArrayList<TeamsPOJO>>() {
                @Override
                public void onResponse(Call<ArrayList<TeamsPOJO>> call, Response<ArrayList<TeamsPOJO>> response) {
                    // define news array list
                    teams = new ArrayList<>();

                    // assign news array list to response array list
                    teams = response.body();

                    // create adapter
                    TeamsRecyclerViewAdapter adapter = new TeamsRecyclerViewAdapter(teams,MainActivity.this);
                    teamRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,true));
                    teamRecyclerView.setAdapter(adapter);
                }
                @Override
                public void onFailure(Call<ArrayList<TeamsPOJO>> call, Throwable t) {
                    Log.e("ErrorGetTeams","Error");
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

    // get matches async
    private class GetNextMatches extends AsyncTask<Void,Void,Void>{

        private MainActivityApiCalls apiInterface ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // define apiInteface
            apiInterface = ApiClient.getApiClient().create(MainActivityApiCalls.class);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Call<Matches> call = apiInterface.getMatches();
            call.enqueue(new Callback<Matches>() {
                @Override
                public void onResponse(Call<Matches> call, Response<Matches> response) {
                    matches = response.body();
                    displayMatches = new ArrayList<>();
                    // add matches
                    // display only 3 matches
                    if (matches.getData().size()> 6)
                        for (int i = 0 ; i < 6 ; i++){
                            displayMatches.add(new DisplayInfo
                                (matches.getData().get(i).getChampionshipId(),
                                        matches.getData().get(i).getRoundId(),
                                        matches.getData().get(i).getFirstTeam(),
                                        matches.getData().get(i).getSecondTeam(),
                                        matches.getData().get(i).getDate()));
                    }
                    else
                        for (int i = 0 ; i < matches.getData().size() ; i++) {
                            displayMatches.add(new DisplayInfo
                                    (matches.getData().get(i).getChampionshipId(),
                                            matches.getData().get(i).getRoundId(),
                                            matches.getData().get(i).getFirstTeam(),
                                            matches.getData().get(i).getSecondTeam(),
                                            matches.getData().get(i).getDate()));
                        }

                    // create adapter
                    viewPagerMatchesAdapter = new ViewPagerMatchesAdapter(MainActivity.this,displayMatches);
                    // set adapter
                    viewPagerMatches.setAdapter(viewPagerMatchesAdapter);
                    // set properites
                    viewPagerMatches.setCycle(true);
                    // start scorlling
                    viewPagerMatches.startAutoScroll();

                }

                @Override
                public void onFailure(Call<Matches> call, Throwable t) {
                    Log.e("ErrorGetMatches","Error");
                }
            });
            return null;
        }
    }
}
