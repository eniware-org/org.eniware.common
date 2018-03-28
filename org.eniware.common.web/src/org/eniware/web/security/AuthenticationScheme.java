/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.security;

/**
 * Authentication scheme constants.
 * 
 * @version 1.0
 * @since 1.11
 */
public enum AuthenticationScheme {

	/** The original scheme. */
	V1("SolarNetworkWS"),

	/** The version 2 scheme. */
	V2("SNWS2");

	private String schemeName;

	private AuthenticationScheme(String schemeName) {
		this.schemeName = schemeName;
	}

	/**
	 * Get the scheme name.
	 * 
	 * @return the scheme name
	 */
	public String getSchemeName() {
		return schemeName;
	}
}
