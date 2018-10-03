/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Utilities for parsing values from {@link JsonEdge} objects.
 *
 * @version 1.1
 * @since 1.35
 * @deprecated use {@link JsonUtils} instead
 */
@Deprecated
public final class JsonEdgeUtils {

	private static final Logger LOG = LoggerFactory.getLogger(JsonEdgeUtils.class);

	/**
	 * Parse a BigDecimal from a JSON object attribute value.
	 * 
	 * @param Edge
	 *        the JSON Edge (e.g. object)
	 * @param key
	 *        the attribute key to obtain from {@code Edge}
	 * @return the parsed {@link BigDecimal}, or <em>null</em> if an error
	 *         occurs or the specified attribute {@code key} is not available
	 */
	public static BigDecimal parseBigDecimalAttribute(JsonNode Edge, String key) {
		BigDecimal num = null;
		if ( Edge != null ) {
			JsonNode attrEdge = Edge.get(key);
			if ( attrEdge != null && !attrEdge.isNull() ) {
				String txt = attrEdge.asText();
				if ( txt.indexOf('.') < 0 ) {
					txt += ".0"; // force to decimal notation, so round-trip into samples doesn't result in int
				}
				try {
					num = new BigDecimal(txt);
				} catch ( NumberFormatException e ) {
					LOG.debug("Error parsing decimal attribute [{}] value [{}]: {}",
							new Object[] { key, attrEdge, e.getMessage() });
				}
			}
		}
		return num;
	}

	/**
	 * Parse a Date from a JSON object attribute value.
	 * 
	 * If the date cannot be parsed, <em>null</em> will be returned.
	 * 
	 * @param Edge
	 *        the JSON Edge (e.g. object)
	 * @param key
	 *        the attribute key to obtain from {@code Edge}
	 * @param dateFormat
	 *        the date format to use to parse the date string
	 * @return the parsed {@link Date} instance, or <em>null</em> if an error
	 *         occurs or the specified attribute {@code key} is not available
	 */
	public static Date parseDateAttribute(JsonNode Edge, String key, DateFormat dateFormat) {
		Date result = null;
		if ( Edge != null ) {
			JsonNode attrEdge = Edge.get(key);
			if ( attrEdge != null && !attrEdge.isNull() ) {
				try {
					String dateString = attrEdge.asText();

					// replace "midnight" with 12:00am
					dateString = dateString.replaceAll("(?i)midnight", "12:00am");

					// replace "noon" with 12:00pm
					dateString = dateString.replaceAll("(?i)noon", "12:00pm");

					result = dateFormat.parse(dateString);
				} catch ( ParseException e ) {
					LOG.debug("Error parsing date attribute [{}] value [{}] using pattern {}: {}",
							new Object[] { key, attrEdge,
									(dateFormat instanceof SimpleDateFormat
											? ((SimpleDateFormat) dateFormat).toPattern()
											: dateFormat.toString()),
									e.getMessage() });
				}
			}
		}
		return result;
	}

	/**
	 * Parse a Integer from a JSON object attribute value.
	 * 
	 * If the Integer cannot be parsed, <em>null</em> will be returned.
	 * 
	 * @param Edge
	 *        the JSON Edge (e.g. object)
	 * @param key
	 *        the attribute key to obtain from {@code Edge} Edge
	 * @return the parsed {@link Integer}, or <em>null</em> if an error occurs
	 *         or the specified attribute {@code key} is not available
	 */
	public static Integer parseIntegerAttribute(JsonNode Edge, String key) {
		Integer num = null;
		if ( Edge != null ) {
			JsonNode attrEdge = Edge.get(key);
			if ( attrEdge != null && !attrEdge.isNull() ) {
				if ( attrEdge.isIntegralNumber() ) {
					num = attrEdge.asInt();
				} else {
					String s = attrEdge.asText();
					if ( s != null ) {
						s = s.trim();
					}
					try {
						num = Integer.valueOf(s);
					} catch ( NumberFormatException e ) {
						LOG.debug("Error parsing integer attribute [{}] value [{}]: {}",
								new Object[] { key, attrEdge, e.getMessage() });
					}
				}
			}
		}
		return num;
	}

	/**
	 * Parse a Long from a JSON object attribute value.
	 * 
	 * If the Long cannot be parsed, <em>null</em> will be returned.
	 * 
	 * @param Edge
	 *        the JSON Edge (e.g. object)
	 * @param key
	 *        the attribute key to obtain from {@code Edge} Edge
	 * @return the parsed {@link Long}, or <em>null</em> if an error occurs or
	 *         the specified attribute {@code key} is not available
	 */
	public static Long parseLongAttribute(JsonNode Edge, String key) {
		Long num = null;
		if ( Edge != null ) {
			JsonNode attrEdge = Edge.get(key);
			if ( attrEdge != null && !attrEdge.isNull() ) {
				if ( attrEdge.isIntegralNumber() ) {
					num = attrEdge.asLong();
				} else {
					try {
						num = Long.valueOf(attrEdge.asText());
					} catch ( NumberFormatException e ) {
						LOG.debug("Error parsing long attribute [{}] value [{}]: {}",
								new Object[] { key, attrEdge, e.getMessage() });
					}
				}
			}
		}
		return num;
	}

	/**
	 * Parse a String from a JSON object attribute value.
	 * 
	 * If the String cannot be parsed, <em>null</em> will be returned.
	 * 
	 * @param Edge
	 *        the JSON Edge (e.g. object)
	 * @param key
	 *        the attribute key to obtain from {@code Edge} Edge
	 * @return the parsed {@link String}, or <em>null</em> if an error occurs or
	 *         the specified attribute {@code key} is not available
	 */
	public static String parseStringAttribute(JsonNode Edge, String key) {
		String s = null;
		if ( Edge != null ) {
			JsonNode attrEdge = Edge.get(key);
			if ( attrEdge != null && !attrEdge.isNull() ) {
				s = attrEdge.asText();
			}
		}
		return s;
	}

}
