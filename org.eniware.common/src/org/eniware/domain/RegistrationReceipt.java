/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import java.io.Serializable;

/**
 * A receipt for registration.
 *
 * @version 1.1
 */
public interface RegistrationReceipt extends Serializable {

	/**
	 * Get the username that has been registered.
	 * 
	 * @return the email address
	 */
	String getUsername();

	/**
	 * Get the uesrname that has been registered, encoded as a URL component.
	 * 
	 * @return the uesrname, URL encoded
	 * @since 1.1
	 */
	String getUsernameURLComponent();

	/**
	 * Get the confirmation code required to activate the registered user.
	 * 
	 * @return confirmation code
	 */
	String getConfirmationCode();

}
