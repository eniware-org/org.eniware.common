/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.support;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * Helper class to inject beans into a ServletContext.
 * 
 * @version $Revision$
 */
public class ServletContextBeanInjector implements ServletContextAware {

	private Map<String, Object> beans;
	private ServletContext servletContext;
	private boolean initialized = false;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		injectBeans();
	}
	
	private void injectBeans() {
		if ( this.initialized || this.beans == null  || this.servletContext == null ) {
			return;
		}
		for ( Map.Entry<String, Object> me : beans.entrySet() ) {
			this.servletContext.setAttribute(me.getKey(), me.getValue());
		}
		initialized = true;
	}
	
	/**
	 * Initialize the ServletContext.
	 */
	public void init() {
		if ( beans == null || servletContext == null ) {
			return;
		}
		injectBeans();
	}

	public Map<String, Object> getBeans() {
		return beans;
	}
	public void setBeans(Map<String, Object> beans) {
		this.beans = beans;
	}

}
