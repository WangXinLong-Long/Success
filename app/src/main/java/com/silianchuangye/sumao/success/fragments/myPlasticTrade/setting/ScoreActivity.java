package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener{

    private RatingBar mRatingBar;
    private TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        mRatingBar = (RatingBar) findViewById(R.id.ratingbar);
        TextView title = (TextView) findViewById(R.id.tv_child_title_bar_title);
        title.setText("评分");
        ImageView back = (ImageView) findViewById(R.id.iv_child_title_bar_back);
        back.setOnClickListener(this);
        state = (TextView) findViewById(R.id.state);
        LayerDrawable ld_stars = (LayerDrawable) mRatingBar.getProgressDrawable();
        ld_stars.getDrawable(2).setColorFilter(Color.parseColor("#F7931E"), PorterDuff.Mode.SRC_ATOP);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int rat = ((int) rating);
                switch (rat)
                {
                    case 1:
                        state.setText("不满意");
                        break;
                    case 2:
                        state.setText("不满意");
                        break;
                    case 3:
                        state.setText("一般");
                        break;
                    case 4:
                        state.setText("满意");
                        break;
                    case 5:
                        state.setText("非常满意 ");
                        break;
                    default:
                        throw  new RuntimeException("出错了");

                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
        }
    }
}
