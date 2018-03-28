/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import java.util.Map;

/**
 * Information that identifies the central SolarNet network.
 * 
 * @version 1.1
 */
public interface NetworkIdentity {

	/**
	 * The {@link #getNetworkServiceURLs()} key for the SolarUser service.
	 * 
	 * @since 1.1
	 */
	final String SOLARUSER_NETWORK_SERVICE_KEY = "solaruser";

	/**
	 * The {@link #getNetworkServiceURLs()} key for the SolarQuery service.
	 * 
	 * @since 1.1
	 */
	final String SOLARQUERY_NETWORK_SERVICE_KEY = "solarquery";

	/**
	 * Get the service host name.
	 * 
	 * @return map of service host name
	 */
	String getHost();

	/**
	 * The host port to use.
	 * 
	 * @return the port
	 */
	Integer getPort();

	/**
	 * Flag indicating if TLS must be used.
	 * 
	 * @return boolean
	 */
	boolean isForceTLS();

	/**
	 * Get a universally unique key that identifies this service.
	 * 
	 * @return unique key
	 */
	String getIdentityKey();

	/**
	 * Get the terms of service for this service.
	 * 
	 * @return the terms of service
	 */
	String getTermsOfService();

	/**
	 * Get a mapping of pre-defined network service URLs, to be used by clients
	 * to provide links to the SolarNetwork they are associated with.
	 * 
	 * @return a mapping of keys to string URLs
	 * @since 1.1
	 */
	Map<String, String> getNetworkServiceURLs();

}
