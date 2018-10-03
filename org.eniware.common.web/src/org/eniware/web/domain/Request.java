/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.domain;

import java.util.Map;

/**
 * A web request envelope object.
 * 
 * @version 1.1
 */
public class Request extends org.eniware.domain.Request {

	public Request(String username, String password, Map<String, Object> data) {
		super(username, password, data);
	}

}
