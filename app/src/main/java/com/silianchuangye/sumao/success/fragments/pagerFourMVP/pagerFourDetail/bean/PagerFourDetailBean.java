package com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.bean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class PagerFourDetailBean implements Serializable {

    /**
     * content : <strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 中国石油网消息&nbsp;</strong>11月15日，记者从中国石油石油化工研究院获悉，11月初，中国石油“环保型超重力液化气深度脱硫(LDS)成套技术”成果鉴定与技术交流推介会在甘肃庆阳召开。由石化院研发的让液化气深度脱硫不排渣的LDS技术得到高度评价。这标志着石化院又一质量环境技术利器进入规模化加速推广景气周期。
     <p>
     　　科技评估中心、咨询中心等组成的鉴定委员会审慎评估后，一致认为“环保型超重力液化气深度脱硫成套技术”为世界首创，长周期运行的经济技术指标先进，总体达到国际领先水平，具有良好的经济和社会效益。特别是LDS技术的环境效益十分显著，应首先加快推广，推动企业更好地履行社会责任。
     </p>
     <p>
     　　在炼化高附加值产品生产和应用中，液化气是生产MTBE、聚丙烯等的重要原料，目前应用Merox碱液抽提法即“碱法”占液化气脱硫醇装置的90%。然而，此工艺生成的二硫化物无法高效从碱液中分离造成反携带，碱液再生不完全而频繁更换新碱又造成碱渣大量排放，成为“碱法”的技术瓶颈。
     </p>
     <p>
     　　中国石油历经10年应用基础研究、关键设备研究、成套技术开发到工业设计和工业试验，由石化院在炼油与化工分公司及科技管理部等部门的支持下自主开发LDS技术，实现了液化气深度脱硫、碱渣大幅减排和产品质量升级三重目标。工业试验装置技术经济和安全环保类指标在同类技术中领先，2015年通过生产标定。
     </p>
     <p>
     　　预计到2020年，我国液化石油气产量达到3030万吨，全球产量高达2亿吨以上。LDS技术的成功研发，将有助于中国石油炼化业务提质增效，也将在我国汽油质量升级与节能环保的进程中大放异彩。
     </p>
     * source : 中国石油新闻中心
     * createDate : {"time":1479398400000}
     * updateDate : {"time":1480423232000}
     */

    private String content;
    private String source;
    private CreateDateBean createDate;
    private UpdateDateBean updateDate;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public CreateDateBean getCreateDate() {
        return createDate;
    }

    public void setCreateDate(CreateDateBean createDate) {
        this.createDate = createDate;
    }

    public UpdateDateBean getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(UpdateDateBean updateDate) {
        this.updateDate = updateDate;
    }

    public static class CreateDateBean implements Serializable {
        /**
         * time : 1479398400000
         */

        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class UpdateDateBean implements Serializable  {
        /**
         * time : 1480423232000
         */

        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
