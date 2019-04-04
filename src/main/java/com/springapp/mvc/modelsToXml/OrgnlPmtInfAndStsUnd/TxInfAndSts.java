package com.springapp.mvc.modelsToXml.OrgnlPmtInfAndStsUnd;


import javax.xml.bind.annotation.XmlElement;

public class TxInfAndSts {



    private String OrgnlEndToEndId;
    private String TxSts;
    private StsRsnInf stsRsnInf;
    private OrgnlTxRef orgnlTxRef;

    public String getOrgnlEndToEndId() {
        return OrgnlEndToEndId;
    }
    @XmlElement
    public void setOrgnlEndToEndId(String orgnlEndToEndId) {
        OrgnlEndToEndId = orgnlEndToEndId;
    }

    public String getTxSts() {
        return TxSts;
    }
    @XmlElement
    public void setTxSts(String txSts) {
        TxSts = txSts;
    }

    public StsRsnInf getStsRsnInf() {
        return stsRsnInf;
    }
    @XmlElement
    public void setStsRsnInf(StsRsnInf stsRsnInf) {
        this.stsRsnInf = stsRsnInf;
    }

    public OrgnlTxRef getOrgnlTxRef() {
        return orgnlTxRef;
    }
    @XmlElement
    public void setOrgnlTxRef(OrgnlTxRef orgnlTxRef) {
        this.orgnlTxRef = orgnlTxRef;
    }
}
