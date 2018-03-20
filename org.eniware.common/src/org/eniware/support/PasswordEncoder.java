/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.support;

/**
 * A password encoder API.
 * 
 * <p>
 * Modeled after the Spring Security <code>PasswordEncoder</code> API.
 * </p>
 * 
 * @author matt
 * @version 1.0
 * @since 1.36
 */
public interface PasswordEncoder {

	/**
	 * Return <em>true</em> if a password is already encrypted or not.
	 * 
	 * <p>
	 * This assumes the password has been encoded in such a way that it can be
	 * recognized as an ecrypted password, for example with a
	 * <code>{SSHA}</code> prefix.
	 * </p>
	 * 
	 * @param rawPassword
	 *        the password
	 * @return boolean
	 */
	boolean isPasswordEncrypted(CharSequence password);

	/**
	 * Encode a raw password.
	 * 
	 * @return the encrypted password string
	 */
	String encode(CharSequence rawPassword);

	/**
	 * Verify the encoded password obtained from storage matches the submitted
	 * raw password after it too is encoded. Returns true if the passwords
	 * match, false if they do not. The stored password itself is never decoded.
	 * 
	 * @param rawPassword
	 *        the raw password to encode and match
	 * @param encodedPassword
	 *        the encoded password from storage to compare with
	 * @return true if the raw password, after encoding, matches the encoded
	 *         password from storage
	 */
	boolean matches(CharSequence rawPassword, String encodedPassword);

}
