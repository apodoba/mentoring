package com.apodoba.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection implements com.apodoba.connection.Connection{
	
	private Connection connection;

	public MysqlConnection(Connection connection){
		this.connection = connection;
	}
	
	public Statement createStatement(){
		Statement statement = null;
		try {
			statement = this.connection.createStatement();
		} catch (SQLException e) {
		}
		return statement;
	}

	@Override
	public PreparedStatement createPreparedStatement(String sql) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
}
