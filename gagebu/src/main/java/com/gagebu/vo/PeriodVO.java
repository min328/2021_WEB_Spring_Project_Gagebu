package com.gagebu.vo;

public class PeriodVO {
	
	private String startPoint;
	private String endPoint;
	private int idx_num;
	
	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public int getIdx_num() {
		return idx_num;
	}
	public void setIdx_num(int idx_num) {
		this.idx_num = idx_num;
	}
	
	@Override
	public String toString() {
		return "PeriodVO [startPoint=" + startPoint + ", endPoint=" + endPoint + ", idx_num=" + idx_num + "]";
	}
}
