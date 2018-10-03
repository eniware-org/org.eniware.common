/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.TimeZone;

/**
 * {@link PropertySerializer} for {@link TimeZone} objects into TimeZone ID strings.
 *
 * @version $Revision$
 * @since 1.2
 */
public class TimeZonePropertySerializer implements PropertySerializer {

	@Override
	public Object serialize(Object data, String propertyName,
			Object propertyValue) {
		if ( propertyValue == null ) {
			return null;
		} else if ( propertyValue instanceof TimeZone  ) {
			return ((TimeZone)propertyValue).getID();
		} 
		throw new IllegalArgumentException("Unsupported date object [" 
				+propertyValue.getClass() +"]: " +propertyValue);	}

}
