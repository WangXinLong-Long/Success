package com.silianchuangye.sumao.success.utils.ThreeLevelLindage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12.
 */

public class CascadingMenuView extends RelativeLayout {

    private static final String TAG = CascadingMenuView.class.getSimpleName();
//    三级菜单选择后触发的接口，即最终选择的内容
    private CascadingMenuViewOnSelectListener mOnSelectListener;

    private ListView firstMenuListView;
    private ListView secondMenuListView;
    private ListView thirdMenuListView;

//  每次选择的子菜单内容
    private ArrayList<Area> thirdItem = new ArrayList<>();
    private ArrayList<Area> secondItem = new ArrayList<>();
    private ArrayList<Area> menuItem = new ArrayList<>();

    private MenuItemAdapter firstMenuListViewAdapter;
    private MenuItemAdapter secondMenuListViewAdapter;
    private MenuItemAdapter thirdMenuListViewAdapter;

    private int firstPosition = 0;
    private int secondPosition = 0;
    private int thirdPosition = 0;

    private DBHelper dbHelper;

    private Context context;

    public CascadingMenuView( ArrayList<Area> menuList, Context context1) {
        super(context1);
        this.menuItem = menuList;
        this.context = context1;
        dbHelper = new DBHelper(context1);
        init(context1);
    }

    public CascadingMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        dbHelper = new DBHelper(context);
        init(this.context);
    }

    private void init(final Context context) {
        LayoutInflater inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        inflater.inflate(R.layout.view_region,this,true);
        setBackgroundColor(getResources().getColor(R.color.white));
        firstMenuListView = ((ListView) findViewById(R.id.listView));
        secondMenuListView = ((ListView) findViewById(R.id.listView2));
        thirdMenuListView = ((ListView) findViewById(R.id.listView3));

        firstMenuListViewAdapter = new MenuItemAdapter(context,menuItem,R.drawable.choose_item_selected,R.drawable.choose_eara_item_selector);
        firstMenuListViewAdapter.setTextSize(17);
        firstMenuListViewAdapter.setSelectedPositionNoNotify(firstPosition,menuItem);
        firstMenuListView.setAdapter(firstMenuListViewAdapter);
        firstMenuListViewAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                secondMenuListView.setVisibility(View.VISIBLE);
                thirdMenuListView.setVisibility(View.INVISIBLE);
//                选中主菜单，清空原本子菜单内容，增加新内容
                secondItem.clear();
                secondItem = getSecondItem(menuItem.get(position).getCode());
                Area area =new Area();
                area.setPcode("0");
                area.setName("请选择省");
                area.setCode("0");
                secondItem.add(0,area);
                if(secondItem!=null){
                    Log.d(TAG, "" + secondItem.size());}
//                通知适配器刷新
                secondMenuListView.setVisibility(View.VISIBLE);
                secondMenuListViewAdapter.notifyDataSetChanged();
                secondMenuListViewAdapter.setSelectedPositionNoNotify(0,secondItem);
                Toast.makeText(context,""+menuItem.get(position).getCode(),Toast.LENGTH_SHORT).show();

                thirdItem.clear();
//                thirdItem = getThirdItem(secondItem.get(0).getCode());
////                通知适配器刷新
//                thirdMenuListViewAdapter.notifyDataSetChanged();
//                thirdMenuListViewAdapter.setSelectedPositionNoNotify(0,thirdItem);
            }
        });

        secondItem = getSecondItem(menuItem.get(firstPosition).getCode());
        Log.d(TAG, secondItem.get(secondPosition).toString());
        thirdItem = getThirdItem(secondItem.get(secondPosition).getCode());
        secondMenuListViewAdapter = new MenuItemAdapter(context,secondItem,R.drawable.choose_item_selected,R.drawable.choose_eara_item_selector);
        secondMenuListViewAdapter.setTextSize(15);
        secondMenuListViewAdapter.setSelectedPositionNoNotify(secondPosition,secondItem);
        secondMenuListView.setAdapter(secondMenuListViewAdapter);

        secondMenuListViewAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                thirdMenuListView.setVisibility(View.VISIBLE);
                thirdItem.clear();
                thirdItem = getThirdItem(secondItem.get(position).getCode());
                Area area =new Area();
                area.setPcode("0");
                area.setName("请选择市");
                area.setCode("0");
                thirdItem.add(0,area);
//                通知适配器刷新
                thirdMenuListViewAdapter.notifyDataSetChanged();
                thirdMenuListViewAdapter.setSelectedPositionNoNotify(0,thirdItem);
            }
        });

//        初始化三级主菜单
        thirdItem = getThirdItem(secondItem.get(secondPosition).getCode());
        thirdMenuListViewAdapter = new MenuItemAdapter(context,thirdItem,R.drawable.choose_item_right,R.drawable.choose_plate_item_selector);
        thirdMenuListViewAdapter.setTextSize(13);
        thirdMenuListViewAdapter.setSelectedPositionNoNotify(thirdPosition,thirdItem);
        thirdMenuListView.setAdapter(thirdMenuListViewAdapter);
        thirdMenuListViewAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Area menuItem = thirdItem.get(position);
                if (mOnSelectListener!=null){
                    mOnSelectListener.getValue(menuItem);
                }
                Log.d(TAG, menuItem.toString());
            }
        });

//        设置默认选择
        setDefaultSelect();
    }

    private void setDefaultSelect() {
//        firstMenuListView.setSelection(firstPosition);
//        secondMenuListView.setSelection(secondPosition);
//        thirdMenuListView.setSelection(thirdPosition);
    }

    public ArrayList<Area> getSecondItem(String pcode){
        ArrayList<Area> list = dbHelper.getCity(pcode);
        return list;
    }

    public ArrayList<Area> getThirdItem(String pcode){
        ArrayList<Area> list = dbHelper.getDistrict(pcode);
        return list;
    }

    public void setCascadingMenuViewOnSelectListener(CascadingMenuViewOnSelectListener onSelectListener){
        mOnSelectListener = onSelectListener;
    }
}
