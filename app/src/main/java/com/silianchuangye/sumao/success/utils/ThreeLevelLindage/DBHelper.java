package com.silianchuangye.sumao.success.utils.ThreeLevelLindage;

import android.content.Context;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.RegisterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class DBHelper {
    private Context context;
    private  String data;

    public DBHelper(Context context) {
        super();
        this.context = context;
//        dbm = new DBManager(context);
        LogUtils.log("context--->"+context);

    }

    public ArrayList<Area> getCity(String pcode) {
        data = StreamUtils.get(this.context, R.raw.address);
        ArrayList<Area> list = new ArrayList<>();
        Gson gson = new Gson();
        RegisterBean registerBean = gson.fromJson(data, RegisterBean.class);
        List<RegisterBean.ListBean> regions = registerBean.getList();
        for (RegisterBean.ListBean region : regions) {
            if (region.getRegionCode().equals(pcode)) {
                List<RegisterBean.ListBean.ProvincesBean> provinces = region.getProvinces();
                for (int i = 0; i < provinces.size(); i++) {
                    RegisterBean.ListBean.ProvincesBean province = provinces.get(i);
                    Area area = new Area();
                    area.setName(province.getProvinceName());
                    area.setCode(province.getProvinceCode());
                    list.add(area);
                }
            }
        }
        return list;
    }
//    public ArrayList<Area> getCity(String pcode){
//        dbm.openDatabase();
//        db = dbm.getDatabase();
//        ArrayList<Area> list = new ArrayList<>();
//        try {
//            String sql = "select * from city where pcode='"+pcode+"'";
//            Cursor cursor = db.rawQuery(sql,null);
//            cursor.moveToFirst();
//            while(!cursor.isLast()){
//                String code =  cursor.getString(cursor.getColumnIndex("code"));
//                byte[] bytes = cursor.getBlob(2);
//                String name = new String(bytes,"gbk");
//                Area area = new Area();
//                area.setName(name);
//                area.setCode(code);
//                area.setPcode(pcode);
//                list.add(area);
//                cursor.moveToNext();
//            }
//            String code = cursor.getString(cursor.getColumnIndex("code"));
//            byte[] bytes = cursor.getBlob(2);
//            String name = new String(bytes,"gbk");
//            Area area = new Area();
//            area.setName(name);
//            area.setCode(code);
//            area.setPcode(pcode);
//            list.add(area);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        dbm.closeDatabase();
//        db.close();
//        return list;
//    }

    public ArrayList<Area> getProvince() {
        data = StreamUtils.get(this.context, R.raw.address);
        ArrayList<Area> list = new ArrayList<>();
        Gson gson = new Gson();
        RegisterBean registerBean = gson.fromJson(data, RegisterBean.class);
        List<RegisterBean.ListBean> registes = registerBean.getList();

        for (RegisterBean.ListBean areas : registes) {
            Area area = new Area();
            area.setCode(areas.getRegionCode());
            area.setName(areas.getRegionName());
            list.add(area);
        }
        return list;
    }

    public ArrayList<Area> getDistrict(String pcode) {
        data = StreamUtils.get(this.context, R.raw.address);
        ArrayList<Area> list = new ArrayList<>();
        Gson gson = new Gson();
        RegisterBean registerBean = gson.fromJson(data, RegisterBean.class);

        List<RegisterBean.ListBean> regions = registerBean.getList();
        for (int k = 0; k < regions.size(); k++) {
            List<RegisterBean.ListBean.ProvincesBean> provinces = regions.get(k).getProvinces();

            for (int j = 0; j < provinces.size(); j++) {
                RegisterBean.ListBean.ProvincesBean province = provinces.get(j);

                if (province.getProvinceCode().equals(pcode)) {
                    List<RegisterBean.ListBean.ProvincesBean.CitysBean> citys = province.getCitys();
                    for (int i = 0; i < citys.size(); i++) {
                        RegisterBean.ListBean.ProvincesBean.CitysBean city = citys.get(i);
                        Area area = new Area();
                        area.setName(city.getCityName());
                        area.setCode(city.getCityCode());
                        list.add(area);
                    }
                }
            }

        }
        return list;
    }
//    public ArrayList<Area> getProvince(){
//        dbm.openDatabase();
//        db = dbm.getDatabase();
//        ArrayList<Area> list = new ArrayList<>();
//        try {
//            String sql = "select * from province";
//            Cursor cursor = db.rawQuery(sql,null);
//            cursor.moveToFirst();
//            while (!cursor.isLast()){
//                String code=cursor.getString(cursor.getColumnIndex("code"));
//                byte bytes[]=cursor.getBlob(2);
//                String name=new String(bytes,"gbk");
//                Area area=new Area();
//                area.setName(name);
//                area.setCode(code);
//                list.add(area);
//                cursor.moveToNext();
//            }
//            String code=cursor.getString(cursor.getColumnIndex("code"));
//            byte bytes[]=cursor.getBlob(2);
//            String name=new String(bytes,"gbk");
//            Area area=new Area();
//            area.setName(name);
//            area.setCode(code);
//            list.add(area);
//
//        } catch (Exception e) {
//            return null;
//        }
//        dbm.closeDatabase();
//        db.close();
//        return list;
//
//    }

//    public ArrayList<Area> getDistrict(String pcode) {
//        dbm.openDatabase();
//        db = dbm.getDatabase();
//        ArrayList<Area> list = new ArrayList<Area>();
//        try {
//            String sql = "select * from district where pcode='"+pcode+"'";
//            Cursor cursor = db.rawQuery(sql,null);
//            if (cursor.moveToFirst()) {
//                while (!cursor.isLast()) {
//                    String code = cursor.getString(cursor
//                            .getColumnIndex("code"));
//                    byte bytes[] = cursor.getBlob(2);
//                    String name = new String(bytes, "gbk");
//                    Area Area = new Area();
//                    Area.setName(name);
//                    Area.setPcode(code);
//                    list.add(Area);
//                    cursor.moveToNext();
//                }
//                String code = cursor.getString(cursor.getColumnIndex("code"));
//                byte bytes[] = cursor.getBlob(2);
//                String name = new String(bytes, "gbk");
//                Area Area = new Area();
//                Area.setName(name);
//                Area.setPcode(code);
//                list.add(Area);
//            }
//
//        } catch (Exception e) {
//            Log.i("wer", e.toString());
//        }
//        dbm.closeDatabase();
//        db.close();
//        return list;
//
//    }

}
