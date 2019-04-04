package com.springapp.mvc.modelsToXml.OrgnlPmtInfAndStsUnd;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by maksat on 01.04.2019.
 */
public class StsRsnInf {
    private Rsn rsn;
    private String AddtlInf;

    public Rsn getRsn() {
        return rsn;
    }
    @XmlElement
    public void setRsn(Rsn rsn) {
        this.rsn = rsn;
    }

    public String getAddtlInf() {
        return AddtlInf;
    }
    @XmlElement
    public void setAddtlInf(String addtlInf) {
        AddtlInf = addtlInf;
    }
}
