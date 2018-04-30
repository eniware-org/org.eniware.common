/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * {@link PropertySerializer} for Joda date/time objects into Strings.
 *
 * @version $Revision$ $Date$
 */
public class JodaDatePropertySerializer implements PropertySerializer {
	
	private DateTimeFormatter formatter = null;

	/**
	 * Construct from a String date pattern.
	 * 
	 * @param pattern the Joda date format pattern
	 */
	public JodaDatePropertySerializer(String pattern) {
		this(pattern, null);
	}
	
	/**
	 * Construct from a String date pattern.
	 * 
	 * @param pattern the Joda date format pattern
	 * @param timeZone the time zone to format in
	 */
	public JodaDatePropertySerializer(String pattern, TimeZone timeZone) {
		formatter = DateTimeFormat.forPattern(pattern);
		if ( timeZone != null ) {
			formatter = formatter.withZone(DateTimeZone.forTimeZone(timeZone));
		}
	}
	
	/* (non-Javadoc)
	 * @see net.sf.eniwarenetwork.util.PropertySerializer#serialize(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	public Object serialize(Object data, String propertyName, Object propertyValue) {
		if ( propertyValue == null ) {
			return null;
		} else if ( propertyValue instanceof ReadableInstant  ) {
			return formatter.print((ReadableInstant)propertyValue);
		} else if ( propertyValue instanceof ReadablePartial ) {
			return formatter.print((ReadablePartial)propertyValue);
		} else if ( propertyValue instanceof Date ) {
			return formatter.print(((Date)propertyValue).getTime());
		} else if ( propertyValue instanceof Calendar ) {
			return formatter.print(((Calendar)propertyValue).getTimeInMillis());
		} 
		throw new IllegalArgumentException("Unsupported date object [" 
				+propertyValue.getClass() +"]: " +propertyValue);
	}

}
