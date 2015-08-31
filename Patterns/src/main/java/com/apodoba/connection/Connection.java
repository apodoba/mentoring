package com.apodoba.connection;

import java.sql.PreparedStatement;
import java.sql.Statement;

public interface Connection {
	
	public Statement createStatement();
	
	public PreparedStatement createPreparedStatement(String sql);

}
