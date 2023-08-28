package com.github.jmsmarcelo.alurahotel.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {
	private static String username;
	private static String password;
	
	public FactoryConnection(String user, String pass) {
		FactoryConnection.username = user;
		FactoryConnection.password = pass;
	}
	public FactoryConnection() {
	}
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/alura_hotel", username, password);
	}
}
