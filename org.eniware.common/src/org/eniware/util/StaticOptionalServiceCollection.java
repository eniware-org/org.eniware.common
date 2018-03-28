/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.Collection;

/**
 * Implementation of {@link OptionalServiceCollection} using a static collection
 * of service instances.
 * 
 * <p>
 * This can be useful when the {@link OptionalServiceCollection} API is
 * required, but the service is known and available statically.
 * </p>
 * 
 * @version 1.0
 */
public class StaticOptionalServiceCollection<T> implements OptionalServiceCollection<T> {

	private final Collection<T> services;

	/**
	 * Construct with the static services.
	 * 
	 * @param services
	 *        the services
	 */
	public StaticOptionalServiceCollection(Collection<T> services) {
		super();
		this.services = services;
	}

	@Override
	public Iterable<T> services() {
		return services;
	}

}
