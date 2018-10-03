/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import java.util.Map;

/**
 * A request envelope object.
 * 
 * @version 1.0
 */
public class Request {

	private final String username;
	private final String password;
	private final Map<String, Object> data;

	public Request(String username, String password, Map<String, Object> data) {
		super();
		this.username = username;
		this.password = password;
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Map<String, Object> getData() {
		return data;
	}

}
