package com.silianchuangye.sumao.success.utils.ThreeLevelLindage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class MenuItemAdapter extends BaseAdapter {

    private Context context;
    //    菜单列表
    private List<Area> mListData;
    private int selectedPos = -1;
    private String selectedText = "";
    private int normalDrawleId;
    private Drawable selectedDrawble;
    private float textSize;
    private View.OnClickListener onClickListener;
    private OnItemClickListener mOnItemClickListener;

    public MenuItemAdapter(Context context, List<Area> mListData, int selectedDrawble, int normalDrawleId) {
        this.context = context;
        this.mListData = mListData;
        this.selectedDrawble = context.getResources().getDrawable(selectedDrawble);
        this.normalDrawleId = normalDrawleId;
        init();
    }

    private void init() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = ((Integer) view.getTag());
                setSelectedPosition(selectedPos);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, selectedPos);
                }
            }
        };
    }

    /*
    设置选中的position,并通知刷新其他列表
     */
    private void setSelectedPosition(int pos) {
        if (this.mListData != null && pos < mListData.size()) {
            this.selectedPos = pos;
            this.selectedText = mListData.get(pos).getName();
            notifyDataSetChanged();
        }
    }

    /*
    设置选中的position,但不通知刷新
     */
    public void setSelectedPositionNoNotify(int pos, ArrayList<Area> list) {
        selectedPos = pos;
        mListData = list;
        if (mListData != null && pos < mListData.size()) {
            selectedText = mListData.get(pos).getName();
        }
    }

    /**
     * 获取选中的position
     *
     * @return
     */
    public int getSelectedPosition() {
        if (mListData != null && selectedPos < mListData.size()) {
            return selectedPos;
        }
        return -1;
    }

    /**
     * 设置列表字体大小
     */
    public void setTextSize(float size) {
        this.textSize = size;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int i) {
        return mListData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        TextView view;
        if (convertView == null) {
            view = (TextView) LayoutInflater.from(context).inflate(R.layout.choose_item, viewGroup, false);
        } else {
            view = ((TextView) convertView);
        }
        view.setTag(position);
        String mString = "";
        if ((mListData != null)) {
            if (position < mListData.size()) {
                mString = mListData.get(position).getName();
            }
        }
        if (mString.contains("不限")) {
            view.setText("不限");
        } else {
            view.setText(mString);
        }
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        if (selectedText != null && selectedText.equals(mString)) {
            view.setBackgroundDrawable(selectedDrawble);
        } else {
            view.setBackgroundDrawable(context.getResources().getDrawable(normalDrawleId));
        }
        view.setPadding(20, 0, 0, 0);
        view.setOnClickListener(onClickListener);


        return view;
    }
}
