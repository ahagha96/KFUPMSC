package agha.kfupmscapp.Activities.DisplayTeamActivity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import agha.kfupmscapp.Activities.DisplayMatchActivity.DisplayMatchActivity;
import agha.kfupmscapp.Activities.DisplayTeamActivity.API.DisplayMatchInfo;
import agha.kfupmscapp.R;

/**
 * Created by User-Sai on 1/21/2018.
 */

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private ArrayList<DisplayMatchInfo> matchesArrayList;
    private Context context;

    public MatchesAdapter(ArrayList<DisplayMatchInfo> array, Context c){
        matchesArrayList = array ;
        context = c ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_match_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.firstTeamName.setText(matchesArrayList.get(position).getFirstTeamName());
        holder.secondTeamName.setText(matchesArrayList.get(position).getSecondTeamName());
        holder.date.setText(matchesArrayList.get(position).getDate());
        holder.title.setText(matchesArrayList.get(position).getTitle());
        holder.firstTeamScore.setText(matchesArrayList.get(position).getFirstTeamScore()+"");
        holder.secondTeamScore.setText(matchesArrayList.get(position).getSecondTeamScore()+"");
        Ion.with(context)
                .load(matchesArrayList.get(position).getFirstTeamLogo())
                .withBitmap()
                .intoImageView(holder.firstTeamLogo);
        Ion.with(context)
                .load(matchesArrayList.get(position).getSecondTeamLogo())
                .withBitmap()
                .intoImageView(holder.secondTeamLogo);

        holder.firstTeamName.setTag(R.id.title1,matchesArrayList.get(position).getTitle()+"");
        holder.firstTeamName.setTag(R.id.date1,matchesArrayList.get(position).getDate()+"");
        holder.firstTeamName.setTag(R.id.firstTeamName1,matchesArrayList.get(position).getFirstTeamName()+"");
        holder.firstTeamName.setTag(R.id.secondTeamName1,matchesArrayList.get(position).getSecondTeamName()+"");
        holder.firstTeamName.setTag(R.id.firstTeamScore1,matchesArrayList.get(position).getFirstTeamScore()+"");
        holder.firstTeamName.setTag(R.id.secondTeamScore1,matchesArrayList.get(position).getSecondTeamScore()+"");
        holder.firstTeamName.setTag(R.id.firstTeamLogo1,matchesArrayList.get(position).getFirstTeamLogo());
        holder.firstTeamName.setTag(R.id.secondTeamLogo1,matchesArrayList.get(position).getSecondTeamLogo());
        holder.firstTeamName.setTag(R.id.firstTeamDes1,matchesArrayList.get(position).getFirstDes()+"");
        holder.firstTeamName.setTag(R.id.secondTeamDes1,matchesArrayList.get(position).getSecondDes()+"");

        /*
        Picasso.with(context).load(matchesArrayList.get(position).getFirstTeamLogo()).into(holder.firstTeamLogo);
        Picasso.with(context).load(matchesArrayList.get(position).getSecondTeamLogo()).into(holder.secondTeamLogo);*/
    }

    @Override
    public int getItemCount() {
        return matchesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView firstTeamLogo,secondTeamLogo;
        TextView firstTeamName,secondTeamName,firstTeamScore,secondTeamScore,date,title;

        public ViewHolder(View itemView) {
            super(itemView);
            firstTeamLogo = (ImageView)itemView.findViewById(R.id.match_card_team_one_image);
            secondTeamLogo = (ImageView)itemView.findViewById(R.id.match_card_team_two_image);
            firstTeamName = (TextView)itemView.findViewById(R.id.match_card_team_one_name);
            secondTeamName = (TextView)itemView.findViewById(R.id.match_card_team_two_name);
            date = (TextView)itemView.findViewById(R.id.match_card_date);
            title = (TextView)itemView.findViewById(R.id.match_card_title);
            firstTeamScore = (TextView)itemView.findViewById(R.id.match_card_team_one_score);
            secondTeamScore = (TextView)itemView.findViewById(R.id.match_card_team_two_score);

            // set on click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DisplayMatchActivity.class);
                    intent.putExtra("title",(String)firstTeamName.getTag(R.id.title1));
                    intent.putExtra("date",(String)firstTeamName.getTag(R.id.date1));
                    intent.putExtra("oneName",(String)firstTeamName.getTag(R.id.firstTeamName1));
                    intent.putExtra("twoName",(String)firstTeamName.getTag(R.id.secondTeamName1));
                    intent.putExtra("oneScore",(String)firstTeamName.getTag(R.id.firstTeamScore1));
                    intent.putExtra("twoScore",(String)firstTeamName.getTag(R.id.secondTeamScore1));
                    intent.putExtra("oneImage",(String)firstTeamName.getTag(R.id.firstTeamLogo1));
                    intent.putExtra("twoImage",(String)firstTeamName.getTag(R.id.secondTeamLogo1));
                    intent.putExtra("oneDes",(String)firstTeamName.getTag(R.id.firstTeamDes1));
                    intent.putExtra("twoDes",(String)firstTeamName.getTag(R.id.secondTeamDes1));

                    context.startActivity(intent);
                }
            });
        }
    }
}
