/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.support;

import java.net.URI;
import java.util.Map;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * {@code HandshakeInterceptor} that populates properties from the connection as
 * message headers.
 * 
 * @version 1.0
 * @since 1.14
 */
public class RequestInfoHandshakeInterceptor implements HandshakeInterceptor {

	/**
	 * The attribute name for the {@link org.springframework.http.HttpMethod} of
	 * the HTTP request.
	 */
	private static final String REQUEST_METHOD_ATTR = "requestMethod";

	/** The attribute name for the {@link URI} of the HTTP request. */
	public static final String REQUEST_URI_ATTR = "requestUri";

	/**
	 * Populate request information as session attributes.
	 * 
	 * <p>
	 * The following attributes will be populated:
	 * </p>
	 * 
	 * <dl>
	 * <dt>requestUri</dt>
	 * <dd>The {@link URI} of the HTTP request.</dd>
	 * </dl>
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
			WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		attributes.putIfAbsent(REQUEST_URI_ATTR, request.getURI());
		attributes.putIfAbsent(REQUEST_METHOD_ATTR, request.getMethod());
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
			WebSocketHandler wsHandler, Exception exception) {
		// nothing to add
	}

}
