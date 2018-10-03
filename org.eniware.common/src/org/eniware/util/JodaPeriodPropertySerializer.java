/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * {@link PropertySerializer} for Joda Period and Duration objects into Strings.
 * 
 * <p>{@link Duration} instances will be converted to {@link Period} instances
 * via {@link Duration#toPeriod()}.</p>
 * 
 * @version $Revision$
 */
public class JodaPeriodPropertySerializer implements PropertySerializer {

	private PeriodFormatter formatter = ISOPeriodFormat.standard();

	@Override
	public Object serialize(Object data, String propertyName,
			Object propertyValue) {
		if ( propertyValue == null ) {
			return null;
		} else if ( propertyValue instanceof ReadablePeriod  ) {
			return formatter.print((ReadablePeriod)propertyValue);
		} else if ( propertyValue instanceof Duration ) {
			Period p = ((Duration)propertyValue).toPeriod();
			return formatter.print(p);
		} 
		throw new IllegalArgumentException("Unsupported date object [" 
				+propertyValue.getClass() +"]: " +propertyValue);
	}

}
