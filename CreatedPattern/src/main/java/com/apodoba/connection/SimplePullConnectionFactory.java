package com.apodoba.connection;

import java.io.IOException;
import java.util.Properties;

public class SimplePullConnectionFactory implements PullConnectionFactory{

	@Override
	public ConnectionPull getConnectionPull() throws IOException {
		ConnectionPull connectionPull = null;
		
		Properties properties = new Properties();
		properties.load(ConnectionPull.class.getResourceAsStream("/connection.properties"));
		
		if("mysql".equals(properties.get("database"))){
			connectionPull = MySqlConnectionPull.getInstance();
		}

		return connectionPull;
	}

}
