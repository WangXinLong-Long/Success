package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.open;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.my.MyZijinActivity;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * A simple {@link Fragment} subclass.
 */
public class JiansheFragment extends Fragment implements View.OnClickListener {
    private ImageView more;
    private LinearLayout lyout_open_no;
    private LinearLayout layout_open;
    private TextView searchZhinan;
    private TextView tvSearchZhinan;
    private TextView tvAccount;
    private TextView tvName;
    private TextView tvBank;
    private TextView tvBank_Account;
    private TextView tvOpen;


    public JiansheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_jianshe, container, false);
        tvOpen= (TextView) view.findViewById(R.id.tvOpen);
        tvOpen.setOnClickListener(this);
        tvName= (TextView) view.findViewById(R.id.tvName);
        tvBank= (TextView) view.findViewById(R.id.tvBank);
        tvBank_Account= (TextView) view.findViewById(R.id.tvBank_Account);
        layout_open= (LinearLayout) view.findViewById(R.id.layout_open);
        lyout_open_no= (LinearLayout) view.findViewById(R.id.lyout_open_no);
        more= (ImageView) view.findViewById(R.id.more);
        more.setOnClickListener(this);
        searchZhinan= (TextView) view.findViewById(R.id.searchZhinan);
        searchZhinan.setOnClickListener(this);
        tvSearchZhinan= (TextView) view.findViewById(R.id.tvSearchZhinan);
        tvSearchZhinan.setOnClickListener(this);
        tvAccount= (TextView) view.findViewById(R.id.tvBank_Account);
        tvAccount.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getinfo();
    }

    public void getinfo(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/myaccountListInfo";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("bankId","3");
        Log.d("建设银行的rp",rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("建设银行的rp",result);
                //if ()
                try {
//                    JSONObject jsonobject=new JSONObject(result);
//                    String ccbAccount=jsonobject.getString("ccbAccount");
                  //  if (ccbAccount==null||ccbAccount.equals("")||ccbAccount.isEmpty()){
                      if (!result.contains("ccbAccount")){
                        lyout_open_no.setVisibility(View.VISIBLE);
                        layout_open.setVisibility(View.GONE);
                    }else{

                          JSONObject jsonObject=new JSONObject(result);
                          String ccbAccount=jsonObject.getString("ccbAccount");
                          if (!ccbAccount.isEmpty()&&!ccbAccount.equals("")&&ccbAccount!=null){
                              lyout_open_no.setVisibility(View.GONE);
                              layout_open.setVisibility(View.VISIBLE);
                              JSONObject object=new JSONObject(ccbAccount);
                              String name=object.getString("mbrName");
                              String cardNo=object.getString("cardNo");
                              //String acctName=object.getString("acctName");
                              tvName.setText(name);
                              tvBank_Account.setText(cardNo);
                              tvBank.setText("建设银行");

                          }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more:
            case R.id.tvOpen:
                Intent intent=new Intent(getActivity(),BankwebActivity.class);
                intent.putExtra("url","http://www.ccb.com");
                intent.putExtra("name","签约");
                startActivity(intent);
                break;
            case R.id.searchZhinan://建设银行的签约指南
                break;
            case R.id.tvSearchZhinan://其他银行的签约指南
                break;
            case R.id.tvBank_Account:
                Intent intent1=new Intent(getActivity(),MyZijinActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
