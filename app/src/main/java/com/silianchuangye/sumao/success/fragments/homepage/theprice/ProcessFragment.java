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
public class ProcessFragment extends Fragment {
    private View view;
    private WebView web_PrcessFragment;
    public ProcessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_process, container, false);
        web_PrcessFragment= (WebView) view.findViewById(R.id.web_fragment_process);
        web_PrcessFragment.getSettings().setJavaScriptEnabled(true);
        web_PrcessFragment.loadUrl("http://www.baidu.com/");
        web_PrcessFragment.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web_PrcessFragment.loadUrl(url);
                return true;
            }
        });
        return view;
    }

}
