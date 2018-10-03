/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.TimeZone;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

/**
 * Abstract {@link JsonDeserializer} class for converting strings into Joda
 * objects.
 *
 * @version 1.0
 */
public abstract class JodaBaseJsonDeserializer<T> extends StdScalarDeserializer<T> {

	private static final long serialVersionUID = -7136078168225301983L;

	/** The {@link DateTimeFormatter} for parsing dates. */
	protected final DateTimeFormatter formatter;

	/**
	 * Construct from a String date pattern.
	 * 
	 * @param clazz
	 *        the class type
	 * @param pattern
	 *        the Joda date format pattern
	 */
	public JodaBaseJsonDeserializer(Class<T> clazz, String pattern) {
		this(clazz, pattern, null);
	}

	/**
	 * Construct from a String date pattern.
	 * 
	 * @param clazz
	 *        the class type
	 * @param pattern
	 *        the Joda date format pattern
	 * @param timeZone
	 *        the time zone to format in
	 */
	public JodaBaseJsonDeserializer(Class<T> clazz, String pattern, TimeZone timeZone) {
		super(clazz);
		if ( timeZone != null ) {
			formatter = DateTimeFormat.forPattern(pattern).withZone(DateTimeZone.forTimeZone(timeZone));
		} else {
			formatter = DateTimeFormat.forPattern(pattern);
		}
	}

}
