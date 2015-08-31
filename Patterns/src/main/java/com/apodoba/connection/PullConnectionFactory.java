package com.apodoba.connection;

import java.io.IOException;

public interface PullConnectionFactory {
	
	ConnectionPull getConnectionPull() throws IOException;

}
