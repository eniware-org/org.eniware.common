/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.gemini;

import org.eclipse.virgo.web.dm.ServerOsgiBundleXmlWebApplicationContext;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;

/**
 * Replacement of {@link ServerOsgiBundleXmlWebApplicationContext} that disables
 * XML validation on Spring configuration files.
 * 
 * <p>
 * This can greatly increase application startup time on low-powered devices, as
 * well as reduce network access.
 * </p>
 *
 * @version 1.1
 */
public class NonValidatingServerOsgiBundleXmlWebApplicationContext
		extends ServerOsgiBundleXmlWebApplicationContext {

	/**
	 * Default constructor.
	 */
	public NonValidatingServerOsgiBundleXmlWebApplicationContext() {
		super();
	}

	/**
	 * Construct with configuration locations.
	 * 
	 * @param configLocations
	 *        The locations.
	 */
	public NonValidatingServerOsgiBundleXmlWebApplicationContext(String[] configLocations) {
		super(configLocations);
	}

	/**
	 * Construct with a parent context.
	 * 
	 * @param parent
	 *        The parent context.
	 */
	public NonValidatingServerOsgiBundleXmlWebApplicationContext(ApplicationContext parent) {
		super(parent);
	}

	/**
	 * Construct with configuration locations and a parent context.
	 * 
	 * @param configLocations
	 *        The configuration locations.
	 * @param parent
	 *        The parent context.
	 */
	public NonValidatingServerOsgiBundleXmlWebApplicationContext(String[] configLocations,
			ApplicationContext parent) {
		super(configLocations, parent);
	}

	@Override
	protected void initBeanDefinitionReader(XmlBeanDefinitionReader beanDefinitionReader) {
		super.initBeanDefinitionReader(beanDefinitionReader);
		beanDefinitionReader.setValidating(false);
	}

}
