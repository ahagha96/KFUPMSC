package agha.kfupmscapp.Activities.DisplayNewsActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import agha.kfupmscapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayNews extends AppCompatActivity {

    @BindView(R.id.display_news_date)
    TextView date;

    @BindView(R.id.display_news_title)
    TextView title;

    @BindView(R.id.display_news_body)
    TextView body;

    @BindView(R.id.display_news_image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);
        ButterKnife.bind(this);

        // get intent
        Intent intent = getIntent();
        // extract extras
        date.setText(intent.getStringExtra("date"));
        title.setText(intent.getStringExtra("title"));
        body.setText(intent.getStringExtra("body"));
        // extract image
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        image.setImageBitmap(bmp);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
