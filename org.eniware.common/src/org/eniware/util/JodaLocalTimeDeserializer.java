/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.LocalTime;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

/**
 * JsonDeserializer for {@link LocalTime} objects from formatted strings.
 *
 * @version 1.0
 */
public class JodaLocalTimeDeserializer extends JodaBaseJsonDeserializer<LocalTime> {

	private static final long serialVersionUID = -557649930976184805L;

	/**
	 * Default constructor.
	 * 
	 * <p>
	 * Uses the pattern <code>HH:mm</code>.
	 * </p>
	 */
	public JodaLocalTimeDeserializer() {
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
	public JodaLocalTimeDeserializer(String pattern, TimeZone timeZone) {
		super(LocalTime.class, pattern, timeZone);
	}

	/**
	 * Construct with a pattern.
	 * 
	 * @param pattern
	 *        the pattern
	 */
	public JodaLocalTimeDeserializer(String pattern) {
		super(LocalTime.class, pattern);
	}

	@Override
	public LocalTime deserialize(JsonParser parser, DeserializationContext context) throws IOException,
			JsonProcessingException {
		return formatter.parseLocalTime(parser.getText());
	}

}
