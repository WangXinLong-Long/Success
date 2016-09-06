package com.silianchuangye.sumao.success.fragments.homepage.auction;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContractFragment extends Fragment {


    private View view;
    private WebView contract_web_view;
    private Bundle bundle;
    private String contract;
    private WebSettings settings;

    public ContractFragment() {
        // Required empty public constructorll
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contract, container, false);
        contract_web_view = ((WebView) view.findViewById(R.id.contract_web_view));
        bundle = getArguments();
        contract = bundle.getString("contract");
        settings = contract_web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        contract_web_view.loadUrl(SuMaoConstant.SUMAO_FILE_IP+contract);
        contract_web_view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                contract_web_view.loadUrl(url);
                return true;
            }
        });
//        contract_web_view
        return view;
    }

}
