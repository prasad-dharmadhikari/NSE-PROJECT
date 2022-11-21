package com.example.springbootapp.dto;

public class Status {
	public String jobId;
	public String folderId;
	public int numberOfRuns;
	public String name;
	public String type;
	public String status;
	public boolean held;
	public boolean deleted;
	public String startTime;
	public String endTime;
	public String outputURI;
	public String logURI;
	public String folder;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public int getNumberOfRuns() {
		return numberOfRuns;
	}

	public void setNumberOfRuns(int numberOfRuns) {
		this.numberOfRuns = numberOfRuns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isHeld() {
		return held;
	}

	public void setHeld(boolean held) {
		this.held = held;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOutputURI() {
		return outputURI;
	}

	public void setOutputURI(String outputURI) {
		this.outputURI = outputURI;
	}

	public String getLogURI() {
		return logURI;
	}

	public void setLogURI(String logURI) {
		this.logURI = logURI;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Override
	public String toString() {
		return "Status [jobId=" + jobId + ", folderId=" + folderId + ", numberOfRuns=" + numberOfRuns + ", name=" + name
				+ ", type=" + type + ", status=" + status + ", held=" + held + ", deleted=" + deleted + ", startTime="
				+ startTime + ", endTime=" + endTime + ", outputURI=" + outputURI + ", logURI=" + logURI + ", folder="
				+ folder + "]";
	}

}
