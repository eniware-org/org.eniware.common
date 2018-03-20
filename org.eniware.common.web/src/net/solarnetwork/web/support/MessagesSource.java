/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package net.solarnetwork.web.support;

import java.util.Enumeration;
import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * Extension of MessageSource to allow for getting all messages.
 * 
 * <p>Adapted from {@code magoffin.matt.xweb.util.MessagesSource}.</p>
 * 
 * @author matt
 * @version $Revision$
 */
public interface MessagesSource extends MessageSource {

	/**
	 * Get an enumeration of keys.
	 * @param locale
	 * @return enumeration of message keys
	 */
	public Enumeration<String> getKeys(Locale locale);
	
	/**
	 * Register an additional message resource at runtime.
	 * 
	 * @param resource the resource path to register
	 */
	public void registerMessageResource(String resource);
	
}
