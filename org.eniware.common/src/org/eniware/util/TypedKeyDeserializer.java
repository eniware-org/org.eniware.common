/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.Module;

/**
 * {@KeyDeserializer} does not implement an interface, nor provide a default
 * "type" the deserializer supports. This API provides a way to configure them
 * on a {@link Module}.
 * 
 * @author matt
 * @version 1.0
 */
public interface TypedKeyDeserializer {

	/**
	 * The type to register the key deserializer with.
	 * 
	 * @return A type.
	 */
	Class<?> getKeyType();

	/**
	 * The key deserializer to register.
	 * 
	 * @return The key deserializer.
	 */
	KeyDeserializer getKeyDeserializer();

}
