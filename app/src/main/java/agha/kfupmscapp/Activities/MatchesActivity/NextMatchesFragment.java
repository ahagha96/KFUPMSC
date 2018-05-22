package agha.kfupmscapp.Activities.MatchesActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.security.interfaces.DSAKey;
import java.util.ArrayList;

import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.DisplayInfo;
import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.Matches;
import agha.kfupmscapp.Activities.MatchesActivity.API.MatchesActivityApiCalls;
import agha.kfupmscapp.R;
import agha.kfupmscapp.Utilities.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User-Sai on 1/16/2018.
 */

public class NextMatchesFragment extends Fragment {

    RelativeLayout relativeLayout;
    // lv view
    ListView nextMatches;
    // lv adapter
    MatchesAdapter adapter;
    // matches response object
    Matches matches ;
    // matches array list response
    ArrayList<DisplayInfo> displayMatches;
    // current page of calling
    private int nextPage;
    // boolean value to stop calling
    private boolean isFinish = false;
    // swipe
    SwipeRefreshLayout swipeRefreshLayout;

    // constructor
    public NextMatchesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_next_matches,container,false);
        // define lv and bind
        relativeLayout = (RelativeLayout) v.findViewById(R.id.rl);
        nextMatches = (ListView) v.findViewById(R.id.next_matches_lv);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.matches_swipe);
        // define variables
        displayMatches = new ArrayList<>();
        adapter = new MatchesAdapter(displayMatches);
        // set adapter
        nextMatches.setAdapter(adapter);

        // call -- default page 1
        new GetNextMatches().execute(1);
        // swipe listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // call -- default page 1
                displayMatches.clear();
                adapter.notifyDataSetChanged();
                isFinish = false ;
                new GetNextMatches().execute(1);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }

    private class MatchesAdapter extends BaseAdapter{

        // layout inflater
        private LayoutInflater inflater ;
        ArrayList<DisplayInfo> arrayList ;
        TextView title,firstTeamName,secondTeamName,date;
        ImageView firstTeamImage,secondTeamImage;

        public MatchesAdapter(ArrayList<DisplayInfo> arrayList){
            this.arrayList = arrayList;
            inflater = LayoutInflater.from(getActivity());
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
            v = inflater.inflate(R.layout.layout_match_card,null);
            // bind
            title = (TextView)v.findViewById(R.id.match_card_title);
            firstTeamName = (TextView)v.findViewById(R.id.match_card_team_one_name);
            secondTeamName = (TextView)v.findViewById(R.id.match_card_team_two_name);
            date = (TextView)v.findViewById(R.id.match_card_date);
            firstTeamImage = (ImageView)v.findViewById(R.id.match_card_team_one_image);
            secondTeamImage = (ImageView)v.findViewById(R.id.match_card_team_two_image);
            // set data
            title.setText(arrayList.get(position).getTitle());
            date.setText(arrayList.get(position).getDate());
            firstTeamName.setText(arrayList.get(position).getFirstTeamName());
            secondTeamName.setText(arrayList.get(position).getSecondTeamName());
            // set images
            Ion.with(getContext())
                    .load(arrayList.get(position).getFirstTeamLogo())
                    .withBitmap()
                    .intoImageView(firstTeamImage);
            Ion.with(getContext())
                    .load(arrayList.get(position).getSecondTeamLogo())
                    .withBitmap()
                    .intoImageView(secondTeamImage);

            if (reachedEndOfList(position) && isFinish == false){
                new GetNextMatches().execute(nextPage);
            }

            return v;
        }

        private boolean reachedEndOfList(int i) {
            return i == getCount() - 1;
        }
    }

    private class GetNextMatches extends AsyncTask<Integer,Void,Void>{

        private MatchesActivityApiCalls apiInterface;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // define interface
            apiInterface = ApiClient.getApiClient().create(MatchesActivityApiCalls.class);
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            Call<Matches> call = apiInterface.getNextMatches(integers[0]);
            call.enqueue(new Callback<Matches>() {
                @Override
                public void onResponse(Call<Matches> call, Response<Matches> response) {
                    matches = response.body();
                    // fill array
                    for (int i = 0 ; i < matches.getData().size() ; i++) {
                        displayMatches.add(new DisplayInfo
                                (matches.getData().get(i).getChampionshipId(),
                                        matches.getData().get(i).getRoundId(),
                                        matches.getData().get(i).getFirstTeam(),
                                        matches.getData().get(i).getSecondTeam(),
                                        matches.getData().get(i).getDate()));
                    }
                    // update adapter
                    adapter.notifyDataSetChanged();
                    // update next page
                    nextPage = matches.getNextPage();
                    // check if last page
                    if (response.body().getCurrentPage() == response.body().getLastPage()){
                        isFinish = true ;
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.e("error","error");
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
