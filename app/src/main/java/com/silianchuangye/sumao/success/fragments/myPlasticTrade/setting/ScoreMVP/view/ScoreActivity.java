package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.bean.HelpAndFeedbackBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.bean.ScoreInfoBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.presenter.ScorePresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener,IScoreView{

    private RatingBar mRatingBar;
    private TextView state;
    private ScorePresenter scorePresenter;
    private int rat;
    private Button btSave_password_update;
    private String userName;
    private ScoreInfoBean scoreInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        userName = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("zhanghao","");
        LogUtils.log("userName"+ userName);
        scorePresenter = new ScorePresenter(this);
        mRatingBar = (RatingBar) findViewById(R.id.ratingbar);
        TextView title = (TextView) findViewById(R.id.tv_child_title_bar_title);
        title.setText("评分");
        //返回
        ImageView back = (ImageView) findViewById(R.id.iv_child_title_bar_back);
        back.setOnClickListener(this);
        state = (TextView) findViewById(R.id.state);
        LayerDrawable ld_stars = (LayerDrawable) mRatingBar.getProgressDrawable();
        ld_stars.getDrawable(2).setColorFilter(Color.parseColor("#F7931E"), PorterDuff.Mode.SRC_ATOP);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rat = ((int) rating);
                setScoreText( rat);

            }
        });
        btSave_password_update = ((Button) findViewById(R.id.btSave_password_update));
        btSave_password_update.setOnClickListener(this);
        btSave_password_update.setVisibility(View.GONE);
        scorePresenter.scoreInformation(userName);

    }
public void setScoreText(int rat){
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
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            case R.id.btSave_password_update:
//                TODO 账号
                if (rat == 0){
                    Toast.makeText(this,"请选择满意程度",Toast.LENGTH_SHORT).show();
                }else {


                    scorePresenter.sendScoreToServer(rat+"",userName);
                }
                break;
        }
    }

    @Override
    public void getScoreState(HelpAndFeedbackBean helpAndFeedbackBean) {
        String result = helpAndFeedbackBean.getResult().get(0).getInformation();
        if(result.contains("成功")){
            Toast.makeText(this,"上传成功",Toast.LENGTH_SHORT).show();
            finish();
        }else if (result.contains("失败")){
            Toast.makeText(this,"上传失败，请再试一次",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"请选择满意程度",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getScoreInfo(String info) {
//        {"username":"wangwei","userscore":"4.5"}
        if(info.contains("userscore")){
            Gson gson = new Gson();
            scoreInfoBean = gson.fromJson(info, ScoreInfoBean.class);
            mRatingBar.setRating(Integer.parseInt(scoreInfoBean.getUserscore()));
            setScoreText(Integer.parseInt(scoreInfoBean.getUserscore()));
            mRatingBar.isIndicator();
        }else {
            btSave_password_update.setVisibility(View.VISIBLE);
        }
    }
}
