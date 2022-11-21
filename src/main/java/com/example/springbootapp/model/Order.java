package com.example.springbootapp.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("folder")
    @Expose
    private String folder;
    @SerializedName("application")
    @Expose
    private String application;
    @SerializedName("subapplication")
    @Expose
    private String subapplication;
    @SerializedName("ctm")
    @Expose
    private String ctm;
    @SerializedName("jobs")
    @Expose
    private String jobs;
    @SerializedName("ignoreCriteria")
    @Expose
    private String ignoreCriteria;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("hold")
    @Expose
    private String hold;
    @SerializedName("orderIntoFolder")
    @Expose
    private String orderIntoFolder;
    @SerializedName("waitForOrderDate")
    @Expose
    private String waitForOrderDate;
    @SerializedName("variables")
    @Expose
    private ArrayList<HashMap<String,String>> variableOne;

    public Order(String folder, String application, String subapplication, String ctm, String jobs,
            String ignoreCriteria, String orderDate, String hold, String orderIntoFolder, String waitForOrderDate,
            ArrayList<HashMap<String,String>> variables) {
        super();
        this.folder = folder;
        this.application = application;
        this.subapplication = subapplication;
        this.ctm = ctm;
        this.jobs = jobs;
        this.ignoreCriteria = ignoreCriteria;
        this.orderDate = orderDate;
        this.hold = hold;
        this.orderIntoFolder = orderIntoFolder;
        this.waitForOrderDate = waitForOrderDate;
        this.variableOne = variables;
    }

    public Order() {
        super();
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getSubapplication() {
        return subapplication;
    }

    public void setSubapplication(String subapplication) {
        this.subapplication = subapplication;
    }

    public String getCtm() {
        return ctm;
    }

    public void setCtm(String ctm) {
        this.ctm = ctm;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getIgnoreCriteria() {
        return ignoreCriteria;
    }

    public void setIgnoreCriteria(String ignoreCriteria) {
        this.ignoreCriteria = ignoreCriteria;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold;
    }

    public String getOrderIntoFolder() {
        return orderIntoFolder;
    }

    public void setOrderIntoFolder(String orderIntoFolder) {
        this.orderIntoFolder = orderIntoFolder;
    }

    public String getWaitForOrderDate() {
        return waitForOrderDate;
    }

    public void setWaitForOrderDate(String waitForOrderDate) {
        this.waitForOrderDate = waitForOrderDate;
    }

    public ArrayList<HashMap<String,String>> getVariables() {
        return variableOne;
    }

    public void setVariables(ArrayList<HashMap<String, String>> variables) {
        this.variableOne = variables;
    }

    @Override
    public String toString() {
        return "Order [folder=" + folder + ", application=" + application + ", subapplication=" + subapplication
                + ", ctm=" + ctm + ", jobs=" + jobs + ", ignoreCriteria=" + ignoreCriteria + ", orderDate=" + orderDate
                + ", hold=" + hold + ", orderIntoFolder=" + orderIntoFolder + ", waitForOrderDate=" + waitForOrderDate
                + ", variables=" + variableOne + "]";
    }
    
    

    
}