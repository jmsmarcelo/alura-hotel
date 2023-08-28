package com.github.jmsmarcelo.alurahotel.jdbc;

import java.math.BigDecimal;
import java.sql.Date;

public class Guest {
	private long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String country;
	private String phone;
	private long reserveId;
	private Reserve reserve;
	
	public Guest(String firstName, String lastName,
			Date birthDate, String country, String phone, long reserveId) {
		this.update(firstName, lastName, birthDate, country, phone);
		this.reserveId = reserveId;
	}
	public void update(String firstName, String lastName,
			Date birthDate, String country, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.country = country;
		this.phone = phone;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public String getCountry() {
		return country;
	}
	public String getPhone() {
		return phone;
	}
	public long getReserveId() {
		return reserveId;
	}
	public void setReserve(Date checkIn, Date checkOut, BigDecimal price, String payMethod) {
		this.reserve = new Reserve(checkIn, checkOut, price, payMethod);
	}
	public Reserve getReserve() {
		return reserve;
	}
}
