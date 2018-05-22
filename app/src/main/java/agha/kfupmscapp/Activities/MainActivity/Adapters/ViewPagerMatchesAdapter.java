package agha.kfupmscapp.Activities.MainActivity.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.MainActivity.API.MatchesClasses.DisplayInfo;
import agha.kfupmscapp.R;

/**
 * Created by User-Sai on 1/14/2018.
 */

public class ViewPagerMatchesAdapter extends PagerAdapter {

    private Context context ;
    private LayoutInflater layoutInflater ;
    private ArrayList<DisplayInfo> matches;

    public ViewPagerMatchesAdapter(Context c , ArrayList<DisplayInfo> arrayList){
        context = c ;
        matches = arrayList;
    }

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(RelativeLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        // define layout inflater
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // create view to bind
        final View v = layoutInflater.inflate(R.layout.layout_match_card,container,false);
        // bind
        final TextView title = (TextView)v.findViewById(R.id.match_card_title);
        final TextView firstTeamName = (TextView)v.findViewById(R.id.match_card_team_one_name);
        final TextView secondTeamName = (TextView)v.findViewById(R.id.match_card_team_two_name);
        TextView date = (TextView)v.findViewById(R.id.match_card_date);
        final ImageView firstTeamImage = (ImageView)v.findViewById(R.id.match_card_team_one_image);
        final ImageView secondTeamImage = (ImageView)v.findViewById(R.id.match_card_team_two_image);

        // set views
        date.setText(matches.get(position).getDate());
        title.setText(matches.get(position).getTitle());
        firstTeamName.setText(matches.get(position).getFirstTeamName());
        secondTeamName.setText(matches.get(position).getSecondTeamName());
        Ion.with(context)
                .load(matches.get(position).getFirstTeamLogo())
                .withBitmap()
                .intoImageView(firstTeamImage);
        Ion.with(context)
                .load(matches.get(position).getSecondTeamLogo())
                .withBitmap()
                .intoImageView(secondTeamImage);

        // add view
        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
