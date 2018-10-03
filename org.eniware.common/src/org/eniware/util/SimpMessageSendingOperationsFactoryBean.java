/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import org.springframework.messaging.simp.SimpMessageSendingOperations;

/**
 * Factory bean to facilitate auto-wiring of a
 * {@link SimpMessageSendingOperations}.
 * 
 * With Spring's websocket support, the automatically registered
 * {@code SimpMessageSendingOperations} has a generated ID, and cannot be easily
 * exported as an OSGi service. This factory can overcome that, by auto-wiring
 * the object as a property, then exporting the bean with a known ID (or simply
 * as an OSGi service).
 * 
 * @version 1.0
 */
public class SimpMessageSendingOperationsFactoryBean
		extends AutowiredPropertyFactoryBean<SimpMessageSendingOperations> {

	public SimpMessageSendingOperationsFactoryBean() {
		super(SimpMessageSendingOperations.class);
	}

}
