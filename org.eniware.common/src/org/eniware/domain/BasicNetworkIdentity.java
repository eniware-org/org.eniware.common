/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Basic implementation of {@link NetworkIdentity}.
 * 
 * @version 1.1
 */
public class BasicNetworkIdentity implements NetworkIdentity, Serializable {

	private static final long serialVersionUID = 1734756599885882478L;

	private String identityKey;
	private String termsOfService;
	private String host;
	private Integer port;
	private boolean forceTLS;
	private Map<String, String> networkServiceURLs;

	/**
	 * Default constructor.
	 */
	public BasicNetworkIdentity() {
		super();
	}

	/**
	 * Construct with an identity key.
	 * 
	 * @param identityKey
	 *        the identity key
	 * @param termsOfService
	 *        the TOS
	 * @param serviceURLs
	 *        the service URLs
	 */
	public BasicNetworkIdentity(String identityKey, String termsOfService, String host, Integer port,
			Boolean forceTLS) {
		super();
		setIdentityKey(identityKey);
		setTermsOfService(termsOfService);
		setHost(host);
		setPort(port);
		setForceTLS(forceTLS);
	}

	@Override
	public String getIdentityKey() {
		return identityKey;
	}

	@Override
	public String getTermsOfService() {
		return termsOfService;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public Integer getPort() {
		return port;
	}

	@Override
	public boolean isForceTLS() {
		return forceTLS;
	}

	public void setIdentityKey(String identityKey) {
		this.identityKey = identityKey;
	}

	public void setTermsOfService(String termsOfService) {
		this.termsOfService = termsOfService;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setForceTLS(boolean forceTLS) {
		this.forceTLS = forceTLS;
	}

	@Override
	public Map<String, String> getNetworkServiceURLs() {
		return networkServiceURLs;
	}

	public void setNetworkServiceURLs(Map<String, String> networkServiceURLs) {
		this.networkServiceURLs = networkServiceURLs;
	}

	private void putServiceURL(String key, String url) {
		if ( key == null ) {
			return;
		}
		if ( url == null ) {
			if ( networkServiceURLs == null ) {
				return;
			}
			networkServiceURLs.remove(key);
		} else {
			Map<String, String> map = networkServiceURLs;
			if ( map == null ) {
				map = new LinkedHashMap<String, String>(2);
				networkServiceURLs = map;
			}
			map.put(key, url);
		}
	}

	private String getServiceURL(String key) {
		return (networkServiceURLs == null ? null : networkServiceURLs.get(key));
	}

	/**
	 * Put a {@code networkServiceURLs} value for the
	 * {@link NetworkIdentity#ENIWAREUSER_NETWORK_SERVICE_KEY} key.
	 * 
	 * @param url
	 *        The URL to set, or <em>null</em> to remove.
	 */
	public void setEniwareUserServiceURL(String url) {
		putServiceURL(ENIWAREUSER_NETWORK_SERVICE_KEY, url);
	}

	/**
	 * Get the {@link NetworkIdentity#ENIWAREUSER_NETWORK_SERVICE_KEY}
	 * {@code networkServiceURLs} value.
	 */
	public String getEniwareUserServiceURL() {
		return getServiceURL(ENIWAREUSER_NETWORK_SERVICE_KEY);
	}

	/**
	 * Put a {@code networkServiceURLs} value for the
	 * {@link NetworkIdentity#ENIWAREQUERY_NETWORK_SERVICE_KEY} key.
	 * 
	 * @param url
	 *        The URL to set, or <em>null</em> to remove.
	 */
	public void setEniwareQueryServiceURL(String url) {
		putServiceURL(ENIWAREQUERY_NETWORK_SERVICE_KEY, url);
	}

	/**
	 * Get the {@link NetworkIdentity#ENIWAREQUERY_NETWORK_SERVICE_KEY}
	 * {@code networkServiceURLs} value.
	 */
	public String getEniwareQueryServiceURL() {
		return getServiceURL(ENIWAREQUERY_NETWORK_SERVICE_KEY);
	}

}
