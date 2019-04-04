package com.springapp.mvc.modelsToXml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by maksat on 29.03.2019.
 */
public class InitgPty{
    private com.springapp.mvc.modelsToXml.Id id;

    public Id getId() {
        return id;
    }
    @XmlElement
    public void setId(Id id) {
        this.id = id;
    }
}
