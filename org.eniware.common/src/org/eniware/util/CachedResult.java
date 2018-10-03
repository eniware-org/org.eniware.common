/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.concurrent.TimeUnit;

/**
 * A cached object holder.
 * 
 * @version 1.0
 * @param <T>
 *        The type of object that is cached.
 */
public class CachedResult<T> {

	private final long created;
	private final long expires;
	private final T result;

	/**
	 * Constructor. The current time will be used for the {@code created}
	 * property.
	 * 
	 * @param result
	 *        The result to cache.
	 * @param ttl
	 *        The time to live, after which the result should be considered
	 *        expired.
	 * @param unit
	 *        The time unit for the {@code expiration}.
	 */
	public CachedResult(T result, long ttl, TimeUnit unit) {
		this(result, System.currentTimeMillis(), ttl, unit);
	}

	/**
	 * Constructor.
	 * 
	 * @param result
	 *        The result to cache.
	 * @param created
	 *        The creation time to use for the result.
	 * @param ttl
	 *        The time to live, after which the result should be considered
	 *        expired.
	 * @param unit
	 *        The time unit for the {@code expiration}.
	 */
	public CachedResult(T result, long created, long ttl, TimeUnit unit) {
		super();
		this.result = result;
		this.created = created;
		this.expires = created + unit.toMillis(ttl);
	}

	/**
	 * Test if this result has not expired.
	 * 
	 * @return <em>true</em> if the result has not expired.
	 */
	public boolean isValid() {
		return (System.currentTimeMillis() < expires);
	}

	/**
	 * Get the system time this object was created.
	 * 
	 * @return The system time this object was created.
	 */
	public long getCreated() {
		return created;
	}

	/**
	 * Get the system time this object expires at.
	 * 
	 * @return The system time this object expires at.
	 */
	public long getExpires() {
		return expires;
	}

	/**
	 * Get the cached result object.
	 * 
	 * @return The result object.
	 */
	public T getResult() {
		return result;
	}

}
