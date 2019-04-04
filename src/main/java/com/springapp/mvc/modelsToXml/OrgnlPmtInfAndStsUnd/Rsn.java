package com.springapp.mvc.modelsToXml.OrgnlPmtInfAndStsUnd;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by maksat on 01.04.2019.
 */
public class Rsn {
    private String Cd;

    public String getCd() {
        return Cd;
    }
    @XmlElement
    public void setCd(String cd) {
        Cd = cd;
    }
}
