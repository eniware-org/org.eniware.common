/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

/**
 * API for a standardized way of identifying services, to support configuring
 * links to specific instances of a service at runtime. Many managed services in
 * EniwareEdge allow any number of them to be deployed.
 * 
 * @version 1.0
 */
public interface Identifiable {

	/**
	 * Get a unique identifier for this service.
	 * 
	 * <p>
	 * This should be meaningful to the service implementation, and should be
	 * minimally unique between instances of the same service interface.
	 * </p>
	 * 
	 * @return unique identifier (should never be {@literal null})
	 */
	String getUid();

	/**
	 * Get a grouping identifier for this service.
	 * 
	 * <p>
	 * This should be meaningful to the service implementation.
	 * </p>
	 * 
	 * @return a group identifier, or {@literal null} if not part of any group
	 */
	String getGroupUid();

	/**
	 * Get a friendly display name for this service.
	 * 
	 * @return a display name
	 */
	String getDisplayName();

}
