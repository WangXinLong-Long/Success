package com.silianchuangye.sumao.success.utils.ThreeLevelLindage;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12.
 */

public class CascadingMenuPopWindow extends PopupWindow {
    private Context context;
    private CascadingMenuView cascadingMenuView;
    private ArrayList<Area> areas = null;
    //    提供给外的接口
    private CascadingMenuViewOnSelectListener menuViewOnSelectListener;

    public void setMenuItems(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public void setMenuViewOnSelectListener(CascadingMenuViewOnSelectListener menuViewOnSelectListener) {
        this.menuViewOnSelectListener = menuViewOnSelectListener;
    }

    public CascadingMenuPopWindow(Context context, ArrayList<Area> list) {
        super(context);
        this.context = context;
        this.areas = list;
        init();
    }

    private void init() {
//        实例化级联菜单
        cascadingMenuView = new CascadingMenuView(areas,context);
        setContentView(cascadingMenuView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//        设置回调接口
        cascadingMenuView.setCascadingMenuViewOnSelectListener(new MCascadingMenuViewOnSelectListener());
    }

    class MCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener{

        @Override
        public void getValue(Area menuItem) {
            if (menuViewOnSelectListener!=null){
                menuViewOnSelectListener.getValue(menuItem);
                dismiss();
            }
        }
    }
}
