package com.springapp.mvc.modelsToXml.OrgnlPmtInfAndStsUnd;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by maksat on 01.04.2019.
 */
public class Amt {
    private float InstdAmt;


    public float getInstdAmt() {
        return InstdAmt;
    }
    @XmlElement
    public void setInstdAmt(float instdAmt) {
        InstdAmt = instdAmt;
    }
}
