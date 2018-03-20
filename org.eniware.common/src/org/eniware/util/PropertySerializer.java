/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

/**
 * API for special handling of data serialization, in place of simply
 * calling {@code String.valueOf()}.
 * 
 * <p>This API is designed with implementations being thread-safe by
 * default, so a single implementation can be instantiated once and
 * used throughout an application.</p>
 * 
 * @author matt
 * @version $Revision$ $Date$
 */
public interface PropertySerializer {
	
	/**
	 * Serialize a property value.
	 * 
	 * <p>The {@code data} and {@code propertyName} parameters might not
	 * be used by different implementations, but allow for a single
	 * implementation to serialize more than one property of an object
	 * in different ways, if desired.</p>
	 * 
	 * @param data the source data being serialized, i.e. a JavaBean
	 * @param propertyName the name of the property being serialized
	 * @param propertyValue the value of the property to serialize
	 * @return the serialized value of the property
	 */
	Object serialize(Object data, String propertyName, Object propertyValue);
	
}