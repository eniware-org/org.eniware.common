/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package net.solarnetwork.web.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * Common utility helper methods for web processing.
 * 
 * @author matt
 * @version $Id$
 */
public final class WebUtils {

	/**
	 * Resolve a ModelAndView with an empty model and a view name determined by the
	 * URL "suffix".
	 * 
	 * <p>If the {@link #getViewName()} method returns a value, that view name is used
	 * for every request. Otherwise, this sets the view name to the value of the URL 
	 * "suffix", that is, everything after the last period in the URL. This uses 
	 * {@link StringUtils#getFilenameExtension(String)} on the request URI to accomplish
	 * this. For example a URL like {@code /myController.json} would resolve to a view
	 * named {@code json}. This can be handy when you want to return different data formats
	 * for the same business logic, such as XML or JSON.</p>
	 * 
	 * <p>The {@code viewName} parameter can be used to override the view mapping logic
	 * and instead simply return a {@link ModelAndView} object for the given name. 
	 * For normal controllers with a configurable view name property, that property can
	 * be passed in here, but usually the value will not be configured.</p>
	 * 
	 * @param request the HTTP request
	 * @param viewName the custom view name
	 * @return a view name (never <em>null</em>)
	 */
	public static String resolveViewFromUrlExtension(HttpServletRequest request, String viewName) {
		// resolve the final view name based on the URL suffix, i.e. "*.xml" -> "xml"
		String resolvedViewName = viewName;
		if ( resolvedViewName == null ) {
			resolvedViewName = StringUtils.getFilenameExtension(request.getRequestURI());
		}
		return resolvedViewName;
	}

}
