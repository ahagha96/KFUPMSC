package agha.kfupmscapp.Activities.NewsActivity;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.AboutSportClubActivity.AboutClubActivity;
import agha.kfupmscapp.Activities.AllPlayersActivity.AllPlayersActivity;
import agha.kfupmscapp.Activities.AllTeamsActivity.AllTeamsActivity;
import agha.kfupmscapp.Activities.ChampionsActivity.ChampoinsActivity;
import agha.kfupmscapp.Activities.DevTeamActivity.DevTeamActivity;
import agha.kfupmscapp.Activities.GroupsActivity.GroupsActivity;
import agha.kfupmscapp.Activities.MainActivity.MainActivity;
import agha.kfupmscapp.Activities.MatchesActivity.MatchesActivity;
import agha.kfupmscapp.Activities.NewsActivity.API.NewsActivityApiCalls;
import agha.kfupmscapp.Activities.NewsActivity.Adapters.AllNewsRecyclerViewAdapter;
import agha.kfupmscapp.Activities.MainActivity.API.MainActivityApiCalls;
import agha.kfupmscapp.Activities.MainActivity.API.NewsPOJO;
import agha.kfupmscapp.Activities.ScorerActivity.ScorerActivity;
import agha.kfupmscapp.R;
import agha.kfupmscapp.Utilities.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllNewsActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_menu_all_players)
    NavigationView navigationView;

    @BindView(R.id.all_players_burger)
    ImageView toggle;

    @BindView(R.id.all_news_rv)
    RecyclerView allNewsRecyclerView ;

    @BindView(R.id.all_news_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<NewsPOJO> news ;

    private ProgressDialog progressDialog ;

    private AllNewsRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);
        ButterKnife.bind(this);

        new GetNews().execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetNews().execute();
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
                        intent = new Intent(AllNewsActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_teams:
                        intent = new Intent(AllNewsActivity.this, AllTeamsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_players:
                        intent = new Intent(AllNewsActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_matches:
                        intent = new Intent(AllNewsActivity.this, MatchesActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_news:
                        //intent = new Intent(AllNewsActivity.this, AllNewsActivity.class);
                        //startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_scorers:
                        intent = new Intent(AllNewsActivity.this, ScorerActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_champions:
                        intent = new Intent(AllNewsActivity.this, ChampoinsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_groups:
                        intent = new Intent(AllNewsActivity.this, GroupsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_about:
                        intent = new Intent(AllNewsActivity.this, AboutClubActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_dev:
                        intent = new Intent(AllNewsActivity.this, DevTeamActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;
                }

                return true;
            }
        });
    }

    // get news async inner class
    private class GetNews extends AsyncTask<Void,Void,Void> {

        private NewsActivityApiCalls apiInterface ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // define apiInteface
            apiInterface = ApiClient.getApiClient().create(NewsActivityApiCalls.class);
            // show dialog
            progressDialog = new ProgressDialog(AllNewsActivity.this);
            progressDialog.setMessage("Loading ...");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<ArrayList<NewsPOJO>> call = apiInterface.getNews();
            call.enqueue(new Callback<ArrayList<NewsPOJO>>() {
                @Override
                public void onResponse(Call<ArrayList<NewsPOJO>> call, Response<ArrayList<NewsPOJO>> response) {
                    // define news array list
                    news = new ArrayList<>();
                    // assign news array list to response array list
                    news = response.body();
                    // create adapter
                    recyclerViewAdapter = new AllNewsRecyclerViewAdapter(news,AllNewsActivity.this);
                    allNewsRecyclerView.setLayoutManager(new LinearLayoutManager(AllNewsActivity.this,LinearLayoutManager.VERTICAL,false));
                    allNewsRecyclerView.setAdapter(recyclerViewAdapter);
                }
                @Override
                public void onFailure(Call<ArrayList<NewsPOJO>> call, Throwable t) {
                    Log.e("ErrorGetNews","Error");
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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // hide dialog
            progressDialog.dismiss();
        }
    }
}
