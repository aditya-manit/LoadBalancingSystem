package com.aditya.loadbalancer;

public class Request {
    private int reqID;
    private String reqStatement;

    public Request(int reqID, String reqStatement) {
        this.reqID = reqID;
        this.reqStatement = reqStatement;
    }

    public int getReqID() {
        return reqID;
    }

    public void setReqID(int reqID) {
        this.reqID = reqID;
    }

    public String getReqStatement() {
        return reqStatement;
    }

    public void setReqStatement(String reqStatement) {
        this.reqStatement = reqStatement;
    }
}
