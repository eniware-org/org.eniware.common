/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@link FactoryBean} where the exposed object is actually configured via
 * auto-wiring on {@link #setObject(Object)}. This is to facilitate exposing
 * services with known bean IDs, for situations where a bean might be injected
 * into the application context with an ID generated at runtime.
 * 
 * @author matt
 * @version 1.0
 */
public class AutowiredPropertyFactoryBean<T> implements FactoryBean<T> {

	private T object;
	private final Class<T> objectType;

	/**
	 * Construct with the object type.
	 * 
	 * @param objectType
	 *        The object type.
	 */
	public AutowiredPropertyFactoryBean(Class<T> objectType) {
		super();
		this.objectType = objectType;
	}

	@Override
	public Class<?> getObjectType() {
		return objectType;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public T getObject() throws Exception {
		return object;
	}

	@Autowired
	public void setObject(T object) {
		this.object = object;
	}

}
