/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package net.solarnetwork.web.security;

/**
 * Exception for security errors.
 * 
 * @author matt
 * @version 1.0
 * @since 1.11
 */
public class SecurityException extends RuntimeException {

	private static final long serialVersionUID = 4715317846353024503L;

	public SecurityException() {
		super();
	}

	public SecurityException(String msg, Throwable t) {
		super(msg, t);
	}

	public SecurityException(String msg) {
		super(msg);
	}

	public SecurityException(Throwable t) {
		super(t);
	}

}
