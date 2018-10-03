/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.support;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Extension of {@link ReloadableResourceBundleMessagesSource} to allow finding
 * all keys for all messages.
 * 
 * <p>
 * Adapted from {@code magoffin.matt.xweb.util.ResourceBundleMessagesSource}.
 * </p>
 * 
 * @version $Revision$
 */
public class ReloadableResourceBundleMessagesSource extends ReloadableResourceBundleMessageSource
		implements MessageSource, MessagesSource {

	/**
	 * Private copy of basenames, as parent class does not provide a way to
	 * access this.
	 */
	private String[] basenames;
	private MessagesSource parent;

	@Override
	public void setBasenames(String... basenames) {
		super.setBasenames(basenames);
		this.basenames = basenames;
	}

	@Override
	public void registerMessageResource(String resource) {
		String[] newBasenames = new String[basenames.length + 1];
		System.arraycopy(basenames, 0, newBasenames, 0, basenames.length);
		newBasenames[newBasenames.length - 1] = resource;
		super.setBasenames(newBasenames);
		this.basenames = newBasenames;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Enumeration<String> getKeys(Locale locale) {
		PropertiesHolder propHolder = super.getMergedProperties(locale);
		return Collections.enumeration((Set) propHolder.getProperties().keySet());
	}

	@Override
	protected String getMessageInternal(String code, Object[] args, Locale locale) {
		String msg = super.getMessageInternal(code, args, locale);
		if ( msg != null ) {
			return msg;
		}
		if ( parent != null ) {
			return parent.getMessage(code, args, locale);
		}
		return null;
	}

	/**
	 * @return the parent
	 */
	public MessagesSource getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *        the parent to set
	 */
	public void setParent(MessagesSource parent) {
		this.parent = parent;
	}

}
