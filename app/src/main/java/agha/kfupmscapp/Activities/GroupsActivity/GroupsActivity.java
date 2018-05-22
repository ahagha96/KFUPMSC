package agha.kfupmscapp.Activities.GroupsActivity;

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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import agha.kfupmscapp.Activities.GroupsActivity.API.DisplayGroupInfo;
import agha.kfupmscapp.Activities.GroupsActivity.API.Group;
import agha.kfupmscapp.Activities.GroupsActivity.API.GroupApiCall;
import agha.kfupmscapp.Activities.MainActivity.MainActivity;
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

public class GroupsActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_menu_all_players)
    NavigationView navigationView;

    @BindView(R.id.all_players_burger)
    ImageView toggle;

    @BindView(R.id.groups_lv)
    ListView groupListView;

    @BindView(R.id.groups_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<DisplayGroupInfo> arrayList;
    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        ButterKnife.bind(this);

        // init variables
        init();
        // get groups
        new GetGroups().execute();
        // swipe listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // get groups
                arrayList.clear();
                new GetGroups().execute();
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
                        intent = new Intent(GroupsActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_teams:
                        intent = new Intent(GroupsActivity.this, AllTeamsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_players:
                        intent = new Intent(GroupsActivity.this, AllPlayersActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_matches:
                        intent = new Intent(GroupsActivity.this, MatchesActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_news:
                        intent = new Intent(GroupsActivity.this, AllNewsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_scorers:
                        intent = new Intent(GroupsActivity.this, ScorerActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_champions:
                        intent = new Intent(GroupsActivity.this, ChampoinsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_groups:
                        //intent = new Intent(GroupsActivity.this, GroupsActivity.class);
                        //startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_about:
                        intent = new Intent(GroupsActivity.this, AboutClubActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_dev:
                        intent = new Intent(GroupsActivity.this, DevTeamActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;
                }

                return true;
            }
        });

    }

    private void init() {
        arrayList = new ArrayList<>();
        adapter = new GroupAdapter(arrayList);
        groupListView.setAdapter(adapter);
    }

    private class GetGroups extends AsyncTask<Void,Void,Void>{

        private GroupApiCall apiInterface ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // init
            apiInterface = ApiClient.getApiClient().create(GroupApiCall.class);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<ArrayList<Group>> call = apiInterface.getGroups();
            call.enqueue(new Callback<ArrayList<Group>>() {
                @Override
                public void onResponse(Call<ArrayList<Group>> call, Response<ArrayList<Group>> response) {
                    // fill array
                    for (int i = 0 ; i<response.body().size() ; i++){
                        arrayList.add(new DisplayGroupInfo(response.body().get(i).getLetter(),
                                response.body().get(i).getTeams()));
                    }
                    // refresh adapter
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<ArrayList<Group>> call, Throwable t) {
                    Log.e("ErrorGetGroups","Error");
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

    private class GroupAdapter extends BaseAdapter{

        private ArrayList<DisplayGroupInfo> arrayList;
        private TextView groupName;

        public GroupAdapter(ArrayList<DisplayGroupInfo> arrayList){
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            // inflate
            view = getLayoutInflater().inflate(R.layout.layout_row_group,null);
            final LinearLayout linearLayout;
            linearLayout = (LinearLayout) view.findViewById(R.id.group_teams);
            // clean linear layout
            linearLayout.removeAllViews();
            groupName = (TextView)view.findViewById(R.id.group_name);
            groupName.setText(arrayList.get(i).getLetter());

            // add teams
            for (int index = 0 ; index<arrayList.get(i).getArrayList().size() ; index++) {
                // define child
                final View child = getLayoutInflater().inflate(R.layout.layout_linear_row_group,null);
                if (index % 2 == 0) {
                    if (index == arrayList.get(i).getArrayList().size()-1)
                        child.setBackground(getResources().getDrawable(R.drawable.group_last_body_light));
                    else
                        child.setBackgroundColor(getResources().getColor(R.color.colorPlaceHolderDarkGray));
                } else {
                    if (index == arrayList.get(i).getArrayList().size()-1)
                        child.setBackground(getResources().getDrawable(R.drawable.group_last_body_dark));
                }
                TextView teamName;
                ImageView teamImage;
                teamName = (TextView)child.findViewById(R.id.group_team_name);
                teamImage = (ImageView)child.findViewById(R.id.group_team_image);
                // bind
                teamName.setText(arrayList.get(i).getArrayList().get(index).getName());
                Ion.with(GroupsActivity.this)
                        .load(arrayList.get(i).getArrayList().get(index).getLogo())
                        .withBitmap()
                        .intoImageView(teamImage);
                linearLayout.addView(child);
            }
            // return listview row
            return view;
        }
    }
}
