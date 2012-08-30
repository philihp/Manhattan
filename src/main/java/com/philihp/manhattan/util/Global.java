package com.philihp.manhattan.util;

import org.codehaus.jackson.map.ObjectMapper;

public class Global {

	public static final ObjectMapper mapper = new ObjectMapper();
	
	public static final String ATTR_OAUTH_ACCESS_TOKEN = "ATTR_OAUTH_ACCESS_TOKEN";
	public static final String ATTR_OAUTH_REQUEST_TOKEN = "ATTR_OAUTH_REQUEST_TOKEN";
	
	private Global() {
	}
}
