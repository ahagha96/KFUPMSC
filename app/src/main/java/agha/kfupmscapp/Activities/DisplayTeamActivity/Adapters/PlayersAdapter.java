package agha.kfupmscapp.Activities.DisplayTeamActivity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import agha.kfupmscapp.Activities.DisplayPlayerActivity.DisplayPlayerActivity;
import agha.kfupmscapp.Activities.DisplayTeamActivity.API.DisplayPlayerInfo;
import agha.kfupmscapp.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User-Sai on 1/21/2018.
 */

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {

    private ArrayList<DisplayPlayerInfo> playersArrayList;
    private Context context;

    public PlayersAdapter(ArrayList<DisplayPlayerInfo> array, Context c){
        playersArrayList = array ;
        context = c ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_players,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(context).load(playersArrayList.get(position).getLogo()).noFade().into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.playerName.setText(playersArrayList.get(position).getName());
                if (playersArrayList.get(position).getPosition() != null){
                    switch (playersArrayList.get(position).getPosition()){
                        case 1:
                            holder.playerName.append(" (مهاجم)");
                            break;
                        case 2:
                            holder.playerName.append(" (وسط)");
                            break;
                        case 3:
                            holder.playerName.append(" (مدافع)");
                            break;
                        case 4:
                            holder.playerName.append(" (حارس)");
                            break;
                    }
                }
            }

            @Override
            public void onError() {
                // TODO appropriate error msg
            }
        });
        holder.playerName.setTag(playersArrayList.get(position).getTeamID());
        holder.imageView.setTag(playersArrayList.get(position).getPlayerID());
    }

    @Override
    public int getItemCount() {
        return playersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageView ;
        TextView playerName;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.rv_player_image);
            playerName = (TextView) itemView.findViewById(R.id.rv_player_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DisplayPlayerActivity.class);
                    intent.putExtra("teamID",(int)playerName.getTag());
                    intent.putExtra("playerID",(int)imageView.getTag());
                    context.startActivity(intent);
                }
            });
        }
    }
}
