/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Basic implementation of {@link ObjectMapperService}.
 *
 * @version 1.0
 */
public class SimpleObjectMapperService implements ObjectMapperService {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	/**
	 * Set the mapper to use.
	 * 
	 * @param objectMapper
	 *        the mapper to use
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
