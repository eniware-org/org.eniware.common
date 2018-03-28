/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;

/**
 * Serialize a {@link BindingResult} into a simple Map, to eliminate circular references.
 * 
 * @version $Revision$
 */
public class BindingResultSerializer implements PropertySerializer {

	@Override
	public Object serialize(Object data, String propertyName, Object propertyValue) {
		if ( propertyValue == null ) {
			return null;
		}
		if ( !(propertyValue instanceof BindingResult) ) {
			throw new IllegalArgumentException("Not a BindingResult: " +propertyValue.getClass());
		}
		BindingResult br = (BindingResult)propertyValue;
		if ( !br.hasErrors() ) {
			return null;
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("objectName", br.getObjectName());
		map.put("errors", br.getAllErrors());
		map.put("globalErrors", br.getGlobalErrors());
		return map;
	}

}
