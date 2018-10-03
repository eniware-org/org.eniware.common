/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Serialize objects to a JSON string value.
 *
 * @version 1.0
 */
public class JSONStringPropertySerializer implements PropertySerializer {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private ObjectMapper objectMapper;

	@Override
	public Object serialize(Object data, String propertyName, Object propertyValue) {
		try {
			return objectMapper.writeValueAsString(propertyValue);
		} catch ( Exception e ) {
			log.error("Exception marshalling {} to JSON", propertyValue, e);
		}
		return null;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
