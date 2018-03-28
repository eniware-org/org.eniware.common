/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.LocalDate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

/**
 * JsonDeserializer for {@link LocalDate} objects from formatted strings.
 * 
 * @version 1.0
 */
public class JodaLocalDateDeserializer extends JodaBaseJsonDeserializer<LocalDate> {

	private static final long serialVersionUID = -6903430362288878512L;

	/**
	 * Default constructor.
	 * 
	 * <p>
	 * Uses the pattern <code>yyyy-MM-dd</code>.
	 * </p>
	 */
	public JodaLocalDateDeserializer() {
		super(LocalDate.class, "yyyy-MM-dd");
	}

	/**
	 * Construct with values.
	 * 
	 * @param pattern
	 *        the pattern
	 * @param timeZone
	 *        the time zone
	 */
	public JodaLocalDateDeserializer(String pattern, TimeZone timeZone) {
		super(LocalDate.class, pattern, timeZone);
	}

	/**
	 * Construct with a pattern.
	 * 
	 * @param pattern
	 *        the pattern
	 */
	public JodaLocalDateDeserializer(String pattern) {
		super(LocalDate.class, pattern);
	}

	@Override
	public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException,
			JsonProcessingException {
		return formatter.parseLocalDate(parser.getText());
	}

}
