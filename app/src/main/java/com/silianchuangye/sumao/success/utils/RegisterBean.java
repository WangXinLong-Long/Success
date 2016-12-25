package com.silianchuangye.sumao.success.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class RegisterBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * regionCode : 13
         * regionName : 华北地区
         * provinces : [{"provinceCode":"1311","provinceName":"北京","citys":[{"cityCode":"131101","cityName":"北京市"},{"cityCode":"131102","cityName":"北京外县"},{"cityCode":"131199","cityName":"其他"}]}]
         */

        private String regionCode;
        private String regionName;
        private List<ProvincesBean> provinces;

        public String getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(String regionCode) {
            this.regionCode = regionCode;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public List<ProvincesBean> getProvinces() {
            return provinces;
        }

        public void setProvinces(List<ProvincesBean> provinces) {
            this.provinces = provinces;
        }

        public static class ProvincesBean implements Serializable {
            /**
             * provinceCode : 1311
             * provinceName : 北京
             * citys : [{"cityCode":"131101","cityName":"北京市"},{"cityCode":"131102","cityName":"北京外县"},{"cityCode":"131199","cityName":"其他"}]
             */

            private String provinceCode;
            private String provinceName;
            private List<CitysBean> citys;

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public List<CitysBean> getCitys() {
                return citys;
            }

            public void setCitys(List<CitysBean> citys) {
                this.citys = citys;
            }

            public static class CitysBean implements Serializable{
                /**
                 * cityCode : 131101
                 * cityName : 北京市
                 */

                private String cityCode;
                private String cityName;

                public String getCityCode() {
                    return cityCode;
                }

                public void setCityCode(String cityCode) {
                    this.cityCode = cityCode;
                }

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }
            }
        }
    }
}
