package com.example.springbootapp.dto;

import java.util.ArrayList;

public class JobStatusResponse {
	public ArrayList<Status> statuses;
	public int startIndex;
	public int itemsPerPage;
	public int total;

	public ArrayList<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(ArrayList<Status> statuses) {
		this.statuses = statuses;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "JobStatusResponse [statuses=" + statuses + ", startIndex=" + startIndex + ", itemsPerPage="
				+ itemsPerPage + ", total=" + total + "]";
	}

}
