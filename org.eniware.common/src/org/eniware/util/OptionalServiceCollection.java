/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

/**
 * API for a collection of "optional" services. This API is like a simplified
 * OSGi ServiceTracker for a collection of services. Calling the
 * {@link #service()} method will return the first available matching service,
 * or <em>null</em> if none available. </p>
 * 
 * @param <T>
 *        the tracked service type
 *
 * @version 1.0
 * @see OptionalService
 */
public interface OptionalServiceCollection<T> {

	/**
	 * Get the collection of configured services.
	 * 
	 * @return the services, never <em>null</em> but could be empty
	 */
	Iterable<T> services();

}
