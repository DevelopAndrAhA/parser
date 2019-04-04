package com.springapp.mvc.modelsToXml.OrgnlPmtInfAndStsUnd;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by maksat on 01.04.2019.
 */
public class OrgnlTxRef {
    private Amt amt;
    private String ReqdExctnDt;

    public Amt getAmt() {
        return amt;
    }
    @XmlElement
    public void setAmt(Amt amt) {
        this.amt = amt;
    }

    public String getReqdExctnDt() {
        return ReqdExctnDt;
    }

    public void setReqdExctnDt(String reqdExctnDt) {
        ReqdExctnDt = reqdExctnDt;
    }
}
