package com.springapp.mvc.modelsToXml;

import com.springapp.mvc.modelsToXml.OrgnlPmtInfAndStsUnd.TxInfAndSts;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;


public class OrgnlPmtInfAndSts {



    private long OrgnlPmtInfId;
    private String PmtInfSts;
    private List<TxInfAndSts> txInfAndSts;

    public long getOrgnlPmtInfId() {
        return OrgnlPmtInfId;
    }
    @XmlElement
    public void setOrgnlPmtInfId(long orgnlPmtInfId) {
        OrgnlPmtInfId = orgnlPmtInfId;
    }




    public String getPmtInfSts() {
        return PmtInfSts;
    }
    @XmlElement
    public void setPmtInfSts(String pmtInfSts) {
        PmtInfSts = pmtInfSts;
    }

    public List<TxInfAndSts> getTxInfAndSts() {
        return txInfAndSts;
    }
    @XmlElement
    public void setTxInfAndSts(List<TxInfAndSts> txInfAndSts) {
        this.txInfAndSts = txInfAndSts;
    }

}
