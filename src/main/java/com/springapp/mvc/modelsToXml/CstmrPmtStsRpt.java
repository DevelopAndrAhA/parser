package com.springapp.mvc.modelsToXml;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CstmrPmtStsRpt")
public class CstmrPmtStsRpt {

    private com.springapp.mvc.modelsToXml.GrpHdr grpHdr;
    private com.springapp.mvc.modelsToXml.OrgnlGrpInfAndSts orgnlGrpInfAndSts;
    private OrgnlPmtInfAndSts orgnlPmtInfAndSts;

    public GrpHdr getGrpHdr() {
        return grpHdr;
    }
    @XmlElement
    public void setGrpHdr(GrpHdr grpHdr) {
        this.grpHdr = grpHdr;
    }

    public OrgnlGrpInfAndSts getOrgnlGrpInfAndSts() {
        return orgnlGrpInfAndSts;
    }
    @XmlElement
    public void setOrgnlGrpInfAndSts(OrgnlGrpInfAndSts orgnlGrpInfAndSts) {
        this.orgnlGrpInfAndSts = orgnlGrpInfAndSts;
    }

    public OrgnlPmtInfAndSts getOrgnlPmtInfAndSts() {
        return orgnlPmtInfAndSts;
    }
    @XmlElement
    public void setOrgnlPmtInfAndSts(OrgnlPmtInfAndSts orgnlPmtInfAndSts) {
        this.orgnlPmtInfAndSts = orgnlPmtInfAndSts;
    }
}