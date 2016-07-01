package com.silianchuangye.sumao.success.fragments.homepage.theprice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MyApp;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ChinaNorthAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
//华北界面
public class ChinaNorthFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    private View chinanorth;
    private RelativeLayout relative_chinanorth;//点价规则，点价流程
    private TextView tv_chinanorth;
    private ImageView img_chiannorth;
    private ListView lv_chinanorth;
    private List<ChinaNorthInfo> list=new ArrayList<ChinaNorthInfo>();
    private List<String> allList=new ArrayList<String>();
    private ChinaNorthAdapter adapter;
    private boolean flag=true;
    private PopupWindow popupWindow;
    private View popView;
    private ImageView img_chinanorth_back;
    private Button btn_chinanorth_ok;
    private CheckBox checkBox1_chinanorth;
    private CheckBox checkBox2_chinanorth;
    private WebView web_chinanorth;
    private int i=1;
    private SharedPreferences share;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chinanorth =inflater.inflate(R.layout.fragment_china_north, container, false);
        initDate();
        initView();
        SharedPreferences share = getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);
        String str = share.getString("s", "");
        Log.e("TAG", "s" + str);
        if (!str.equals("1")) {
           showPopView();
        }
        return chinanorth;
    }

    private void initDate() {
        for(int i=0;i<3;i++){
            ChinaNorthInfo info=new ChinaNorthInfo();
            info.title="油化工线性聚乙烯LLDPE-"+i;
            info.name="张晓明-"+i;
            info.telnum="1111111111111-"+i;
            list.add(info);
        }
    }

    private void initView() {
        tv_chinanorth= (TextView) chinanorth.findViewById(R.id.tv_chinanorth);
        img_chiannorth= (ImageView) chinanorth.findViewById(R.id.img_chinanorth);
        lv_chinanorth= (ListView) chinanorth.findViewById(R.id.lv_chainanorth);
        relative_chinanorth= (RelativeLayout) chinanorth.findViewById(R.id.relative_chinanorth);
        relative_chinanorth.setOnClickListener(this);
        lv_chinanorth.setOnItemClickListener(this);
        adapter=new ChinaNorthAdapter(list,getActivity());
        lv_chinanorth.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relative_chinanorth:
                //跳转到点价界面
                Intent intent=new Intent(getActivity(),ChinaNorth_Price.class);
                startActivity(intent);
                break;
            case R.id.img_chinanorth_back:
                popupWindow.dismiss();
                break;
            case R.id.btn_chinanarth_ok:
                if(checkBox1_chinanorth.isChecked()){
                    Log.e("TAG","checkBox1_chinanorth.isChecked()==="+checkBox1_chinanorth.isChecked());
                    popupWindow.dismiss();
                }else{
                    Toast.makeText(getActivity(),"请选择是否已阅读",Toast.LENGTH_SHORT).show();
                }
                if(checkBox2_chinanorth.isChecked()){
                    SharedPreferences share=getActivity().getSharedPreferences("share",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt=share.edit();
                    edt.putString("s","1");
                    edt.commit();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(),ChinaNorth_MyPrice.class);
        intent.putExtra("name",list.get(position).title);
        startActivity(intent);
    }
    //弹出的popWindow
    private void showPopView(){
        popView=View.inflate(getActivity(),R.layout.chinanorth_popwindow,null);
        img_chinanorth_back= (ImageView) popView.findViewById(R.id.img_chinanorth_back);
        btn_chinanorth_ok= (Button) popView.findViewById(R.id.btn_chinanarth_ok);
        web_chinanorth= (WebView) popView.findViewById(R.id.web_chinanarth);
        web_chinanorth.getSettings().setJavaScriptEnabled(true);//支持js
        web_chinanorth.loadUrl("http://www.baidu.com/");//载入网页地址
        //监听webview打开地址
        web_chinanorth.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //url->webview要打开地址
                web_chinanorth.loadUrl(url);
                return true;
            }
        });
        checkBox1_chinanorth= (CheckBox) popView.findViewById(R.id.check1_chinanorth_pop);
        checkBox2_chinanorth= (CheckBox) popView.findViewById(R.id.check2_chinanorth_pop);
        img_chinanorth_back.setOnClickListener(this);
        btn_chinanorth_ok.setOnClickListener(this);

        popView.measure(0,0);
        int w=getActivity().getWindowManager().getDefaultDisplay().getWidth();
        popupWindow=new PopupWindow(popView,w,popView.getMeasuredHeight());
        popupWindow.setFocusable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(lv_chinanorth, Gravity.BOTTOM,0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                popupWindow.dismiss();
                Log.e("TAG","s;ldkff");
            }
        });
    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }
}
