package com.github.jmsmarcelo.alurahotel.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection conn;
	
	public DAO(Connection conn) throws SQLException {
		this.conn = conn;
	}
	public void insert(Reserve reserve) throws SQLException {
		try(PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO reservations(check_in, check_out, price, pay_method)"
				+ " VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			try(ResultSet rs = setPs(ps, reserve).getGeneratedKeys()) {
				while(rs.next())
					reserve.setId(rs.getLong(1));
			}
		}
	}
	public void insert(Guest guest) throws SQLException {
		try(PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO guests(first_name, last_name, birth_date, country, phone, reserve_id)"
				+ " VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			ps.setLong(6, guest.getReserveId());
			try(ResultSet rs = setPs(ps, guest).getGeneratedKeys()) {
				while(rs.next())
					guest.setId(rs.getLong(1));
			}
		}
	}
	public void update(Guest guest) throws SQLException {
		try(PreparedStatement ps = conn.prepareStatement(
				"UPDATE guests SET first_name = ?, last_name = ?, birth_date = ?, country = ?,"
				+ " phone = ? WHERE id = ?")) {
			ps.setLong(6, guest.getId());
			setPs(ps, guest);
		}
	}
	public void update(Reserve reserve, long reserveId) throws SQLException {
		try(PreparedStatement ps = conn.prepareStatement(
				"UPDATE reservations SET check_in = ?, check_out = ?, price = ?, pay_method = ?"
				+ " WHERE id = ?")) {
			ps.setLong(5, reserveId);
			setPs(ps, reserve);
		}
	}
	public PreparedStatement setPs(PreparedStatement ps, Guest guest) throws SQLException {
		ps.setString(1, guest.getFirstName());
		ps.setString(2, guest.getLastName());
		ps.setDate(3, guest.getBirthDate());
		ps.setString(4, guest.getCountry());
		ps.setString(5, guest.getPhone());
		ps.execute();
		return ps;
	}
	public PreparedStatement setPs(PreparedStatement ps, Reserve reserve) throws SQLException {
		ps.setDate(1, reserve.getCheckIn());
		ps.setDate(2, reserve.getCheckOut());
		ps.setBigDecimal(3, reserve.getPrice());
		ps.setString(4, reserve.getPayMethod());
		ps.execute();
		return ps;
	}
	public List<Guest> search(String match) throws SQLException {
		List<Guest> guests = new ArrayList<Guest>();
		String sql = "SELECT * FROM guests"
						+ " INNER JOIN reservations ON guests.reserve_id = reservations.id"
						+ " WHERE first_name REGEXP ?"
						+ " OR last_name REGEXP ?";
		try(PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, match + ".*");
			ps.setString(2, match + ".*");
			ps.execute();
			try(ResultSet rs = ps.getResultSet()) {
				while(rs.next()) {
					Guest guest = new Guest(
							rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getDate("birth_date"),
							rs.getString("country"),
							rs.getString("phone"),
							rs.getLong("reserve_id"));
					guest.setId(rs.getLong("guests.id"));
					guest.setReserve(
							rs.getDate("check_in"),
							rs.getDate("check_out"),
							rs.getBigDecimal("price"),
							rs.getString("pay_method"));
					guest.getReserve().setId(rs.getLong("reserve_id"));
					guests.add(guest);
				}
			}
		}
		return guests;
	}
	public void delete(Guest guest) throws SQLException {
		try(Statement s = conn.createStatement()) {
			s.execute("DELETE FROM guests WHERE id = " + guest.getId());
			s.execute("DELETE FROM reservations WHERE id = " + guest.getReserveId());
		}
	}
	public Connection getConn() {
		return this.conn;
	}
}
