package com.silianchuangye.sumao.success.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Jobs Created on 2016/11/30.
 */
public class CustomGrideView extends GridView {
    public CustomGrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
