package com.springapp.mvc.modelsToXml;

import javax.xml.bind.annotation.XmlElement;


public class OrgnlGrpInfAndSts {



    private String OrgnlMsgId;
    private String OrgnlMsgNmId;
    private String OrgnlNbOfTxs;
    private String OrgnlCtrlSum;
    private String GrpSts;

    public String getOrgnlMsgId() {
        return OrgnlMsgId;
    }
    @XmlElement
    public void setOrgnlMsgId(String orgnlMsgId) {
        OrgnlMsgId = orgnlMsgId;
    }

    public String getOrgnlMsgNmId() {
        return OrgnlMsgNmId;
    }
    @XmlElement
    public void setOrgnlMsgNmId(String orgnlMsgNmId) {
        OrgnlMsgNmId = orgnlMsgNmId;
    }

    public String getOrgnlNbOfTxs() {
        return OrgnlNbOfTxs;
    }
    @XmlElement
    public void setOrgnlNbOfTxs(String orgnlNbOfTxs) {
        OrgnlNbOfTxs = orgnlNbOfTxs;
    }

    public String getOrgnlCtrlSum() {
        return OrgnlCtrlSum;
    }
    @XmlElement
    public void setOrgnlCtrlSum(String orgnlCtrlSum) {
        OrgnlCtrlSum = orgnlCtrlSum;
    }

    public String getGrpSts() {
        return GrpSts;
    }
    @XmlElement
    public void setGrpSts(String grpSts) {
        GrpSts = grpSts;
    }
}
