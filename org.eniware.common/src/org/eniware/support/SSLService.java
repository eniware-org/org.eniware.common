/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.support;

import javax.net.ssl.SSLSocketFactory;

/**
 * API for SSL supporting functions.
 * 
 * @author matt
 * @version 1.0
 */
public interface SSLService {

	/**
	 * Get a {@code SSLSocketFactory} configured appropriately for the
	 * application. This method may return a singleton object.
	 * 
	 * @return the factory
	 */
	SSLSocketFactory getSSLSocketFactory();

}
