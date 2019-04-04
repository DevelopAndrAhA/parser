package com.springapp.mvc.models4testokhttp;

import java.util.List;

public class MyOrder {
    private int id;
    private String date;
    private String date_hms;
    private String status;
    private String sum;
    private boolean selected;
    private boolean hide;
    private boolean removeorder;
    private Courier2 courier2;
    private List<Products> productses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_hms() {
        return date_hms;
    }

    public void setDate_hms(String date_hms) {
        this.date_hms = date_hms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean isRemoveorder() {
        return removeorder;
    }

    public void setRemoveorder(boolean removeorder) {
        this.removeorder = removeorder;
    }

    public Courier2 getCourier2() {
        return courier2;
    }

    public void setCourier2(Courier2 courier2) {
        this.courier2 = courier2;
    }

    public List<Products> getProductses() {
        return productses;
    }

    public void setProductses(List<Products> productses) {
        this.productses = productses;
    }
}
