package com.springapp.mvc.modelsToXml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by maksat on 29.03.2019.
 */
public class GrpHdr {
    private String MsgId;
    private String CreDtTm;
    private InitgPty initgPty;
    public String getMsgId() {
        return MsgId;
    }
    @XmlElement
    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public String getCreDtTm() {
        return CreDtTm;
    }
    @XmlElement
    public void setCreDtTm(String creDtTm) {
        CreDtTm = creDtTm;
    }

    public InitgPty getInitgPty() {
        return initgPty;
    }
    @XmlElement
    public void setInitgPty(InitgPty initgPty) {
        this.initgPty = initgPty;
    }
}
