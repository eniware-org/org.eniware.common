/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.security;

/**
 * Global web constants for the central EniwareNetwork applications.
 * 
 * @version 1.0
 * @since 1.11
 */
public final class WebConstants {

	/** The prefix used for all custom HTTP headers. */
	public static final String HEADER_PREFIX = "X-SN-";

	/** An error message header. */
	public static final String HEADER_ERROR_MESSAGE = HEADER_PREFIX + "ErrorMessage";

	/**
	 * A date header, e.g. for clients that don't have direct access to standard
	 * HTTP Date header.
	 */
	public static final String HEADER_DATE = HEADER_PREFIX + "Date";

	/**
	 * The hex-encoded SHA256 value of an empty string.
	 * 
	 * @since 1.1
	 */
	public static final String EMPTY_STRING_SHA256_HEX = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

	// can't construct me
	private WebConstants() {
		super();
	}

}
