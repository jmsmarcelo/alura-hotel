package com.github.jmsmarcelo.alurahotel.jdbc;

import java.math.BigDecimal;
import java.sql.Date;

public class Reserve {
	private long id;
	private Date checkIn;
	private Date checkOut;
	private BigDecimal price;
	private String payMethod;
	
	public Reserve(Date checkIn, Date checkOut, BigDecimal price, String payMethod) {
		update(checkIn, checkOut, price, payMethod);
	}
	public void update(Date checkIn, Date checkOut, BigDecimal price, String payMethod) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.price = price;
		this.payMethod = payMethod;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
}
