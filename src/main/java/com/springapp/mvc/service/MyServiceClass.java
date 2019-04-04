package com.springapp.mvc.service;


import com.springapp.mvc.models.GrpHdr;
import com.springapp.mvc.models.PmtInf;
import com.springapp.mvc.models.RejTable;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class MyServiceClass {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory session;
    public boolean save(GrpHdr grpHdr) {
        session.getCurrentSession().save(grpHdr);
        return true;
    }

    public boolean save(RejTable rejTable) {
        session.getCurrentSession().save(rejTable);
        return true;
    }
    public boolean save(List<PmtInf> pmtInflist){
        for(int i=0;i<pmtInflist.size();i++){
            session.getCurrentSession().save(pmtInflist.get(i));
        }
        return true;
    }


    public List<PmtInf> getlistPmtInf() {
        Criteria criteria = session.getCurrentSession().createCriteria(PmtInf.class);
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(100);
        List<PmtInf> list  = criteria.list();
        return list;
    }
    public List<RejTable> getRejTables() {
        Criteria criteria = session.getCurrentSession().createCriteria(RejTable.class);
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(100);
        List<RejTable> list  = criteria.list();
        return list;
    }
    public RejTable getRejTable(long r_id){
        Criteria criteria = session.getCurrentSession().createCriteria(RejTable.class);
        criteria.add(Restrictions.eq("r_id",r_id));
        criteria.createAlias("pmtInf", "p");
        criteria.createCriteria("p.grpHdr");
        RejTable rejTable = (RejTable) criteria.uniqueResult();
        return rejTable;
    }


    public List<RejTable> generateXmlFromRejtable(){
        Criteria criteria = session.getCurrentSession().createCriteria(RejTable.class);
        criteria.add(Restrictions.isNull("done"));
        List<RejTable> rejTables = criteria.list();
        return rejTables;
    }
    public GrpHdr getGrpHdrById(long id){
        GrpHdr grpHdr = session.getCurrentSession().get(GrpHdr.class, id);
        return grpHdr;
    }
    public PmtInf getPmtInfById(long id){
        PmtInf  pmtInf= session.getCurrentSession().get(PmtInf.class,id);
        return pmtInf;
    }
    public void removeLastData(){
        Criteria criteria = session.getCurrentSession().createCriteria(GrpHdr.class);
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(1);
        GrpHdr grpHdr = (GrpHdr) criteria.list().get(0);
        session.getCurrentSession().remove(grpHdr);
        session.getCurrentSession().createSQLQuery("DELETE FROM pmtinf WHERE grphdr_id="+grpHdr.getGrpHdr_id());
    }
    public GrpHdr getLastData(){
        Criteria criteria = session.getCurrentSession().createCriteria(GrpHdr.class);
        criteria.addOrder(Order.desc("grpHdr_id"));
        criteria.setMaxResults(1);
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            GrpHdr grpHdr = (GrpHdr) l.get(0);
            return grpHdr;
        }
        return null;
    }
    public List<RejTable> getLastRejTables(){
        String sql = "SELECT * FROM rejtable WHERE done IS NULL OR NOT done='Y'";
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql).addEntity(RejTable.class);
        List<RejTable> rejTables = sqlQuery.list();
        return rejTables;
    }
    public int getSizePmtInf(){
        Criteria criteria = session.getCurrentSession().createCriteria(GrpHdr.class);
        criteria.setMaxResults(1);
        criteria.addOrder(Order.desc("id"));
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            GrpHdr grpHdr = (GrpHdr) l.get(0);
            String sql = "SELECT pIdPrKey FROM pmtinf WHERE grphdr_id="+grpHdr.getGrpHdr_id()+" AND statReadAndGenXml IS NULL";
            SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql);
            List list = sqlQuery.list();
            return list.size();
        }
        return 0;
    }
    public List<PmtInf> getAcceptedPmtInfs(long grpHdrId,List<Long> pmtInfsId){
        try {
            Criteria criteria = session.getCurrentSession().createCriteria(PmtInf.class);
            criteria.add(Restrictions.eq("grpHdr.id", grpHdrId));
            criteria.add(Restrictions.not(Restrictions.in("pIdPrKey",pmtInfsId)));
            List<PmtInf> list = criteria.list();
            if(list!=null&&list.size()!=0){
                return list;
            }
        }catch (SQLGrammarException exception){exception.printStackTrace();}
        List<PmtInf> list = new ArrayList<>();
        return list;
    }
    public void updateRejTableStatuses(){
        String sql = "UPDATE rejtable SET done ='Y' WHERE done IS NULL";
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
    }
    public void updatePmtInfStatuses(){
        String sql = "UPDATE pmtinf SET statReadAndGenXml ='Y' WHERE statReadAndGenXml IS NULL";
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
    }
    public PmtInf getLastPmtInf(){
        Criteria criteria = session.getCurrentSession().createCriteria(PmtInf.class);
        criteria.addOrder(Order.desc("pIdPrKey"));
        criteria.setMaxResults(1);
        List l = criteria.list();
        if(l!=null&&l.size()!=0){
            PmtInf pmtInf = (PmtInf) l.get(0);
            return pmtInf;
        }
        return null;
    }
    public List<PmtInf> getAcceptedPmtInfs(long grpHdrId){
        try {
            Criteria criteria = session.getCurrentSession().createCriteria(PmtInf.class);
            criteria.add(Restrictions.eq("grpHdr.id", grpHdrId));
            criteria.add(Restrictions.isNull("statReadAndGenXml"));
            criteria.addOrder(Order.desc("pIdPrKey"));
            List<PmtInf> list = criteria.list();
            if(list!=null&&list.size()!=0){
                return list;
            }
        }catch (SQLGrammarException exception){exception.printStackTrace();}
        List<PmtInf> list = new ArrayList<>();
        return list;
    }
}


















