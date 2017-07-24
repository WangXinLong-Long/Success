package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.open;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class KunlunFragment extends Fragment implements View.OnClickListener{
    private LinearLayout layout_open_no;
    private LinearLayout layout_open;
    private TextView tvOpen;
    private ImageView more;
    private TextView tvzhinan;
    private TextView tvzijin;
    private TextView tvName;
    private TextView tvBank;
    private TextView tvBank_Account;
    private Button bt_jieyue;
    private Button  bt_update;
    private String name="";
    private String cardNo="";
    private String mobilePhone="";


    public KunlunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view= inflater.inflate(R.layout.fragment_kunlun, container, false);
        tvOpen= (TextView) view.findViewById(R.id.tvOpen);
        tvOpen.setOnClickListener(this);
        more= (ImageView) view.findViewById(R.id.more);
        more.setOnClickListener(this);
        tvzhinan= (TextView) view.findViewById(R.id.tvzhinan);
        tvzhinan.setOnClickListener(this);
        tvzijin= (TextView) view.findViewById(R.id.tvzijin);
        tvzijin.setOnClickListener(this);
        tvName= (TextView) view.findViewById(R.id.tvName);
        tvBank= (TextView) view.findViewById(R.id.tvBank);
        tvBank_Account= (TextView) view.findViewById(R.id.tvBank_Account);
        bt_jieyue= (Button) view.findViewById(R.id.bt_jieyue);
        bt_jieyue.setOnClickListener(this);
        bt_update= (Button) view.findViewById(R.id.bt_update);
        bt_update.setOnClickListener(this);
        layout_open= (LinearLayout) view.findViewById(R.id.layout_open);
        layout_open_no= (LinearLayout) view.findViewById(R.id.layout_open);

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
        rp.addParameter("bankId","2");
        Log.d("昆仑银行的rp",rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("昆仑银行的rp",result);
                try {
//                    JSONObject jsonObject=new JSONObject(result);
//                    String klbAccount=jsonObject.getString("klbAccount");
//                    if (klbAccount.isEmpty()||klbAccount.equals("")||klbAccount==null){

                    if (!result.contains("klbAccount")){
                        layout_open_no.setVisibility(View.VISIBLE);
                        layout_open.setVisibility(View.GONE);
                    }else{

                        //                    JSONObject jsonObject=new JSONObject(result);
//                    String klbAccount=jsonObject.getString("klbAccount");
//                    if (klbAccount.isEmpty()||klbAccount.equals("")||klbAccount==null){
                        JSONObject jsonObject=new JSONObject(result);
                        String klbAccount=jsonObject.getString("klbAccount");
                        if (klbAccount.length()>1){
                            layout_open_no.setVisibility(View.GONE);
                            layout_open.setVisibility(View.VISIBLE);
                            JSONObject object=new JSONObject(klbAccount);
                             name=object.getString("innerAccName");
                             cardNo=object.getString("innerAccNo");
                             mobilePhone=object.getString("mobilePhone");
                           // String acctName=object.getString("acctName");
                            tvName.setText(name);
                            tvBank_Account.setText(cardNo);
                            tvBank.setText("昆仑银行");
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
            case R.id.tvOpen:
            case R.id.more:
                Intent intent=new Intent(getActivity(),KunlunOpenActivity.class);
                startActivity(intent);
                break;
            case R.id.tvzhinan:
                //指南
                break;
            case R.id.tvzijin:
                Intent intent1=new Intent(getActivity(), MyZijinActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_jieyue://解约
                final AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                alert.setMessage("确定要解约吗？");
                alert.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jieyue();
                    }
                });
                alert.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.create().dismiss();
                    }
                });
                alert.create().show();
                break;
            case R.id.bt_update://变更帐号
                Intent intent2=new Intent(getActivity(),KunlunUpdateinfoActivity.class);
//                name=object.getString("innerAccName");
//                cardNo=object.getString("innerAccNo");
//                mobilePhone=object.getString("mobilePhone");
                intent2.putExtra("name",name);
                intent2.putExtra("cardNo",cardNo);
                intent2.putExtra("mobilePhone",mobilePhone);
                startActivity(intent2);
                break;
        }
    }
    public void jieyue(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/kunLunoutSign";
        RequestParams rp=new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("解约的result",result);
                if (result.contains("success")){
                    Toast.makeText(getActivity(), "解约成功", Toast.LENGTH_SHORT).show();
                    layout_open_no.setVisibility(View.VISIBLE);
                    layout_open.setVisibility(View.GONE);
                }else{
                    Toast.makeText(getActivity(), "解约失败", Toast.LENGTH_SHORT).show();
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
}
