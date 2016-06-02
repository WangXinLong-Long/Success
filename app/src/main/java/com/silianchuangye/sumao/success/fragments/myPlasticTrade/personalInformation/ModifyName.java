package com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;


/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class ModifyName extends Activity implements View.OnClickListener{
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView,modify_name_save;
    TextView tv_title_bar_title;
    RelativeLayout modify_title;
    String classname;
    String receivingInformation;
    Boolean canBeAmpty;
    EditText modify_information;
    TextView prompt_information;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_name);

        modify_title = ((RelativeLayout) findViewById(R.id.modify_title));
        modify_title.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);
        iv_title_bar_search.setVisibility(View.INVISIBLE);
        iv_title_bar_back.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        receivingInformation = bundle.getString("receivingInformation");
        canBeAmpty  = bundle.getBoolean("canBeAmpty");
        message = bundle.getString("message");
        tv_title_bar_title.setText(receivingInformation);
        tv_title_bar_title.setTextColor(Color.WHITE);

        modify_name_save = ((Button) findViewById(R.id.modify_name_save));
        modify_information = ((EditText) findViewById(R.id.modify_information));
        prompt_information  = ((TextView) findViewById(R.id.prompt_information));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.modify_name_save:
                /**
                 * 接口，修改信息
                 */
                canCloseThisActivity();


                break;

        }
    }
    public void canCloseThisActivity()
    {
        if (modify_information.getText().toString().equals(""))
        {
            if (canBeAmpty) {finish();}
            else
            {
                prompt_information.setText("*请输入"+message);
                prompt_information.setVisibility(View.VISIBLE);
            }

        }else {
            prompt_information.setVisibility(View.INVISIBLE);
            /**
             * 在这里吧EditText的文本信息获取到，调用接口传到服务器上
             */
        Toast.makeText(ModifyName.this,"调用接口传到服务器上",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN)
        {
            finish();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
