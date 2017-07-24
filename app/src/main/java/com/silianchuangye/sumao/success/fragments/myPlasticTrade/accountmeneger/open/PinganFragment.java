package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.open;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
public class PinganFragment extends Fragment implements View.OnClickListener {
    private LinearLayout layout_open_no;
    private LinearLayout layout_open;
    private TextView tvZijin;
    private TextView tvName;
    private TextView tvBank;
    private TextView tvBank_Account;
    private TextView tvOpen;
    private ImageView more;
    private TextView tvZhinan_pingan;
    private TextView tvzhinan_qita;


    public PinganFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view= inflater.inflate(R.layout.fragment_pingan, container, false);
        tvZijin= (TextView) view.findViewById(R.id.tvZijin);
        tvZijin.setOnClickListener(this);
        tvName= (TextView) view.findViewById(R.id.tvName);
        tvBank= (TextView) view.findViewById(R.id.tvBank);
        tvBank_Account= (TextView) view.findViewById(R.id.tvBank_Account);
        tvOpen= (TextView) view.findViewById(R.id.tvOpen);
        tvOpen.setOnClickListener(this);
        more= (ImageView) view.findViewById(R.id.more);
        more.setOnClickListener(this);
        tvzhinan_qita= (TextView) view.findViewById(R.id.tvzhinan_qita);
        tvzhinan_qita.setOnClickListener(this);
        tvZhinan_pingan= (TextView) view.findViewById(R.id.tvZhinan_pingan);
        tvZhinan_pingan.setOnClickListener(this);
        layout_open= (LinearLayout) view.findViewById(R.id.layout_open);
        layout_open_no= (LinearLayout) view.findViewById(R.id.layout_open_no);

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
        rp.addParameter("bankId","1");
        Log.d("平安银行的rp",rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("平安银行的rp",result);
                try {

                    if(!result.contains("pingAnAccount")){
                        layout_open_no.setVisibility(View.VISIBLE);
                        layout_open.setVisibility(View.GONE);
                    }else{

                        JSONObject jsonObject=new JSONObject(result);
                        String pingAnAccount=jsonObject.getString("pingAnAccount");
                        if (!pingAnAccount.isEmpty()&&!pingAnAccount.equals("")&&pingAnAccount!=null){
                               layout_open_no.setVisibility(View.GONE);
                               layout_open.setVisibility(View.VISIBLE);
                            JSONObject object=new JSONObject(pingAnAccount);
                            String name=object.getString("bankName");
                            String cardNo=object.getString("cardNo");
                            String acctName=object.getString("acctName");
//                            SharedPreferences sp=getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
//                            SharedPreferences.Editor editor=sp.edit();
//                            editor.putString("yinhangzhanghu",acctName);
//                            editor.putString("yinhang",name);
//                            editor.putString("yinhangcard",cardNo);
//                            editor.commit();
                            tvName.setText(acctName);
                            tvBank_Account.setText(cardNo);
                            tvBank.setText(name);
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
            case R.id.tvZijin:
                Intent intent=new Intent(getActivity(), MyZijinActivity.class);
                startActivity(intent);
                break;
            case R.id.more:
            case R.id.tvOpen:
                Intent intent1=new Intent(getActivity(),BankwebActivity.class);
                intent1.putExtra("url","https://ebank.sdb.com.cn/corporbank/perRegedit.do?MainAcctId=11017779122006");
                intent1.putExtra("name","签约");
                startActivity(intent1);
                break;
            case R.id.tvZhinan_pingan://指南平安
                break;
            case R.id.tvzhinan_qita://指南其他
                break;
        }
    }
}
