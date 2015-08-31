package com.apodoba.process;

import java.io.IOException;

import com.apodoba.connection.Connection;
import com.apodoba.connection.ConnectionPull;
import com.apodoba.connection.PullConnectionFactory;
import com.apodoba.connection.SimplePullConnectionFactory;

public class ProcessConnection {

	public static Connection getConnection(){
		try {
			PullConnectionFactory pullConnectionFactory = new SimplePullConnectionFactory();
			ConnectionPull connectionPull;
			connectionPull = pullConnectionFactory.getConnectionPull();
			return connectionPull.getConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
