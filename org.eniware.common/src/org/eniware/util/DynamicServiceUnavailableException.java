/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

/**
 * Exception thrown when a dynamic service is not available.
 * 
 * @author matt
 * @version 1.0
 */
public class DynamicServiceUnavailableException extends RuntimeException {

	private static final long serialVersionUID = -6082514393080966631L;

	public DynamicServiceUnavailableException() {
		super();
	}

	public DynamicServiceUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public DynamicServiceUnavailableException(String message) {
		super(message);
	}

	public DynamicServiceUnavailableException(Throwable cause) {
		super(cause);
	}

}
