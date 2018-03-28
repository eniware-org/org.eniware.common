/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

/**
 * API for node/network association details.
 * 
 * @version 1.1
 */
public interface NetworkAssociation extends NetworkIdentity {

	/**
	 * Get a confirmation key, generated on the network side.
	 * 
	 * @return confirmation key
	 */
	String getConfirmationKey();

	/**
	 * Get a security phrase, generated on the network side.
	 * 
	 * @return a security phrase
	 */
	String getSecurityPhrase();

	/**
	 * Get the username associated with this association.
	 * 
	 * @return the username
	 */
	String getUsername();

	/**
	 * Get a password to use for this association's keystore.
	 * 
	 * @return a keystore password
	 * @since 1.1
	 */
	String getKeystorePassword();

}
