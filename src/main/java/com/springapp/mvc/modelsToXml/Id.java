package com.springapp.mvc.modelsToXml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by maksat on 29.03.2019.
 */
public class Id{
    private OrgId orgId;

    public OrgId getOrgId() {
        return orgId;
    }
    @XmlElement
    public void setOrgId(OrgId orgId) {
        this.orgId = orgId;
    }
}
