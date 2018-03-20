/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.LocalDateTime;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * JsonSerializer for {@link LocalDateTime} into simple strings.
 * 
 * @author matt
 * @version 1.1
 */
public class JodaLocalDateTimeSerializer extends JodaBaseJsonSerializer<LocalDateTime> {

	private static final long serialVersionUID = -2514379393212280543L;

	/**
	 * Default constructor.
	 * 
	 * <p>
	 * Uses the pattern <code>yyyy-MM-dd HH:mm</code>.
	 * </p>
	 */
	public JodaLocalDateTimeSerializer() {
		super(LocalDateTime.class, "yyyy-MM-dd HH:mm");
	}

	/**
	 * Construct with values.
	 * 
	 * @param pattern
	 *        the pattern
	 * @param timeZone
	 *        the time zone
	 */
	public JodaLocalDateTimeSerializer(String pattern, TimeZone timeZone) {
		super(LocalDateTime.class, pattern, timeZone);
	}

	/**
	 * Construct with a pattern.
	 * 
	 * @param pattern
	 *        the pattern
	 */
	public JodaLocalDateTimeSerializer(String pattern) {
		super(LocalDateTime.class, pattern);
	}

	@Override
	public void serialize(LocalDateTime o, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonGenerationException {
		if ( o == null ) {
			return;
		}
		generator.writeString(serializeWithFormatter(o));
	}

}
