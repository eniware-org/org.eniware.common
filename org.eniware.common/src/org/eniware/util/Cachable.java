/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

/**
 * API for items that can be cached.
 *
 * @version 1.0
 */
public interface Cachable {

	/**
	 * Get a unique cache key that identifies this cachable item.
	 * 
	 * @return the cache key
	 */
	String getCacheKey();

	/**
	 * Get a suggested time-to-live, in seconds.
	 * 
	 * @return TTL in seconds, or {@literal null} if should use a default value
	 */
	Long getTtl();

	/**
	 * Get a suggested time-to-idle, in seconds.
	 * 
	 * @return TTI in seconds, or {@literal null} if should use a default value
	 */
	Long getTti();

}
