/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

/**
 * API for an "optional" service.
 * 
 * <p>
 * This API is like a simplified OSGi ServiceTracker. Calling the
 * {@link #service()} method will return the first available matching service,
 * or <em>null</em> if none available.
 * </p>
 * 
 * @param <T>
 * 
 * @version 1.0
 */
public interface OptionalService<T> {

	/**
	 * Get the configured service, or <em>null</em> if none available.
	 * 
	 * @return the service, or <em>null</em>
	 */
	T service();

}
