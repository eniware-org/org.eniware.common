/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.support;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter for adding arbitrary HTTP headers to requests, to add cache headers to
 * static files.
 * 
 * <p>
 * Adapted from
 * http://www.jspinsider.com/content/dev/afessh/another-filter-every
 * -site-should-have.html
 * </p>
 * 
 * @version 1.0
 */
public class ResponseHeaderFilter implements Filter {

	private FilterConfig fc;

	@Override
	public void destroy() {
		this.fc = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// set the provided HTTP response parameters
		for ( Enumeration<String> e = fc.getInitParameterNames(); e.hasMoreElements(); ) {
			String headerName = e.nextElement();
			httpResponse.setHeader(headerName, fc.getInitParameter(headerName));
		}
		// pass the request/response on
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.fc = filterConfig;
	}

}
