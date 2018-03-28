/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A tracker of {@link CloseableService} instances, so they have their resources
 * freed when removed from the system runtime.
 * 
 * <p>
 * For example, this class might be configured via OSGi Blueprint like this:
 * </p>
 * 
 * <pre>
 * &lt;reference-list interface="net.solarnetwork.util.CloseableService" availability="optional">
 * 		&lt;reference-listener unbind-method="onReleased">
 * 			&lt;bean class="net.solarnetwork.util.CloseableServiceTracker"/>
 * 		&lt;/reference-listener>
 * &lt;/reference-list>
 * </pre>
 * 
 * @version 1.0
 * @since 1.36
 */
public class CloseableServiceTracker {

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Call when an {@link CloseableService} is no longer available.
	 * 
	 * @param ref
	 *        the service reference
	 */
	public void onReleased(CloseableService service) {
		if ( service == null ) {
			return;
		}
		log.debug("Releasing CloseableService {}", service);
		try {
			service.closeService();
		} catch ( RuntimeException e ) {
			log.error("Error closing CloseableService {}", service, e);
		}
	}

}
