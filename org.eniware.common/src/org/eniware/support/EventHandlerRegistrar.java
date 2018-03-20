/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.support;

import org.osgi.service.event.EventHandler;

/**
 * API for registering OSGi {@link EventHandler} instances with topics.
 * 
 * @author matt
 * @version 1.0
 * @since 1.36
 */
public interface EventHandlerRegistrar {

	/**
	 * Register a handler for a set of topics.
	 * 
	 * @param handler
	 *        the handler
	 * @param topics
	 *        the topics to regsiter
	 */
	void registerEventHandler(EventHandler handler, String... topics);

	/**
	 * Deregister a handler from all topics.
	 * 
	 * @param handler
	 *        the handler to deregister
	 */
	void deregisterEventHandler(EventHandler handler);

}
