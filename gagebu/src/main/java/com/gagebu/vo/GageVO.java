package com.gagebu.vo;

public class GageVO {

	private String whenUsed;
	private String cate1;
	private int price;
	private String content;
	private String memo;
	private String place;
	private String cate2;
	private int idx_num;
	
	public String getWhenUsed() {
		return whenUsed;
	}
	public void setWhenUsed(String whenUsed) {
		this.whenUsed = whenUsed;
	}
	public String getCate1() {
		return cate1;
	}
	public void setCate1(String cate1) {
		this.cate1 = cate1;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getCate2() {
		return cate2;
	}
	public void setCate2(String cate2) {
		this.cate2 = cate2;
	}
	public int getIdx_num() {
		return idx_num;
	}
	public void setIdx_num(int idx_num) {
		this.idx_num = idx_num;
	}
	
	@Override
	public String toString() {
		return "GageVO [whenUsed=" + whenUsed + ", cate1=" + cate1 + ", price=" + price + ", content=" + content
				+ ", memo=" + memo + ", place=" + place + ", cate2=" + cate2 + ", idx_num=" + idx_num + "]";
	}
	
}
