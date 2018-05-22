package agha.kfupmscapp.Activities.MatchesActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
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

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import agha.kfupmscapp.Activities.DisplayMatchActivity.DisplayMatchActivity;
import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.DisplayInfo;
import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.Matches;
import agha.kfupmscapp.Activities.MatchesActivity.API.PreviousInfo;
import agha.kfupmscapp.Activities.MatchesActivity.API.MatchesActivityApiCalls;
import agha.kfupmscapp.R;
import agha.kfupmscapp.Utilities.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User-Sai on 1/16/2018.
 */

public class PreviousMatchesFragment extends Fragment {

    RelativeLayout relativeLayout;
    // lv view
    ListView nextMatches;
    // rv adapter
    MatchesAdapter adapter;
    // matches response object
    Matches matches ;
    // matches array list response
    ArrayList<PreviousInfo> displayMatches;
    // current page of calling
    private int nextPage;
    // boolean value to stop calling
    private boolean isFinish = false;
    // swipe
    SwipeRefreshLayout swipeRefreshLayout;

    // constructor
    public PreviousMatchesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_next_matches,container,false);
        // define lv and bind
        relativeLayout = (RelativeLayout)v.findViewById(R.id.rl);
        nextMatches = (ListView) v.findViewById(R.id.next_matches_lv);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.matches_swipe);
        // define variables
        displayMatches = new ArrayList<>();
        adapter = new MatchesAdapter(displayMatches);
        // set adapter
        nextMatches.setAdapter(adapter);

        // call -- default page 1
        new GetPreviousMatches().execute(1);
        // swipe listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // call -- default page 1
                displayMatches.clear();
                adapter.notifyDataSetChanged();
                isFinish = false ;
                new GetPreviousMatches().execute(1);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }

    private class MatchesAdapter extends BaseAdapter {

        // layout inflater
        private LayoutInflater inflater ;
        ArrayList<PreviousInfo> arrayList ;
        TextView title,firstTeamName,secondTeamName,date,firstTeamScore,secondTeamScore;
        ImageView firstTeamImage,secondTeamImage;

        public MatchesAdapter(ArrayList<PreviousInfo> arrayList){
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
            firstTeamScore = (TextView) v.findViewById(R.id.match_card_team_one_score);
            secondTeamScore = (TextView) v.findViewById(R.id.match_card_team_two_score);
            // set data
            title.setText(arrayList.get(position).getTitle());
            date.setText(arrayList.get(position).getDate());
            firstTeamName.setText(arrayList.get(position).getFirstTeamName());
            secondTeamName.setText(arrayList.get(position).getSecondTeamName());
            firstTeamScore.setText(arrayList.get(position).getFirstTeamScore()+"");
            secondTeamScore.setText(arrayList.get(position).getSecondTeamScore()+"");
            // set images
            // set images
            Ion.with(getContext())
                    .load(arrayList.get(position).getFirstTeamLogo())
                    .withBitmap()
                    .intoImageView(firstTeamImage);
            Ion.with(getContext())
                    .load(arrayList.get(position).getSecondTeamLogo())
                    .withBitmap()
                    .intoImageView(secondTeamImage);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), DisplayMatchActivity.class);
                    // put
                    intent.putExtra("title",arrayList.get(position).getTitle());
                    intent.putExtra("date",arrayList.get(position).getDate());
                    intent.putExtra("oneName",arrayList.get(position).getFirstTeamName());
                    intent.putExtra("twoName",arrayList.get(position).getSecondTeamName());
                    intent.putExtra("oneDes",String.valueOf(arrayList.get(position).getOneDes()));
                    intent.putExtra("twoDes",String.valueOf(arrayList.get(position).getTwoDes()));
                    intent.putExtra("oneScore",arrayList.get(position).getFirstTeamScore()+"");
                    intent.putExtra("twoScore",arrayList.get(position).getSecondTeamScore()+"");
                    intent.putExtra("oneImage",arrayList.get(position).getFirstTeamLogo());
                    intent.putExtra("twoImage",arrayList.get(position).getSecondTeamLogo());

                    // put bitmap
                    /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap b = convertImageViewToBitmap(firstTeamImage);
                    b.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra("oneImage",byteArray);

                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    Bitmap b2 = convertImageViewToBitmap(secondTeamImage);
                    b2.compress(Bitmap.CompressFormat.JPEG, 50, stream2);
                    byte [] byteArray2 = stream2.toByteArray();
                    intent.putExtra("twoImage",byteArray2);*/


                    startActivity(intent);
                }

                private Bitmap convertImageViewToBitmap(ImageView v){
                    Bitmap bm = Ion.with(v).getBitmap();
                    return bm;
                }
            });
            /*
            Picasso.with(getActivity()).load(arrayList.get(position).getFirstTeamLogo()).into(firstTeamImage, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    Picasso.with(getActivity()).load(arrayList.get(position).getSecondTeamLogo()).into(secondTeamImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {}
                        @Override
                        public void onError() {}});}
                @Override
                public void onError() {}});*/

            if (reachedEndOfList(position) && isFinish == false){
                new GetPreviousMatches().execute(nextPage);
            }

            return v;
        }

        private boolean reachedEndOfList(int i) {
            return i == getCount() - 1;
        }
    }

    private class GetPreviousMatches extends AsyncTask<Integer,Void,Void> {

        private MatchesActivityApiCalls apiInterface;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // define interface
            apiInterface = ApiClient.getApiClient().create(MatchesActivityApiCalls.class);
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            Call<Matches> call = apiInterface.getPreviousMatches(integers[0]);
            call.enqueue(new Callback<Matches>() {
                @Override
                public void onResponse(Call<Matches> call, Response<Matches> response) {
                    matches = response.body();
                    // fill array
                    for (int i = 0 ; i < matches.getData().size() ; i++) {
                        displayMatches.add(new PreviousInfo
                                (matches.getData().get(i).getChampionshipId(),
                                        matches.getData().get(i).getRoundId(),
                                        matches.getData().get(i).getFirstTeam(),
                                        matches.getData().get(i).getSecondTeam(),
                                        matches.getData().get(i).getDate(),
                                        matches.getData().get(i).getFirstTeamGoals(),
                                        matches.getData().get(i).getSecondTeamGoals(),
                                        (String)matches.getData().get(i).getFirstTeamDescription(),
                                        (String)matches.getData().get(i).getSecondTeamDescription()));
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
