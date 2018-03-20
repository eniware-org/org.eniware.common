/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

/**
 * A dynamic service that wishes to have internal resources freed when it is no
 * longer needed.
 * 
 * @author matt
 * @version 1.0
 * @since 1.36
 */
public interface CloseableService {

	/**
	 * Method to call when the service is released.
	 */
	public void closeService();

}
