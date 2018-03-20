/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package net.solarnetwork.web.support;

import java.io.IOException;
import java.io.Writer;

/**
 * API for augmenting a view response.
 * 
 * @author matt
 * @version $Revision$ $Date$
 */
public interface ViewResponseAugmentor {

	/**
	 * Augment the response in some way.
	 * 
	 * @param out an output writer
	 * @throws IOException if any IO error occurs
	 */
	void augmentResponse(Writer out) throws IOException;
	
}
