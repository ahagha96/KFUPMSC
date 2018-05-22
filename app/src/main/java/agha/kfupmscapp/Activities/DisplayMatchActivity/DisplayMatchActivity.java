package agha.kfupmscapp.Activities.DisplayMatchActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import agha.kfupmscapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayMatchActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.match_card_team_one_image)
    ImageView teamOneImage;

    @BindView(R.id.match_card_team_two_image)
    ImageView teamTwoImage;

    @BindView(R.id.match_card_team_one_score)
    TextView teamOneScore;

    @BindView(R.id.match_card_team_two_score)
    TextView teamTwoScore;

    @BindView(R.id.team_one_description)
    TextView teamOneDes;

    @BindView(R.id.team_two_description)
    TextView teamTwoDes;

    @BindView(R.id.match_card_team_one_name)
    TextView teamOneName;

    @BindView(R.id.match_card_team_two_name)
    TextView teamTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_match);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        // extract
        title.setText(intent.getStringExtra("title"));
        date.setText(intent.getStringExtra("date"));
        teamOneName.setText(intent.getStringExtra("oneName"));
        teamTwoName.setText(intent.getStringExtra("twoName"));
        teamOneScore.setText(intent.getStringExtra("oneScore"));
        teamTwoScore.setText(intent.getStringExtra("twoScore"));

        Ion.with(DisplayMatchActivity.this).load(intent.getStringExtra("oneImage")).intoImageView(teamOneImage);
        Ion.with(DisplayMatchActivity.this).load(intent.getStringExtra("twoImage")).intoImageView(teamTwoImage);

        // set text
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if (intent.getStringExtra("oneDes").equals("null"))
                teamOneDes.setVisibility(View.INVISIBLE);
            else
                teamOneDes.setText(Html.fromHtml(intent.getStringExtra("oneDes"),Html.FROM_HTML_MODE_LEGACY));

            if (intent.getStringExtra("twoDes").equals("null"))
                teamTwoDes.setVisibility(View.INVISIBLE);
            else
                teamTwoDes.setText(Html.fromHtml(intent.getStringExtra("twoDes"),Html.FROM_HTML_MODE_LEGACY));

        }
        // if before Android N -- no need for the parameter
        else {
            if (intent.getStringExtra("oneDes").equals("null"))
                teamOneDes.setVisibility(View.INVISIBLE);
            else
                teamOneDes.setText(Html.fromHtml(intent.getStringExtra("oneDes")));

            if (intent.getStringExtra("twoDes").equals("null"))
                teamTwoDes.setVisibility(View.INVISIBLE);
            else
                teamTwoDes.setText(Html.fromHtml(intent.getStringExtra("twoDes")));
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
