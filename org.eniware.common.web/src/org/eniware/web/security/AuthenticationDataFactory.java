/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.security;

import java.io.IOException;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * Factory for creating {@code AuthenticationData} instances.
 * 
 * @version 1.0
 * @since 1.11
 */
public final class AuthenticationDataFactory {

	/**
	 * Obtain a {@link AuthenticationData} instance from a HTTP request.
	 * 
	 * @param request
	 *        The HTTP request.
	 * @return the authentication data, or {@code null} if no
	 *         {@code Authorization} header provided on the request or the
	 *         authorization scheme is not supported
	 * @throws IOException
	 *         if any IO error occurs
	 * @throws BadCredentialsException
	 *         if the authorization data is malformed in any way
	 */
	public static AuthenticationData authenticationDataForAuthorizationHeader(
			final SecurityHttpServletRequestWrapper request) throws IOException {
		final String header = request.getHeader("Authorization");

		AuthenticationScheme scheme = null;
		String headerData = null;
		if ( header != null ) {
			for ( AuthenticationScheme aScheme : AuthenticationScheme.values() ) {
				if ( header.startsWith(aScheme.getSchemeName()) ) {
					scheme = aScheme;
					headerData = header.substring(scheme.getSchemeName().length() + 1);
					break;
				}
			}
		}

		if ( scheme == null ) {
			return null;
		}

		AuthenticationData data;
		switch (scheme) {
			case V1:
				data = new AuthenticationDataV1(request, headerData);
				break;

			case V2:
				data = new AuthenticationDataV2(request, headerData);
				break;

			default:
				throw new BadCredentialsException("Authentication scheme not supported.");
		}

		return data;
	}

}
