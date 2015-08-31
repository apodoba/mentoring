package com.apodoba.connection;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnectionPull implements ConnectionPull {

	private Connection connection;

	private MySqlConnectionPull() {
		Properties properties = new Properties();
		try {
			properties.load(ConnectionPull.class
					.getResourceAsStream("/mysql.properties"));
			Class.forName(properties.getProperty("driver"));
			java.sql.Connection mysqlConnection = DriverManager.getConnection(
					properties.getProperty("url"),
					properties.getProperty("username"),
					properties.getProperty("password"));
			
			this.connection = new MysqlConnection(mysqlConnection);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static class MySqlConnectionPullHolder {
		private static final MySqlConnectionPull INSTANCE = new MySqlConnectionPull();
	}

	public static MySqlConnectionPull getInstance() {
		return MySqlConnectionPullHolder.INSTANCE;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}
}