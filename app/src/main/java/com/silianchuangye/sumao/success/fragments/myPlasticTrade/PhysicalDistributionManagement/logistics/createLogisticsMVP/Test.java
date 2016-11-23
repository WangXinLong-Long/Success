package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP;

/**
 * Created by Administrator on 2016/11/17.
 */
public class Test {
    /*
    *
if (result.contains("order")) {
                    try {
                        JSONObject j = new JSONObject(result);
                        String order = j.getString("order");
                        JSONArray ja = new JSONArray(order);

                        for (int i = 0; i < ja.length(); i++) {
                            Createlogistics_ExpandInfo expandInfo1 = new Createlogistics_ExpandInfo();
                            JSONObject job = (JSONObject) ja.get(i);
                            //给group添加数据
                            expandInfo1.order_name = job.getString("firstName");
                            if (job.toString().contains("cl_gongsi")) {
                                expandInfo1.company_name = job.getString("cl_gongsi");
                            } else {
                                expandInfo1.company_name = "";
                            }
                            expandInfo1.date = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(job.getString("submittedDate")));
                            expandInfo1.order_num = job.getString("id");
                            //给子item添加
                            listInfo = new Createlogistics_ListInfo();
                            String cl = job.getString("cl");
                            JSONArray jay = new JSONArray(cl);
                            for (int z = 0; z < jay.length(); z++) {
                                JSONObject childJob = (JSONObject) jay.get(z);
                                listInfo.id = childJob.getString("commerceId");
                                if (cl.contains("cl_fenlei")) {
                                    listInfo.sort = childJob.getString("cl_fenlei");
                                } else {
                                    listInfo.sort = "";
                                }
                                if (cl.contains("cl_mingcheng")) {
                                    listInfo.product_name = childJob.getString("cl_mingcheng");
                                } else {
                                    listInfo.product_name = "";
                                }
                                if (cl.contains("cl_shuliang")) {
                                    listInfo.num = childJob.getString("cl_shuliang");
                                } else {
                                    listInfo.num = "";
                                }
                                if (cl.contains("shippingMethod")) {
                                    listInfo.logistics_name = childJob.getString("shippingMethod");
                                } else {
                                    listInfo.logistics_name = "";
                                }
                                listInfo.only_price = childJob.getString("cl_jine");
                                if (cl.contains("vailableQuantity")) {
                                    listInfo.can_num = childJob.getString("vailableQuantity");
                                } else {
                                    listInfo.can_num = "";
                                }
                                if (cl.contains("cl_cangku")) {
                                    listInfo.cangku_name = childJob.getString("cl_cangku");
                                } else {
                                    listInfo.cangku_name = "";
                                }
                                if (cl.contains("deliveryEndDate")) {
                                    listInfo.date = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(childJob.getString("deliveryStartDate"))) + "    " +
                                            new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(childJob.getString("deliveryEndDate")));
                                } else {
                                    listInfo.date = "";
                                }
                                if (cl.contains("cl_gongsi")) {
                                    listInfo.gongsi = job.getString("cl_gongsi");
                                } else {
                                    listInfo.gongsi = "";
                                }
                                listInfo.edt_num = 0 + "";
                                expandInfo1.list.add(listInfo);
                            }
                            expandList.add(expandInfo1);
                        }
                        adapter = new CreateLogisticsAdapter(CreateLogistics.this, expandList, CreateLogistics.this);
                        expand_lv_create_logistics.setAdapter(adapter);
                        if (adapter != null && expandList != null) {
                            for (int i = 0; i < adapter.getGroupCount(); i++) {
                                expand_lv_create_logistics.expandGroup(i);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CreateLogistics.this, "请重新登陆", Toast.LENGTH_SHORT).show();
                    new TiQu(CreateLogistics.this).showLogin();
                    CreateLogistics.this.finish();
                }*/
}
