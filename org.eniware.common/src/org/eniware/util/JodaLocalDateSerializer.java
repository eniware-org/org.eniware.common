/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.LocalDate;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * JsonSerializer for {@link LocalDate} into simple strings.
 * 
 * @version 1.1
 */
public class JodaLocalDateSerializer extends JodaBaseJsonSerializer<LocalDate> {

	private static final long serialVersionUID = -619186863552732118L;

	/**
	 * Default constructor.
	 * 
	 * <p>
	 * Uses the pattern <code>yyyy-MM-dd</code>.
	 * </p>
	 */
	public JodaLocalDateSerializer() {
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
	public JodaLocalDateSerializer(String pattern, TimeZone timeZone) {
		super(LocalDate.class, pattern, timeZone);
	}

	/**
	 * Construct with a pattern.
	 * 
	 * @param pattern
	 *        the pattern
	 */
	public JodaLocalDateSerializer(String pattern) {
		super(LocalDate.class, pattern);
	}

	@Override
	public void serialize(LocalDate o, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonGenerationException {
		if ( o == null ) {
			return;
		}
		generator.writeString(serializeWithFormatter(o));
	}

}
