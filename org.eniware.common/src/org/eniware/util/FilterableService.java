/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.Map;

/**
 * API for a service that supports filtering properties, to support narrowing
 * down a possible collection of services to one or more specific services
 * matching the filter.
 * 
 * @author matt
 * @version 1.0
 */
public interface FilterableService {

	/**
	 * Get the current map of property filters, with keys representing property
	 * names and value their desired associated value.
	 * 
	 * @return filters
	 */
	Map<String, ?> getPropertyFilters();

	/**
	 * Set a property filter value.
	 * 
	 * @param key
	 *        the key to add
	 * @param value
	 *        the value
	 */
	void setPropertyFilter(String key, Object value);

	/**
	 * Remove a property filter value.
	 * 
	 * @param key
	 *        the key to remove
	 * @return the removed value, or <em>null</em> if no value was available
	 */
	Object removePropertyFilter(String key);

}
