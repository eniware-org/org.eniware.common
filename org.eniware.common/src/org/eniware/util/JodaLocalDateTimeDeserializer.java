/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.LocalDateTime;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

/**
 * JsonDeserializer for {@link LocalDateTime} objects from formatted strings.
 *
 * @version 1.0
 */
public class JodaLocalDateTimeDeserializer extends JodaBaseJsonDeserializer<LocalDateTime> {

	private static final long serialVersionUID = 5709750413856542012L;

	/**
	 * Default constructor.
	 * 
	 * <p>
	 * Uses the pattern <code>yyyy-MM-dd HH:mm</code>.
	 * </p>
	 */
	public JodaLocalDateTimeDeserializer() {
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
	public JodaLocalDateTimeDeserializer(String pattern, TimeZone timeZone) {
		super(LocalDateTime.class, pattern, timeZone);
	}

	/**
	 * Construct with a pattern.
	 * 
	 * @param pattern
	 *        the pattern
	 */
	public JodaLocalDateTimeDeserializer(String pattern) {
		super(LocalDateTime.class, pattern);
	}

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		return formatter.parseLocalDateTime(parser.getText());
	}

}
