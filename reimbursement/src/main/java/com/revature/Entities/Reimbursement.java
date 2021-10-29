package com.revature.Entities;

public class Reimbursement {
    private int rid;
    private int uid;
    private double amount;
    private String date;
    private int status;
    private String comment;

    public Reimbursement() {
        super();
    }

    public Reimbursement(int rid, int uid, double amount, String date, int status, String comment) {
        this.rid = rid;
        this.uid = uid;
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.comment = comment;
    }

    public Reimbursement(int uid, double amount, String date, int status, String comment) {
        this.uid = uid;
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.comment = comment;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Reimbursement [amount=" + amount + ", comment=" + comment + ", date=" + date + ", rid=" + rid
                + ", status=" + status + ", uid=" + uid + "]";
    }

}
