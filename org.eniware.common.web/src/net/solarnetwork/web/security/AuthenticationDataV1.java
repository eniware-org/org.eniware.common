/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package net.solarnetwork.web.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * Original HMAC-SHA1 authentication token scheme.
 * 
 * @author matt
 * @version 1.0
 * @since 1.11
 */
public class AuthenticationDataV1 extends AuthenticationData {

	private final String authTokenId;
	private final String signatureDigest;
	private final String signature;

	public AuthenticationDataV1(SecurityHttpServletRequestWrapper request, String headerValue)
			throws IOException {
		super(AuthenticationScheme.V1, request, headerValue);

		// the header must be in the form TOKEN-ID:HMAC-SHA1-SIGNATURE
		if ( AUTH_TOKEN_ID_LENGTH + 2 >= headerValue.length() ) {
			throw new BadCredentialsException("Invalid Authorization header value");
		}
		authTokenId = headerValue.substring(0, AUTH_TOKEN_ID_LENGTH);
		signatureDigest = headerValue.substring(AUTH_TOKEN_ID_LENGTH + 1);
		signature = computeSignatureData(request);

		validateContentDigest(request);
	}

	private String computeSignatureData(SecurityHttpServletRequestWrapper request) {
		StringBuilder buf = new StringBuilder(request.getMethod());
		buf.append("\n");
		buf.append(nullSafeHeaderValue(request, "Content-MD5")).append("\n");
		buf.append(nullSafeHeaderValue(request, "Content-Type")).append("\n");
		buf.append(httpDate(getDate())).append("\n");
		buf.append(request.getRequestURI());
		appendQueryParameters(request, buf);
		return buf.toString();
	}

	private void appendQueryParameters(HttpServletRequest request, StringBuilder buf) {
		Set<String> paramKeys = request.getParameterMap().keySet();
		if ( paramKeys.size() < 1 ) {
			return;
		}
		String[] keys = paramKeys.toArray(new String[paramKeys.size()]);
		Arrays.sort(keys);
		boolean first = true;
		for ( String key : keys ) {
			if ( first ) {
				buf.append('?');
				first = false;
			} else {
				buf.append('&');
			}
			buf.append(key).append('=').append(request.getParameter(key));
		}
	}

	@Override
	public String getAuthTokenId() {
		return authTokenId;
	}

	@Override
	public String getSignatureDigest() {
		return signatureDigest;
	}

	@Override
	public String getSignatureData() {
		return signature;
	}

	@Override
	public String computeSignatureDigest(String secretKey) {
		return Base64.encodeBase64String(computeMACDigest(secretKey, "HmacSHA1"));
	}

}
