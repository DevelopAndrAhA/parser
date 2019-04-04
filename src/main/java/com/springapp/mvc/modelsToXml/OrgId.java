package com.springapp.mvc.modelsToXml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by maksat on 29.03.2019.
 */
public class OrgId{
    private String BICOrBEI;

    public String getBICOrBEI() {
        return BICOrBEI;
    }
    @XmlElement
    public void setBICOrBEI(String BICOrBEI) {
        this.BICOrBEI = BICOrBEI;
    }
}
