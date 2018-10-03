/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.LocalTime;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * JsonSerializer for {@link LocalTime} into simple strings.
 *
 * @version 1.1
 */
public class JodaLocalTimeSerializer extends JodaBaseJsonSerializer<LocalTime> {

	private static final long serialVersionUID = -3719618691614534679L;

	/**
	 * Default constructor.
	 * 
	 * <p>
	 * Uses the pattern <code>HH:mm</code>.
	 * </p>
	 */
	public JodaLocalTimeSerializer() {
		super(LocalTime.class, "HH:mm");
	}

	/**
	 * Construct with values.
	 * 
	 * @param pattern
	 *        the pattern
	 * @param timeZone
	 *        the time zone
	 */
	public JodaLocalTimeSerializer(String pattern, TimeZone timeZone) {
		super(LocalTime.class, pattern, timeZone);
	}

	/**
	 * Construct with a pattern.
	 * 
	 * @param pattern
	 *        the pattern
	 */
	public JodaLocalTimeSerializer(String pattern) {
		super(LocalTime.class, pattern);
	}

	@Override
	public void serialize(LocalTime o, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		if ( o == null ) {
			return;
		}
		generator.writeString(serializeWithFormatter(o));
	}

}
