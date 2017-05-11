package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;


public class TimeTextView extends TextView implements Runnable {

	Paint mPaint;

	private long[] times;

	private long mday, mhour, mmin, msecond;

	private boolean run = false;

	public TimeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.TimeTextView);

		array.recycle();
	}

	public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mPaint = new Paint();
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.TimeTextView);

		array.recycle();
	}

	public TimeTextView(Context context) {
		super(context);
	}

	public long[] getTimes() {
		return times;
	}

	public void setTimes(long[] times) {
		this.times = times;
		mday = times[0];
		mhour = times[1];
		mmin = times[2];
		msecond = times[3];

	}


	private void ComputeTime() {
		msecond--;
		if (msecond < 0) {
			mmin--;
			msecond = 59;
			if (mmin < 0) {
				mmin = 59;
				mhour--;
				if (mhour < 0) {
					// ����ʱ����
					mhour = 59;
					mday--;

				}
			}

		}

	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	@Override
	public void run() {

		run = true;

		ComputeTime();

		String strTime = "距离结束还剩:" + "<font color=\"#FF0033\">" + mhour + "</font>" + "小时"
				+ "<font color=\"#FF0033\">" + mmin + "</font>" + "分"
				+ "<font color=\"#FF0033\">" + msecond + "</font>" + "秒";

		this.setText(Html.fromHtml(strTime));

		postDelayed(this, 1000);

	}
}
