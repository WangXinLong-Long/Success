package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectDetailAreaMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.bean.InvoiceInformationBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.view.IInvoiceInformationView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.view.InvoiceInformation;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.view.FirmUpdateActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.presenter.AddAddressPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view.AddAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.view.ReceiptAddressDetail;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.view.RegisterFirmActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SelectDetailArea extends Activity implements View.OnClickListener,ISelectDetailAreaView, IInvoiceInformationView {
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
   // Intent intent;
    String county;
    EditText modify_information;
    TextView prompt_information;
    Button modify_name_save;
    String className;
    AddAddressPresenter addAddressPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_address_name);
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        modify_information = ((EditText) findViewById(R.id.modify_information));
        prompt_information = ((TextView) findViewById(R.id.prompt_information));
        Intent intent = getIntent();
       // addAddressPresenter = new AddAddressPresenter(this);
        county = intent.getStringExtra("county");
        Log.d("穿过来的county",county);
        className = intent.getStringExtra("className");
        tv_child_title_bar_title.setText("填写详细地址");
        modify_name_save = ((Button) findViewById(R.id.modify_name_save));
        modify_name_save.setOnClickListener(this);
        iv_child_title_bar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
//            保存按钮
            case R.id.modify_name_save:
                canCloseThisActivity();
                break;
//            返回按钮
            case R.id.iv_child_title_bar_back:
                finish();
                break;
        }
    }
    public void canCloseThisActivity() {
        if (modify_information.getText().toString().equals("")) {

            prompt_information.setText("*请输入" + "详细地址");
            prompt_information.setVisibility(View.VISIBLE);


        } else {
            prompt_information.setVisibility(View.INVISIBLE);
            /**
             * 在这里吧EditText的文本信息获取到，调用接口传到服务器上
             */


            if (className.equals("AddAddress")){
                Intent intent = new Intent();
                intent.putExtra("address",county);
                LogUtils.log("county----------->"+county);
                intent.putExtra("detailAddress",modify_information.getText().toString().trim());
                intent.setClass(SelectDetailArea.this,AddAddress.class);
                startActivity(intent);
            }else if (className.equals("InvoiceInformation"))
            {
            //    addAddressPresenter.setDisplaylAddress(county.substring(0, 4), county.substring(0, 6), county.toString());
//                intent.setClass(SelectDetailArea.this,InvoiceInformation.class);
//                startActivity(intent);
               // addAddressPresenter.setDisplaylAddress(sb.substring(0, 4), sb.substring(0, 6), sb.toString());
                getAddress_info(county.substring(0, 4), county.substring(0, 6), county.toString());

            }else if (className.equals("RegisterFirmActivity")){
                Intent intent = new Intent();
                intent.putExtra("address",county);
                LogUtils.log("county----------->"+county);
                intent.putExtra("detailAddress",modify_information.getText().toString().trim());
                intent.setClass(SelectDetailArea.this,RegisterFirmActivity.class);
                startActivity(intent);
            }else if (className.equals("ReceiptAddressDetail")){
                Intent intent = new Intent();
                intent.putExtra("address",county);
                LogUtils.log("county----------->"+county);
                intent.putExtra("detailAddress",modify_information.getText().toString().trim());
                intent.setClass(SelectDetailArea.this,ReceiptAddressDetail.class);
                startActivity(intent);
            }else if (className.equals("updateFirmActivity")){
                Intent intent = new Intent();
                intent.putExtra("address",county);
                LogUtils.log("county----------->"+county);
                intent.putExtra("detailAddress",modify_information.getText().toString().trim());
                intent.setClass(SelectDetailArea.this,FirmUpdateActivity.class);
                startActivity(intent);
            }



        }
    }
    public void getAddress_info(String province, String city, final String county){
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/addressDisplay"+"?prov="+province+"&city="+city+"&county="+county;
        RequestParams requestParams=new RequestParams(url);
        Log.d("详细地址的返回值",requestParams+"");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("详细地址的返回值",""+result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String address_xiangxi=jsonObject.getString("address");

                    Intent intent=new Intent(SelectDetailArea.this,InvoiceInformation.class);

                    intent.putExtra("address",county);
                    LogUtils.log("county----------->"+county);
                    intent.putExtra("detailAddress",modify_information.getText().toString().trim());
                    intent.putExtra("info",address_xiangxi);
                    startActivity(intent);

                    startActivity(intent);
                } catch (JSONException e) {
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
    public void setResultInText(InvoiceInformationBean bean) {

    }

    @Override
    public void setRegionInText(String result) {
        Intent intent=new Intent(SelectDetailArea.this,InvoiceInformation.class);

        intent.putExtra("address",county);
        LogUtils.log("county----------->"+county);
        intent.putExtra("detailAddress",modify_information.getText().toString().trim());
        intent.putExtra("info",result);
        startActivity(intent);

    }

    @Override
    public void saveModifyInvoiceInformationSuccess() {

    }

    @Override
    public void saveModifyInvoiceInformationFailed() {

    }
}
