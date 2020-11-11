package com.weddingbook.www.dto;

public class GoodsReservationDto {
	private int no;
	private String memberId;
	private String goodsName;
	private String goodsBusinessTel;
	private String reservationName;
	private String reservationDate;
	private String reservationHours;
	private String wTime;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsBusinessTel() {
		return goodsBusinessTel;
	}
	public void setGoodsBusinessTel(String goodsBusinessTel) {
		this.goodsBusinessTel = goodsBusinessTel;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReservationHours() {
		return reservationHours;
	}
	public void setReservationHours(String reservationHours) {
		this.reservationHours = reservationHours;
	}
	public String getwTime() {
		return wTime;
	}
	public void setwTime(String wTime) {
		this.wTime = wTime;
	}
}
