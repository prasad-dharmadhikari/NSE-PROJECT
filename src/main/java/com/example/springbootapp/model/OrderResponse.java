package com.example.springbootapp.model;

public class OrderResponse {

    private String runId;
    private String statusURI;

    public OrderResponse(String runId, String statusURI) {
        super();
        this.runId = runId;
        this.statusURI = statusURI;
    }

    public OrderResponse() {
        super();
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getStatusURI() {
        return statusURI;
    }

    public void setStatusURI(String statusURI) {
        this.statusURI = statusURI;
    }

}
