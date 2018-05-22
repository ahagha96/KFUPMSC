package agha.kfupmscapp.Activities.MainActivity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
 * Created by Abdulrhman Hasan Agha on 1/13/2018.
 */

public class ViewPagerNewsAdapter extends PagerAdapter {

    private Context context ;
    private LayoutInflater layoutInflater ;
    private ArrayList<NewsPOJO> newsArrayList ;

    public ViewPagerNewsAdapter(Context c, ArrayList<NewsPOJO> array){
        context = c ;
        newsArrayList = array ;
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(RelativeLayout)object);
    }


    // we need to declare variables as Final in order to use them inside CallBack of Picasso
    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        // define layout inflater
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // create view to bind
        final View v = layoutInflater.inflate(R.layout.layout_viewpager_news,container,false);
        // set tag
        v.setTag(newsArrayList.get(position).getId());
        // bind
        final TextView header = (TextView) v.findViewById(R.id.viewpager_news_header);
        final TextView body = (TextView) v.findViewById(R.id.viewpager_news_body);
        final ImageView imageView = (ImageView) v.findViewById(R.id.viewpager_news_image_view);

        Ion.with(context)
                .load(newsArrayList.get(position).getImageURL())
                .withBitmap()
                .intoImageView(imageView);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            header.setText(Html.fromHtml(newsArrayList.get(position).getHeader(),Html.FROM_HTML_MODE_LEGACY));
            body.setText(Html.fromHtml(newsArrayList.get(position).getBody(),Html.FROM_HTML_MODE_LEGACY));

        }
        // if before Android N -- no need for the parameter
        else {
            header.setText(Html.fromHtml(newsArrayList.get(position).getHeader()));
            body.setText(Html.fromHtml(newsArrayList.get(position).getBody()));
        }
        // add to view pager
        container.addView(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayNews.class);
                // call put method
                put(newsArrayList.get(position).getCreatedAt(),
                        header.getText().toString(),
                        body.getText().toString(),
                        convertImageViewToBitmap(imageView),
                        intent);
                // start activity
                context.startActivity(intent);
            }
        });

        // return view
        return v ;
    }

    private void put(String createdAt, String title, String body, Bitmap b,Intent i) {
        i.putExtra("date",createdAt);
        i.putExtra("title",title);
        i.putExtra("body",body);
        // put bitmap
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        i.putExtra("image",byteArray);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

    private Bitmap convertImageViewToBitmap(ImageView v){
        //Bitmap bm=((BitmapDrawable)v.getDrawable()).getBitmap();
        Bitmap bm = Ion.with(v).getBitmap();
        return bm;
    }
}
