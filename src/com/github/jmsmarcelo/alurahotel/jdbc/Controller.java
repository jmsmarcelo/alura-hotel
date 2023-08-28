package com.github.jmsmarcelo.alurahotel.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Controller {
	private DAO dao;
	private Reserve reserve;
	private Guest guest;

	public Controller() throws SQLException{
		dao = new DAO(new FactoryConnection().getConnection());
	}
	public void setReserve(
			Date checkIn, Date checkOut, BigDecimal price, String payMethod) throws SQLException {
		reserve = new Reserve( checkIn, checkOut, price, payMethod);
		dao.insert(reserve);
	}
	public void setGuest(
			String firstName, String lastName, Date birthDate, String country,
			String phone) throws NumberFormatException, SQLException {
		guest = new Guest(firstName, lastName, birthDate, country, phone, reserve.getId());
		dao.insert(guest);
	}
	public Connection getConn() {
		return this.dao.getConn();
	}
	public List<Guest> search(String match) throws SQLException {
		return dao.search(match);
	}
	public void update(List<Guest> guests, List<Reserve> reserve) throws SQLException {
		for(int i = 0; i < guests.size(); i++) {
			dao.update(guests.get(i));
			dao.update(reserve.get(i), guests.get(i).getReserveId());
		}
	}
	public void delete(Guest guest) throws SQLException {
		dao.delete(guest);
	}
	public String getReserveId() throws SQLException {
		return String.valueOf(reserve.getId());
	}
}
