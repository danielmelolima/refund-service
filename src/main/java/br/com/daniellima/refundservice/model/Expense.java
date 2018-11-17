package br.com.daniellima.refundservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Expense {

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private BigDecimal price;
	private String justification;
	private String type;
	private String couponPhoto;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getCouponPhoto() {
		return couponPhoto;
	}
	public void setCouponPhoto(String couponPhoto) {
		this.couponPhoto = couponPhoto;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Expense [date=" + date + ", price=" + price + ", justification=" + justification + ", type=" + type + "]";
	}
}