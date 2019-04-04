package com.springapp.mvc.models;

import javax.persistence.*;
import java.util.List;


@Entity(name = "GrpHdr")
@Table(name = "grphdr")
public class GrpHdr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "grpHdr_id")
    private long grpHdr_id;
    private long MsgId;
    private String CreDtTm;
    private int NbOfTxs;
    private float CtrlSum;
    private String Nm;
    private String dateWrite;
    private String wrUser;
    @OneToMany(mappedBy = "grpHdr", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<PmtInf> pmtInfs;

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    public String getCreDtTm() {
        return CreDtTm;
    }

    public void setCreDtTm(String creDtTm) {
        CreDtTm = creDtTm;
    }

    public int getNbOfTxs() {
        return NbOfTxs;
    }

    public void setNbOfTxs(int nbOfTxs) {
        NbOfTxs = nbOfTxs;
    }

    public float getCtrlSum() {
        return CtrlSum;
    }

    public void setCtrlSum(float ctrlSum) {
        CtrlSum = ctrlSum;
    }

    public String getNm() {
        return Nm;
    }

    public void setNm(String nm) {
        Nm = nm;
    }


    public long getGrpHdr_id() {
        return grpHdr_id;
    }

    public void setGrpHdr_id(long grpHdr_id) {
        this.grpHdr_id = grpHdr_id;
    }

    public List<PmtInf> getPmtInfs() {
        return pmtInfs;
    }

    public void setPmtInfs(List<PmtInf> pmtInfs) {
        this.pmtInfs = pmtInfs;
    }

    public String getDateWrite() {
        return dateWrite;
    }

    public void setDateWrite(String dateWrite) {
        this.dateWrite = dateWrite;
    }

    public String getWrUser() {
        return wrUser;
    }

    public void setWrUser(String wrUser) {
        this.wrUser = wrUser;
    }
}
