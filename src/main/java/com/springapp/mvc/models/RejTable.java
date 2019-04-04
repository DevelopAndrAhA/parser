package com.springapp.mvc.models;

import javax.persistence.*;

@Entity(name = "RejTable")
@Table(name = "rejtable")
public class RejTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "r_id")
    private long r_id;
    private String done;
    private String rej_code;

    private long p_id;

    public long getR_id() {
        return r_id;
    }

    public void setR_id(long r_id) {
        this.r_id = r_id;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public long getP_id() {
        return p_id;
    }

    public void setP_id(long p_id) {
        this.p_id = p_id;
    }

    public String getRej_code() {
        return rej_code;
    }

    public void setRej_code(String rej_code) {
        this.rej_code = rej_code;
    }

    @Override
    public String toString() {
        return "RejTable{" +
                "r_id=" + r_id +
                ", done='" + done + '\'' +
                ", rej_code='" + rej_code + '\'' +
                '}';
    }
}
