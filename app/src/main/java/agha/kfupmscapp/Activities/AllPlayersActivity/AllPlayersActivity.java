package agha.kfupmscapp.Activities.AllPlayersActivity;

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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.AboutSportClubActivity.AboutClubActivity;
import agha.kfupmscapp.Activities.AllPlayersActivity.API.AllPlayersApiCalls;
import agha.kfupmscapp.Activities.AllPlayersActivity.API.Player;
import agha.kfupmscapp.Activities.AllPlayersActivity.API.PlayerInfo;
import agha.kfupmscapp.Activities.AllTeamsActivity.AllTeamsActivity;
import agha.kfupmscapp.Activities.ChampionsActivity.ChampoinsActivity;
import agha.kfupmscapp.Activities.DevTeamActivity.DevTeamActivity;
import agha.kfupmscapp.Activities.DisplayPlayerActivity.DisplayPlayerActivity;
import agha.kfupmscapp.Activities.GroupsActivity.GroupsActivity;
import agha.kfupmscapp.Activities.MainActivity.MainActivity;
import agha.kfupmscapp.Activities.MatchesActivity.MatchesActivity;
import agha.kfupmscapp.Activities.NewsActivity.AllNewsActivity;
import agha.kfupmscapp.Activities.ScorerActivity.API.Scorer;
import agha.kfupmscapp.Activities.ScorerActivity.ScorerActivity;
import agha.kfupmscapp.R;
import agha.kfupmscapp.Utilities.ApiClient;
import agha.kfupmscapp.Utilities.Utility;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPlayersActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_menu_all_players)
    NavigationView navigationView;

    @BindView(R.id.all_players_burger)
    ImageView toggle;

    @BindView(R.id.all_players_search_bar)
    EditText searchBar;

    @BindView(R.id.all_players_grid_view)
    GridView teamsGridView;

    @BindView(R.id.all_players_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    // gridview adapter
    private GridViewAdapter gridViewAdapter ;

    // gridview adapter
    private GridViewAdapter gridViewAdapterResults ;

    // layout inflater for
    private LayoutInflater inflater ;

    // boolean value to stop calling -- default = false
    private boolean isFinish = false;

    // array list of teams to be displayed
    private ArrayList<PlayerInfo> playersArrayList;

    // current page of calling
    private int nextPage;

    // search is on -- default is false
    private boolean isSearchOn = false ;

    // array list for search results
    private ArrayList<PlayerInfo> resultArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_players);
        ButterKnife.bind(this);

        // define variables
        defineVariables();
        // set properties for grid view
        setGridView();
        // get teams -- page 1 default always
        new GetAllPlayers().execute(1);
        // swipe listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isSearchOn == false) {
                    playersArrayList.clear();
                    resultArrayList.clear();
                    isFinish = false ;
                    new GetAllPlayers().execute(1);
                    swipeRefreshLayout.setRefreshing(false);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        // edit text listener
        searchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    teamsGridView.setAdapter(gridViewAdapterResults);
                    isSearchOn = true ;
                    new GetSearchResults().execute(searchBar.getText().toString());
                    return true;
                }
                return false;
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
                        intent = new Intent(AllPlayersActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_teams:
                        intent = new Intent(AllPlayersActivity.this, AllTeamsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_players:
                        //intent = new Intent(AllPlayersActivity.this, MainActivity.class);
                        //startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_matches:
                        intent = new Intent(AllPlayersActivity.this, MatchesActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_news:
                        intent = new Intent(AllPlayersActivity.this, AllNewsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_scorers:
                        intent = new Intent(AllPlayersActivity.this, ScorerActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_champions:
                        intent = new Intent(AllPlayersActivity.this, ChampoinsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_groups:
                        intent = new Intent(AllPlayersActivity.this, GroupsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_about:
                        intent = new Intent(AllPlayersActivity.this, AboutClubActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_dev:
                        intent = new Intent(AllPlayersActivity.this, DevTeamActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;
                }

                return true;
            }
        });
    }

    private void defineVariables() {
        inflater = LayoutInflater.from(AllPlayersActivity.this);
        playersArrayList = new ArrayList<>();
        resultArrayList = new ArrayList<>();
        gridViewAdapter = new GridViewAdapter(playersArrayList);
        gridViewAdapterResults = new GridViewAdapter(resultArrayList);
    }

    // set properties
    private void setGridView() {
        teamsGridView.setNumColumns(Utility.calculateNoOfColumns(this,100));
        // default adapter
        teamsGridView.setAdapter(gridViewAdapter);
    }

    // grid view adapter
    private class GridViewAdapter extends BaseAdapter {

        TextView playerName;
        CircleImageView playerImage;
        ArrayList<PlayerInfo> arrayList ;

        public GridViewAdapter(ArrayList<PlayerInfo> arrayList){
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
            view = inflater.inflate(R.layout.layout_rv_players,null);
            // define views
            playerImage = (CircleImageView) view.findViewById(R.id.rv_player_image);
            playerName = (TextView) view.findViewById(R.id.rv_player_name);
            // bind
            playerName.setText(arrayList.get(i).getName());
            Picasso.with(AllPlayersActivity.this).load(arrayList.get(i).getImage()).noFade().into(playerImage);

            if (reachedEndOfList(i) && isFinish == false && isSearchOn == false){
                new GetAllPlayers().execute(nextPage);
            }
            // click listener
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AllPlayersActivity.this, DisplayPlayerActivity.class);
                    intent.putExtra("teamID",arrayList.get(i).getTeamID());
                    intent.putExtra("playerID",arrayList.get(i).getPlayerID());
                    startActivity(intent);
                }
            });

            return view;
        }

        private boolean reachedEndOfList(int i) {
            return i == getCount() - 1;
        }
    }

    // get all players async
    private class GetAllPlayers extends AsyncTask<Integer,Void,Void> {

        private AllPlayersApiCalls apiInterface;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // define interface
            apiInterface = ApiClient.getApiClient().create(AllPlayersApiCalls.class);
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            Call<Player> call = apiInterface.getPlayers(integers[0]);
            call.enqueue(new Callback<Player>() {
                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    // extract data from response and add them to the adapter array list
                    for (int index = 0 ; index<response.body().getData().size() ; index++){
                        playersArrayList.add(new PlayerInfo(response.body().getData().get(index).getProfilePic(),
                                response.body().getData().get(index).getName(),
                                response.body().getData().get(index).getTeamId(),
                                response.body().getData().get(index).getId()));
                    }
                    // refresh adapter and remove loading
                    gridViewAdapter.notifyDataSetChanged();
                    // refresh current value
                    nextPage = response.body().getNextPage();
                    // check if last page
                    if (response.body().getCurrentPage() == response.body().getLastPage()){
                        isFinish = true ;
                    }
                }

                @Override
                public void onFailure(Call<Player> call, Throwable t) {
                    Log.e("ErrorGetPlayers","error");
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

    // get search results
    private class GetSearchResults extends AsyncTask<String, Void, Void>{

        private AllPlayersApiCalls apiInterface;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // define interface
            apiInterface = ApiClient.getApiClient().create(AllPlayersApiCalls.class);
        }
        @Override
        protected Void doInBackground(String... strings) {
            Call<Player> call = apiInterface.searchPlayer(strings[0]);
            call.enqueue(new Callback<Player>() {
                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    resultArrayList.clear();
                    for (int index = 0 ; index<response.body().getData().size() ; index++){
                        resultArrayList.add(new PlayerInfo(response.body().getData().get(index).getProfilePic(),
                                response.body().getData().get(index).getName(),
                                response.body().getData().get(index).getTeamId(),
                                response.body().getData().get(index).getId()));
                    }
                    // update adapter
                    gridViewAdapterResults.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Player> call, Throwable t) {
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

    @Override
    public void onBackPressed() {
        if (isSearchOn){
            isSearchOn = false;
            resultArrayList.clear();
            teamsGridView.setAdapter(gridViewAdapter);
        } else {
            finish();
        }
    }
}
