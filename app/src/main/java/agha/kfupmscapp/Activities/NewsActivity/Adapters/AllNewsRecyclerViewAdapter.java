package agha.kfupmscapp.Activities.NewsActivity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import agha.kfupmscapp.Activities.DisplayNewsActivity.DisplayNews;
import agha.kfupmscapp.Activities.MainActivity.API.NewsPOJO;
import agha.kfupmscapp.R;

/**
 * Created by User-Sai on 1/13/2018.
 */

public class AllNewsRecyclerViewAdapter extends RecyclerView.Adapter<AllNewsRecyclerViewAdapter.ViewHolder>  {

    ArrayList<NewsPOJO> newsArrayList;
    Context context ;

    public AllNewsRecyclerViewAdapter(ArrayList<NewsPOJO> arrayList,Context c){
        newsArrayList = arrayList;
        context = c ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_all_news,parent,false);
        return new ViewHolder(view,context,newsArrayList);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /*Picasso.with(context).load(newsArrayList.get(position).getImageURL()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                // check SDK because we are using HTML library which has been changed after Android N
                // we need to add another parameter
                // if Android N or later
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    holder.date.setText(Html.fromHtml(newsArrayList.get(position).getCreatedAt(),Html.FROM_HTML_MODE_LEGACY));
                    holder.header.setText(Html.fromHtml(newsArrayList.get(position).getHeader(),Html.FROM_HTML_MODE_LEGACY));
                    holder.body.setText(Html.fromHtml(newsArrayList.get(position).getBody(),Html.FROM_HTML_MODE_LEGACY));
                }
                // if before Android N -- no need for the parameter
                else {
                    holder.date.setText(Html.fromHtml(newsArrayList.get(position).getCreatedAt()));
                    holder.header.setText(Html.fromHtml(newsArrayList.get(position).getHeader()));
                    holder.body.setText(Html.fromHtml(newsArrayList.get(position).getBody()));
                }
            }

            @Override
            public void onError() {
                // TODO appropriate error msg
            }
        });*/
        // set image
        Ion.with(context)
                .load(newsArrayList.get(position).getImageURL())
                .withBitmap()
                .intoImageView(holder.image);
        // set text
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.date.setText(Html.fromHtml(newsArrayList.get(position).getCreatedAt(),Html.FROM_HTML_MODE_LEGACY));
            holder.header.setText(Html.fromHtml(newsArrayList.get(position).getHeader(),Html.FROM_HTML_MODE_LEGACY));
            holder.body.setText(Html.fromHtml(newsArrayList.get(position).getBody(),Html.FROM_HTML_MODE_LEGACY));
        }
        // if before Android N -- no need for the parameter
        else {
            holder.date.setText(Html.fromHtml(newsArrayList.get(position).getCreatedAt()));
            holder.header.setText(Html.fromHtml(newsArrayList.get(position).getHeader()));
            holder.body.setText(Html.fromHtml(newsArrayList.get(position).getBody()));
        }
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        final ImageView image ;
        final TextView date, header, body;
        ArrayList<NewsPOJO> arrayList = new ArrayList<>();
        Context context ;

        public ViewHolder(View itemView, final Context context, ArrayList<NewsPOJO> arrayList) {
            super(itemView);
            // init
            this.arrayList = arrayList;
            this.context = context;
            // bind
            image = (ImageView) itemView.findViewById(R.id.all_news_image_view);
            date = (TextView) itemView.findViewById(R.id.all_news_date);
            header = (TextView) itemView.findViewById(R.id.all_news_header);
            body = (TextView) itemView.findViewById(R.id.all_news_body);
            // set on click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DisplayNews.class);
                    // call put method
                    put(    date.getText().toString(),
                            header.getText().toString(),
                            body.getText().toString(),
                            convertImageViewToBitmap(image),
                            intent);
                    // start activity
                    context.startActivity(intent);
                }

                private Bitmap convertImageViewToBitmap(ImageView v){
                    Bitmap bm = Ion.with(v).getBitmap();
                    return bm;
                }

                private void put(String date, String header, String body, Bitmap b, Intent i){
                    i.putExtra("date",date);
                    i.putExtra("title",header);
                    i.putExtra("body",body);
                    // put bitmap
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    b.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                    byte[] byteArray = stream.toByteArray();
                    i.putExtra("image",byteArray);
                }
            });
        }

    }

}
