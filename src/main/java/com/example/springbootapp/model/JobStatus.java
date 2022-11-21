package com.example.springbootapp.model;

public class JobStatus {

    private String runId;
    private String statusURI;
    private Integer numericRunId;

    public String getAlphanumericRunId() {
        return runId;
    }

    public void setAlphanumericRunId(String runId) {
        this.runId = runId;
    }

    public String getStatusURI() {
        return statusURI;
    }

    public void setStatusURI(String statusURI) {
        this.statusURI = statusURI;
    }

    public Integer getNumericRunId() {
        return numericRunId;
    }

    public void setNumericRunId(Integer numericRunId) {
        this.numericRunId = numericRunId;
    }

    @Override
    public String toString() {
        return "JobStatus [runId=" + runId + ", statusURI=" + statusURI + ", numericRunId=" + numericRunId + "]";
    }


}
