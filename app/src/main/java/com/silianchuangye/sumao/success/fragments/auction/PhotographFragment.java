package com.silianchuangye.sumao.success.fragments.auction;


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
public class PhotographFragment extends Fragment {
    private WebView wv_photo;


    public PhotographFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_photograph, container, false);
        wv_photo= (WebView) view.findViewById(R.id.wv_photo);
        wv_photo.loadUrl("http://192.168.32.105/crs/browse/productDisplay.jsp?productId=prod260022");
        wv_photo.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wv_photo.loadUrl(url);
                return true;
            }
        });
        return view;
    }

}
