package agha.kfupmscapp.Activities.DevTeamActivity;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.AboutSportClubActivity.AboutClubActivity;
import agha.kfupmscapp.Activities.AllPlayersActivity.AllPlayersActivity;
import agha.kfupmscapp.Activities.AllTeamsActivity.AllTeamsActivity;
import agha.kfupmscapp.Activities.ChampionsActivity.ChampoinsActivity;
import agha.kfupmscapp.Activities.DevTeamActivity.API.DevTeamCall;
import agha.kfupmscapp.Activities.DevTeamActivity.API.Developer;
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

public class DevTeamActivity extends AppCompatActivity {

    @BindView(R.id.dev_lv)
    ListView listView;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_menu_all_players)
    NavigationView navigationView;

    @BindView(R.id.all_players_burger)
    ImageView toggle;

    ArrayList<Developer> developers ;
    Adapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_team);
        ButterKnife.bind(this);

        // init
        developers = new ArrayList<>();
        adapter = new Adapter();
        listView.setAdapter(adapter);

        // call
        new GetDevelopers().execute();

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
                        intent = new Intent(DevTeamActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_teams:
                        intent = new Intent(DevTeamActivity.this, AllTeamsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_players:
                        intent = new Intent(DevTeamActivity.this, AllPlayersActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_matches:
                        intent = new Intent(DevTeamActivity.this, MatchesActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_news:
                        intent = new Intent(DevTeamActivity.this, AllNewsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_scorers:
                        intent = new Intent(DevTeamActivity.this, ScorerActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_champions:
                        intent = new Intent(DevTeamActivity.this, ChampoinsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_groups:
                        intent = new Intent(DevTeamActivity.this, GroupsActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_menu_about:
                        intent = new Intent(DevTeamActivity.this, AboutClubActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;

                    case R.id.nav_menu_dev:
                        //intent = new Intent(DevTeamActivity.this, DevTeamActivity.class);
                        //startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                        break;
                }

                return true;
            }
        });
    }

    private class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return developers.size();
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

            view = getLayoutInflater().inflate(R.layout.layout_dev_row,null);
            // bind
            RelativeLayout email = (RelativeLayout)view.findViewById(R.id.email_view);
            CircleImageView devImage = (CircleImageView) view.findViewById(R.id.dev_image);
            TextView name = (TextView)view.findViewById(R.id.dev_name);
            TextView role = (TextView)view.findViewById(R.id.dev_role);
            TextView mail = (TextView)view.findViewById(R.id.dev_email);
            ImageView twitter = (ImageView) view.findViewById(R.id.twitter_icon);

            Picasso.with(DevTeamActivity.this).load(developers.get(i).getImage()).noFade().into(devImage);
            name.setText(developers.get(i).getFullName());
            role.setText(developers.get(i).getRole());
            mail.setText(developers.get(i).getEmailAccount());

            // handle twitter click
            twitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+developers.get(i).getTwitterAccount().substring(1))));
                }
            });

            // handle email click
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] { developers.get(i).getEmailAccount() });
                    startActivity(Intent.createChooser(intent, ""));
                }
            });

            return view;
        }
    }

    // get developers async inner class
    private class GetDevelopers extends AsyncTask<Void,Void,Void> {

        private DevTeamCall apiInterface ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // define apiInteface
            apiInterface = ApiClient.getApiClient().create(DevTeamCall.class);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<ArrayList<Developer>> call = apiInterface.getDevelopers();
            call.enqueue(new Callback<ArrayList<Developer>>() {
                @Override
                public void onResponse(Call<ArrayList<Developer>> call, Response<ArrayList<Developer>> response) {
                    // assign response
                    developers = response.body();
                    // refresh adapter
                    adapter.notifyDataSetChanged();

                }
                @Override
                public void onFailure(Call<ArrayList<Developer>> call, Throwable t) {
                    Log.e("ErrorDevelopers","Error");
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
