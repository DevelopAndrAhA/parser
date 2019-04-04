package com.springapp.mvc.models;



import javax.persistence.*;
@Entity(name = "PmtInf")
@Table(name = "pmtinf")
public class PmtInf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pIdPrKey")
    private long pIdPrKey;
    private long PmtInfId;
    private String PmtMtd;
    private boolean BtchBookg;
    private int NbOfTxs;
    private float CtrlSum;
    private String ChrgBr;
    private String ReqdExctnDt;
    private String Prtry;

    private String DbtrNm;
    private String DbtrAdr;
    private String DbtrAdr2;
    private long DbtrOthrId;
    private String DbtrOthrCd;
    private String DbtrOthrIssr;
    private String DbtrAcctIBAN;
    private String DbtrAgtMmbId;

    private String CdTrTxEndId;
    private float CdTrTxInstAmt;
    private String CdTxCdtrAgtMbId;

    private String CdtrNm;
    private String CdtrAdr;
    private String CdtrPrvBirthDt;
    private String CdtrPrvCitBth;
    private String CdtrPrvCtrBth;
    private long CdtrPrvOthId;
    private String CdtrPrvOthCd;
    private String CdtrPrvOthIssr;

    private String CdtrAcctIBAN;

    private String RmtInfUstrd;


    private String statReadAndGenXml;



    @Override
    public String toString() {
        return "PmtInf{" +
                "PmtInfId=" + PmtInfId +
                ", PmtMtd='" + PmtMtd + '\'' +
                ", BtchBookg=" + BtchBookg +
                ", NbOfTxs=" + NbOfTxs +
                ", CtrlSum=" + CtrlSum +
                ", ChrgBr='" + ChrgBr + '\'' +
                ", ReqdExctnDt='" + ReqdExctnDt + '\'' +
                ", Prtry='" + Prtry + '\'' +
                ", DbtrNm='" + DbtrNm + '\'' +
                ", DbtrAdr='" + DbtrAdr + '\'' +
                ", DbtrAdr2='" + DbtrAdr2 + '\'' +
                ", DbtrOthrId=" + DbtrOthrId +
                ", DbtrOthrCd='" + DbtrOthrCd + '\'' +
                ", DbtrOthrIssr='" + DbtrOthrIssr + '\'' +
                ", DbtrAcctIBAN='" + DbtrAcctIBAN + '\'' +
                ", DbtrAgtMmbId='" + DbtrAgtMmbId + '\'' +
                ", CdTrTxEndId=" + CdTrTxEndId +
                ", CdTrTxInstAmt=" + CdTrTxInstAmt +
                ", CdTxCdtrAgtMbId='" + CdTxCdtrAgtMbId + '\'' +
                ", CdtrNm='" + CdtrNm + '\'' +
                ", CdtrAdr='" + CdtrAdr + '\'' +
                ", CdtrPrvBirthDt='" + CdtrPrvBirthDt + '\'' +
                ", CdtrPrvCitBth='" + CdtrPrvCitBth + '\'' +
                ", CdtrPrvCtrBth='" + CdtrPrvCtrBth + '\'' +
                ", CdtrPrvOthId=" + CdtrPrvOthId +
                ", CdtrPrvOthCd='" + CdtrPrvOthCd + '\'' +
                ", CdtrPrvOthIssr='" + CdtrPrvOthIssr + '\'' +
                ", CdtrAcctIBAN='" + CdtrAcctIBAN + '\'' +
                ", RmtInfUstrd='" + RmtInfUstrd + '\'' +
                '}';
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grphdr_id")
    private GrpHdr grpHdr;


    public long getPIdPrKey() {
        return pIdPrKey;
    }

    public void setPIdPrKey(long id) {
        this.pIdPrKey = id;
    }

    public long getPmtInfId() {
        return PmtInfId;
    }

    public void setPmtInfId(long pmtInfId) {
        PmtInfId = pmtInfId;
    }

    public String getPmtMtd() {
        return PmtMtd;
    }

    public void setPmtMtd(String pmtMtd) {
        PmtMtd = pmtMtd;
    }

    public boolean isBtchBookg() {
        return BtchBookg;
    }

    public void setBtchBookg(boolean btchBookg) {
        BtchBookg = btchBookg;
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

    public String getChrgBr() {
        return ChrgBr;
    }

    public void setChrgBr(String chrgBr) {
        ChrgBr = chrgBr;
    }

    public String getReqdExctnDt() {
        return ReqdExctnDt;
    }

    public void setReqdExctnDt(String reqdExctnDt) {
        ReqdExctnDt = reqdExctnDt;
    }

    public String getPrtry() {
        return Prtry;
    }

    public void setPrtry(String prtry) {
        Prtry = prtry;
    }

    public String getDbtrNm() {
        return DbtrNm;
    }

    public void setDbtrNm(String dbtrNm) {
        DbtrNm = dbtrNm;
    }

    public String getDbtrAdr() {
        return DbtrAdr;
    }

    public void setDbtrAdr(String dbtrAdr) {
        DbtrAdr = dbtrAdr;
    }

    public String getDbtrAdr2() {
        return DbtrAdr2;
    }

    public void setDbtrAdr2(String dbtrAdr2) {
        DbtrAdr2 = dbtrAdr2;
    }

    public long getDbtrOthrId() {
        return DbtrOthrId;
    }

    public void setDbtrOthrId(long dbtrOthrId) {
        DbtrOthrId = dbtrOthrId;
    }

    public String getDbtrOthrCd() {
        return DbtrOthrCd;
    }

    public void setDbtrOthrCd(String dbtrOthrCd) {
        DbtrOthrCd = dbtrOthrCd;
    }

    public String getDbtrOthrIssr() {
        return DbtrOthrIssr;
    }

    public void setDbtrOthrIssr(String dbtrOthrIssr) {
        DbtrOthrIssr = dbtrOthrIssr;
    }

    public String getDbtrAcctIBAN() {
        return DbtrAcctIBAN;
    }

    public void setDbtrAcctIBAN(String dbtrAcctIBAN) {
        DbtrAcctIBAN = dbtrAcctIBAN;
    }

    public String getDbtrAgtMmbId() {
        return DbtrAgtMmbId;
    }

    public void setDbtrAgtMmbId(String dbtrAgtMmbId) {
        DbtrAgtMmbId = dbtrAgtMmbId;
    }

    public String getCdTrTxEndId() {
        return CdTrTxEndId;
    }

    public void setCdTrTxEndId(String cdTrTxEndId) {
        CdTrTxEndId = cdTrTxEndId;
    }

    public float getCdTrTxInstAmt() {
        return CdTrTxInstAmt;
    }

    public void setCdTrTxInstAmt(float cdTrTxInstAmt) {
        CdTrTxInstAmt = cdTrTxInstAmt;
    }

    public String getCdTxCdtrAgtMbId() {
        return CdTxCdtrAgtMbId;
    }

    public void setCdTxCdtrAgtMbId(String cdTxCdtrAgtMbId) {
        CdTxCdtrAgtMbId = cdTxCdtrAgtMbId;
    }

    public String getCdtrNm() {
        return CdtrNm;
    }

    public void setCdtrNm(String cdtrNm) {
        CdtrNm = cdtrNm;
    }

    public String getCdtrAdr() {
        return CdtrAdr;
    }

    public void setCdtrAdr(String cdtrAdr) {
        CdtrAdr = cdtrAdr;
    }

    public String getCdtrPrvBirthDt() {
        return CdtrPrvBirthDt;
    }

    public void setCdtrPrvBirthDt(String cdtrPrvBirthDt) {
        CdtrPrvBirthDt = cdtrPrvBirthDt;
    }

    public String getCdtrPrvCitBth() {
        return CdtrPrvCitBth;
    }

    public void setCdtrPrvCitBth(String cdtrPrvCitBth) {
        CdtrPrvCitBth = cdtrPrvCitBth;
    }

    public String getCdtrPrvCtrBth() {
        return CdtrPrvCtrBth;
    }

    public void setCdtrPrvCtrBth(String cdtrPrvCtrBth) {
        CdtrPrvCtrBth = cdtrPrvCtrBth;
    }

    public long getCdtrPrvOthId() {
        return CdtrPrvOthId;
    }

    public void setCdtrPrvOthId(long cdtrPrvOthId) {
        CdtrPrvOthId = cdtrPrvOthId;
    }

    public String getCdtrPrvOthCd() {
        return CdtrPrvOthCd;
    }

    public void setCdtrPrvOthCd(String cdtrPrvOthCd) {
        CdtrPrvOthCd = cdtrPrvOthCd;
    }

    public String getCdtrPrvOthIssr() {
        return CdtrPrvOthIssr;
    }

    public void setCdtrPrvOthIssr(String cdtrPrvOthIssr) {
        CdtrPrvOthIssr = cdtrPrvOthIssr;
    }

    public String getCdtrAcctIBAN() {
        return CdtrAcctIBAN;
    }

    public void setCdtrAcctIBAN(String cdtrAcctIBAN) {
        CdtrAcctIBAN = cdtrAcctIBAN;
    }

    public String getRmtInfUstrd() {
        return RmtInfUstrd;
    }

    public void setRmtInfUstrd(String rmtInfUstrd) {
        RmtInfUstrd = rmtInfUstrd;
    }

    public GrpHdr getGrpHdr() {
        return grpHdr;
    }

    public void setGrpHdr(GrpHdr grpHdr) {
        this.grpHdr = grpHdr;
    }

    public long getpIdPrKey() {
        return pIdPrKey;
    }

    public void setpIdPrKey(long pIdPrKey) {
        this.pIdPrKey = pIdPrKey;
    }

    public String getStatReadAndGenXml() {
        return statReadAndGenXml;
    }

    public void setStatReadAndGenXml(String statReadAndGenXml) {
        this.statReadAndGenXml = statReadAndGenXml;
    }
}
