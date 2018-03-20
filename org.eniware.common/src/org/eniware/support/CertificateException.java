/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.support;

/**
 * Runtime exception to support {@link CertificateService}.
 * 
 * @author matt
 * @version 1.0
 */
public class CertificateException extends RuntimeException {

	private static final long serialVersionUID = 7918730540728511454L;

	/**
	 * Construct with a message and nested exception.
	 * 
	 * @param message
	 *        the message
	 * @param cause
	 *        the original cause
	 */
	public CertificateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construct with a message.
	 * 
	 * @param message
	 *        the message
	 */
	public CertificateException(String message) {
		super(message);
	}

	/**
	 * Construct with a nested exception.
	 * 
	 * @param cause
	 *        the original cause
	 */
	public CertificateException(Throwable cause) {
		super(cause);
	}

}
