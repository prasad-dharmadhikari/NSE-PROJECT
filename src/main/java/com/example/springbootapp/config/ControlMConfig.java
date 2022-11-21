package com.example.springbootapp.config;

public class ControlMConfig {

    public String sessionLoginApi;

    public String orderApi;

    public String jobStatusApi;

    public String serverName;

    public int port;

    public String getSessionLoginApi() {
        return sessionLoginApi;
    }

    public void setSessionLoginApi(String sessionLoginApi) {
        this.sessionLoginApi = sessionLoginApi;
    }

    public String getOrderApi() {
        return orderApi;
    }

    public void setOrderApi(String orderApi) {
        this.orderApi = orderApi;
    }

    public String getJobStatusApi() {
        return jobStatusApi;
    }

    public void setJobStatusApi(String jobStatusApi) {
        this.jobStatusApi = jobStatusApi;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ControlMConfig [sessionLoginApi=" + sessionLoginApi + ", orderApi=" + orderApi + ", jobStatusApi="
                + jobStatusApi + ", serverName=" + serverName + ", port=" + port + "]";
    }
    
    

}
