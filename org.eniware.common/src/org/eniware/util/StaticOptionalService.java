/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link OptionalService} using a static service instance.
 * 
 * <p>
 * This can be useful when the {@link OptionalService} API is required, but the
 * service is known and available statically.
 * </p>
 * 
 * @version 1.1
 */
public class StaticOptionalService<T> implements OptionalService<T>, FilterableService {

	private final T service;
	private Map<String, Object> propertyFilters;

	public StaticOptionalService(T service) {
		super();
		this.service = service;
		propertyFilters = new HashMap<String, Object>(4);
	}

	@Override
	public T service() {
		return service;
	}

	@Override
	public Map<String, ?> getPropertyFilters() {
		return propertyFilters;
	}

	@Override
	public void setPropertyFilter(String key, Object value) {
		Map<String, Object> filters = propertyFilters;
		if ( filters == null ) {
			filters = new HashMap<String, Object>(4);
			propertyFilters = filters;
		}
		filters.put(key, value);
	}

	@Override
	public Object removePropertyFilter(String key) {
		Map<String, Object> filters = propertyFilters;
		Object result = null;
		if ( filters != null ) {
			result = filters.remove(key);
		}
		return result;
	}

}
