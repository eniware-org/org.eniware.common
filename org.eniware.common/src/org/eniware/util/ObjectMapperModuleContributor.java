/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Class for adding a {@link Module} to an existing {@link ObjectMapper}.
 * 
 * This is useful for adding serializers/deserializers to a pre-existing mapper.
 *
 * @version 1.0
 */
public class ObjectMapperModuleContributor extends ObjectMapperModuleSupport {

	/**
	 * Setup the {@link Module} and register it with the configured
	 * {@link ObjectMapper}.
	 */
	public void setupModule() {
		SimpleModule module = new SimpleModule(getModuleName(), getModuleVersion());
		if ( getSerializers() != null ) {
			for ( JsonSerializer<?> serializer : getSerializers() ) {
				module.addSerializer(serializer);
			}
		}
		if ( getDeserializers() != null ) {
			for ( JsonDeserializer<?> deserializer : getDeserializers() ) {
				registerDeserializer(module, deserializer);
			}
		}
		if ( getKeyDeserializers() != null ) {
			for ( TypedKeyDeserializer deserializer : getKeyDeserializers() ) {
				module.addKeyDeserializer(deserializer.getClass(), deserializer.getKeyDeserializer());
			}
		}
		if ( getKeySerializers() != null ) {
			for ( JsonSerializer<?> serializer : getKeySerializers() ) {
				registerKeySerializer(module, serializer);
			}
		}
		getObjectMapper().registerModule(module);
	}

	/**
	 * Set the {@code ObjectMapper} via a {@link ObjectMapperService}.
	 * 
	 * @param service
	 *        The service to call {@link #getObjectMapper()} on.
	 */
	public void setObjectMapperService(ObjectMapperService service) {
		setObjectMapper(service.getObjectMapper());
	}

}
