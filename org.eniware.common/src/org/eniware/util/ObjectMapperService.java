/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * API for exposing {@link ObjectMapper} as a service.
 *
 * @version 1.0
 */
public interface ObjectMapperService {

	/**
	 * Get the {@link ObjectMapper} instance.
	 * 
	 * @return the mapper
	 */
	ObjectMapper getObjectMapper();

}
