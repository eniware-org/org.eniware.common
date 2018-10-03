/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import org.osgi.framework.ServiceReference;

/**
 * An OSGi service registration listener for any type of object, so they can be
 * used to dynamically configure and publish other OSGi services.
 * 
 * <p>
 * For example, this might be configured via Spring DM like this:
 * </p>
 * 
 * <pre>
 * &lt;osgi:reference id="fooService"
 * 		interface="org.eniware.edge.FooService" cardinality="0..1">
 * 		&lt;osgi:listener bind-method="onBind" unbind-method="onUnbind" ref="optionalFooService">
 * &lt;/osgi:list>
 * 
 * &lt;bean id="optionalFooService" 
 * 		class="org.eniware.edge.util.OptionalServiceTracker">
 * 		&lt;property name="service" ref="fooService"/>
 * &lt;/bean>
 * 
 * &lt;bean id="fooServiceConsumer" 
 * 		class="org.eniware.edge.FooServiceConsumer">
 * 		&lt;property name="service" ref="optionalFooService"/>
 * &lt;/bean>
 * </pre>
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>service</dt>
 * <dd>The managed service to track.</dd>
 * </dl>
 *
 * @version 1.1
 */
public class OptionalServiceTracker<T> implements OptionalService<T> {

	private T service;
	private boolean available;

	/**
	 * Get the tracked service, or <em>null</em> if no service currently
	 * available.
	 * 
	 * @return the service
	 */
	public T getService() {
		if ( available ) {
			return service;
		}
		return null;
	}

	@Override
	public T service() {
		return getService();
	}

	/**
	 * Call when a matching service is available.
	 * 
	 * @param ref
	 *        the service reference
	 */
	@SuppressWarnings("rawtypes")
	// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=402255
	public void onBind(ServiceReference ref) {
		available = true;
	}

	/**
	 * Call when a service is no longer available.
	 * 
	 * @param ref
	 *        the service reference
	 */
	@SuppressWarnings("rawtypes")
	public void onUnbind(ServiceReference ref) {
		available = false;
	}

	/**
	 * @param service
	 *        the service to set
	 */
	public void setService(T service) {
		this.service = service;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

}
