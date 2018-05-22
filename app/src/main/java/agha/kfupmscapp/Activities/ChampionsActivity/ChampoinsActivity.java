package agha.kfupmscapp.Activities.ChampionsActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.AboutSportClubActivity.AboutClubActivity;
import agha.kfupmscapp.Activities.AllPlayersActivity.AllPlayersActivity;
import agha.kfupmscapp.Activities.AllTeamsActivity.AllTeamsActivity;
import agha.kfupmscapp.Activities.ChampionsActivity.API.ChampionActivityApiCalls;
import agha.kfupmscapp.Activities.ChampionsActivity.API.ChampionClasses.Champion;
import agha.kfupmscapp.Activities.ChampionsActivity.API.ChampionClasses.ChampionInfo;
import agha.kfupmscapp.Activities.DevTeamActivity.DevTeamActivity;
import agha.kfupmscapp.Activities.GroupsActivity.GroupsActivity;
import agha.kfupmscapp.Activities.MainActivity.MainActivity;
import agha.kfupmscapp.Activities.MatchesActivity.MatchesActivity;
import agha.kfupmscapp.Activities.NewsActivity.AllNewsActivity;
import agha.kfupmscapp.Activities.ScorerActivity.ScorerActivity;
import agha.kfupmscapp.R;
import agha.kfupmscapp.Utilities.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampoinsActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_menu_all_players)
    NavigationView navigationView;

    @BindView(R.id.all_players_burger)
    ImageView toggle;

    @BindView(R.id.champion_lv)
    ListView championListView;

    @BindView(R.id.champion_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    // repsonse object
    private Champion champion;

    // array list of displayed info
    private ArrayList<ChampionInfo> arrayList;

    // current page of calling
    private int nextPage;

    // boolean value to stop calling
    private boolean isFinish = false;

    // layout inflater
    private LayoutInflater inflater ;

    // lv adapter
    ChampionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champoins);
        ButterKnife.bind(this);

        // init variables
        defineVariables();

        // get champions -- default page = 1
        new GetChampions().execute(1);
        // swipe listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                new GetChampions().execute(1);
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
                        intent = new Intent(ChampoinsActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_teams:
                        intent = new Intent(ChampoinsActivity.this, AllTeamsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_players:
                        intent = new Intent(ChampoinsActivity.this, AllPlayersActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_matches:
                        intent = new Intent(ChampoinsActivity.this, MatchesActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_news:
                        intent = new Intent(ChampoinsActivity.this, AllNewsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_scorers:
                        intent = new Intent(ChampoinsActivity.this, ScorerActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_champions:
                        //intent = new Intent(ChampoinsActivity.this, ChampoinsActivity.class);
                        //startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_groups:
                        intent = new Intent(ChampoinsActivity.this, GroupsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_about:
                        intent = new Intent(ChampoinsActivity.this, AboutClubActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_dev:
                        intent = new Intent(ChampoinsActivity.this, DevTeamActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;
                }

                return true;
            }
        });
    }

    private void defineVariables() {
        arrayList = new ArrayList<>();
        inflater = LayoutInflater.from(getApplicationContext());
        adapter = new ChampionAdapter(arrayList);
        championListView.setAdapter(adapter);
    }

    private class ChampionAdapter extends BaseAdapter {

        ArrayList<ChampionInfo> arrayList ;
        AppCompatTextView title,champName,bestKeeperName,bestPlayerName,scorerName;;
        ImageView champImage;
        CircleImageView keeperImage,bestPlayerImage,scorerImage;

        public ChampionAdapter(ArrayList<ChampionInfo> arrayList){
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
        public View getView(final int position, View v, ViewGroup viewGroup) {
            // inflate
            v = inflater.inflate(R.layout.layout_champion_card,null);
            // bind
            title = (AppCompatTextView)v.findViewById(R.id.champion_card_title);
            champName = (AppCompatTextView)v.findViewById(R.id.champion_card_winner_name);
            bestKeeperName = (AppCompatTextView)v.findViewById(R.id.keeper_name);
            bestPlayerName = (AppCompatTextView)v.findViewById(R.id.player_name);
            scorerName = (AppCompatTextView)v.findViewById(R.id.scorer_name);
            champImage = (ImageView)v.findViewById(R.id.champion_card_winner_image);
            keeperImage = (CircleImageView)v.findViewById(R.id.keeper_image);
            scorerImage = (CircleImageView)v.findViewById(R.id.scorer_image);
            bestPlayerImage = (CircleImageView)v.findViewById(R.id.player_image);
            // set data
            title.setText(arrayList.get(position).getTitle());
            champName.setText(arrayList.get(position).getChampionName());
            bestKeeperName.setText(arrayList.get(position).getBestKeeperName());
            bestPlayerName.setText(arrayList.get(position).getBestPlayerName());
            scorerName.setText(arrayList.get(position).getScorerName());
            // set images
            Ion.with(ChampoinsActivity.this)
                    .load(arrayList.get(position).getChampionLogo())
                    .withBitmap()
                    .intoImageView(champImage);
            Picasso.with(getApplicationContext()).load(arrayList.get(position).getScorerLogo()).noFade().into(scorerImage);
            Picasso.with(getApplicationContext()).load(arrayList.get(position).getBestPlayerLogo()).noFade().into(bestPlayerImage);
            Picasso.with(getApplicationContext()).load(arrayList.get(position).getBestKeeperLogo()).noFade().into(keeperImage);

            if (reachedEndOfList(position) && isFinish == false){
                new GetChampions().execute(nextPage);
            }

            return v;
        }

        private boolean reachedEndOfList(int i) {
            return i == getCount() - 1;
        }
    }

    private class GetChampions extends AsyncTask<Integer,Void,Void> {

        private ChampionActivityApiCalls apiInterface;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // define interface
            apiInterface = ApiClient.getApiClient().create(ChampionActivityApiCalls.class);
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            Call<Champion> call = apiInterface.getNextMatches(integers[0]);
            call.enqueue(new Callback<Champion>() {
                @Override
                public void onResponse(Call<Champion> call, Response<Champion> response) {
                    champion = response.body();
                    // fill array
                    for (int i = 0 ; i < champion.getData().size() ; i++) {
                        arrayList.add(new ChampionInfo
                                (champion.getData().get(i).getChampTitle(),
                                        champion.getData().get(i).getChampionTeam().getName(),
                                        champion.getData().get(i).getChampionTeam().getLogo(),
                                        champion.getData().get(i).getBestPlayer().getName(),
                                        champion.getData().get(i).getBestPlayer().getProfilePic(),
                                        champion.getData().get(i).getBestKeeper().getName(),
                                        champion.getData().get(i).getBestKeeper().getProfilePic(),
                                        champion.getData().get(i).getScorerPlayer().getName(),
                                        champion.getData().get(i).getScorerPlayer().getProfilePic()));
                    }
                    // update adapter
                    adapter.notifyDataSetChanged();
                    // update next page
                    nextPage = champion.getNextPage();
                    // check if last page
                    if (response.body().getCurrentPage() == response.body().getLastPage()){
                        isFinish = true ;
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.e("error","error");
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
