package com.silianchuangye.sumao.success.fragments.homepage.theprice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.silianchuangye.sumao.success.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RuleFragment extends Fragment {
private View view;
    private WebView web_RuleFragment;

    public RuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_rule, container, false);
        web_RuleFragment= (WebView) view.findViewById(R.id.web_fragment_rule);
        web_RuleFragment.getSettings().setJavaScriptEnabled(true);
        web_RuleFragment.loadUrl("http://www.baidu.com/");
        web_RuleFragment.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web_RuleFragment.loadUrl(url);
                return true;
            }
        });
        return view;
    }

}
