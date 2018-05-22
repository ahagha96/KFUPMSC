package agha.kfupmscapp.Activities.MainActivity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.DisplayTeamActivity.DisplayTeam;
import agha.kfupmscapp.Activities.MainActivity.API.TeamsPOJO;
import agha.kfupmscapp.R;

/**
 * Created by User-Sai on 1/13/2018.
 */

public class TeamsRecyclerViewAdapter extends RecyclerView.Adapter<TeamsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<TeamsPOJO> teamsArrayList ;
    private Context context;
    private int teamID;

    public TeamsRecyclerViewAdapter(ArrayList<TeamsPOJO> array, Context c){
        teamsArrayList = array ;
        context = c ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_teams,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /*Picasso.with(context).load(teamsArrayList.get(position).getLogo()).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.teamName.setText(teamsArrayList.get(position).getName());
            }

            @Override
            public void onError() {
                // TODO appropriate error msg
            }
        });*/
        // set image
        Ion.with(context)
                .load(teamsArrayList.get(position).getLogo())
                .withBitmap()
                .intoImageView(holder.imageView);
        // set name
        holder.teamName.setText(teamsArrayList.get(position).getName());
        holder.teamName.setTag(teamsArrayList.get(position).getId());
        holder.imageView.setTag(teamsArrayList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return teamsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView ;
        TextView teamName ;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.main_layout_rv_team_image);
            teamName = (TextView) itemView.findViewById(R.id.main_layout_rv_team_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DisplayTeam.class);
                    intent.putExtra("teamID",(int)imageView.getTag());
                    context.startActivity(intent);
                }
            });
        }
    }
}
