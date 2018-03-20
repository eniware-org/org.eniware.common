/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.DateTime;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * JsonSerializer for {@link DateTime} into simple strings.
 * 
 * @author matt
 * @version 1.1
 */
public class JodaDateTimeSerializer extends JodaBaseJsonSerializer<DateTime> {

	private static final long serialVersionUID = 8752735196295119155L;

	/**
	 * Default constructor.
	 * 
	 * <p>
	 * Uses the pattern <code>yyyy-MM-dd HH:mm:ss.SSS'Z'</code>.
	 * </p>
	 */
	public JodaDateTimeSerializer() {
		this("yyyy-MM-dd HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("GMT"));
	}

	/**
	 * Construct with values.
	 * 
	 * @param pattern
	 *        the pattern
	 * @param timeZone
	 *        the time zone
	 */
	public JodaDateTimeSerializer(String pattern, TimeZone timeZone) {
		super(DateTime.class, pattern, timeZone);
	}

	/**
	 * Construct with a pattern.
	 * 
	 * @param pattern
	 *        the pattern
	 */
	public JodaDateTimeSerializer(String pattern) {
		super(DateTime.class, pattern);
	}

	@Override
	public void serialize(DateTime o, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonGenerationException {
		if ( o == null ) {
			return;
		}
		generator.writeString(serializeWithFormatter(o));
	}

}
