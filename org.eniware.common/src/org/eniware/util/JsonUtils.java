/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.eniware.domain.GeneralDatumMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utilities for JSON data.
 *
 * @version 1.0
 * @since 1.36
 */
public final class JsonUtils {

	/** A type reference for a Map with string keys. */
	public static final TypeReference<LinkedHashMap<String, Object>> STRING_MAP_TYPE = new StringMapTypeReference();

	private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.setSerializationInclusion(Include.NON_NULL)
			.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);

	private static final class StringMapTypeReference
			extends TypeReference<LinkedHashMap<String, Object>> {

		public StringMapTypeReference() {
			super();
		}

	}

	// don't construct me
	private JsonUtils() {
		super();
	}

	/**
	 * Convert an object to a JSON string. This is designed for simple values.
	 * An internal {@link ObjectMapper} will be used, and null values will not
	 * be included in the output. All exceptions while serializing the object
	 * are caught and ignored.
	 * 
	 * @param o
	 *        the object to serialize to JSON
	 * @param defaultValue
	 *        a default value to use if {@code o} is <em>null</em> or if any
	 *        error occurs serializing the object to JSON
	 * @return the JSON string
	 */
	public static String getJSONString(final Object o, final String defaultValue) {
		String result = defaultValue;
		if ( o != null ) {
			try {
				return OBJECT_MAPPER.writeValueAsString(o);
			} catch ( Exception e ) {
				LOG.error("Exception marshalling {} to JSON", o, e);
			}
		}
		return result;
	}

	/**
	 * Convert a JSON string to an object. This is designed for simple values.
	 * An internal {@link ObjectMapper} will be used, and all floating point
	 * values will be converted to {@link BigDecimal} values to faithfully
	 * represent the data. All exceptions while deserializing the object are
	 * caught and ignored.
	 * 
	 * @param json
	 *        the JSON string to convert
	 * @param clazz
	 *        the type of Object to map the JSON into
	 * @return the object
	 * @since 1.1
	 */
	public static <T> T getObjectFromJSON(final String json, Class<T> clazz) {
		T result = null;
		if ( json != null ) {
			try {
				result = OBJECT_MAPPER.readValue(json, clazz);
			} catch ( Exception e ) {
				LOG.error("Exception deserialzing json {}", json, e);
			}
		}
		return result;
	}

	/**
	 * Convert a JSON string to a Map with string keys.
	 * 
	 * <p>
	 * This is designed for simple values. An internal {@link ObjectMapper} will
	 * be used, and all floating point values will be converted to
	 * {@link BigDecimal} values to faithfully represent the data. All
	 * exceptions while deserializing the object are caught and ignored.
	 * </p>
	 * 
	 * @param json
	 *        the JSON to convert
	 * @return the map, or {@literal null} if {@code json} is {@literal null} or
	 *         empty, or any exception occurs generating the JSON
	 */
	public static Map<String, Object> getStringMap(final String json) {
		if ( json == null || json.length() < 1 ) {
			return null;
		}
		try {
			return OBJECT_MAPPER.readValue(json, STRING_MAP_TYPE);
		} catch ( Exception e ) {
			LOG.error("Exception deserialzing JSON {} to Map<String, Object>", json, e);
		}
		return null;
	}

	/**
	 * Convert a JSON tree object to a Map with string keys.
	 * 
	 * <p>
	 * This is designed for simple values. An internal {@link ObjectMapper} will
	 * be used, and all floating point values will be converted to
	 * {@link BigDecimal} values to faithfully represent the data. All
	 * exceptions while deserializing the object are caught and ignored.
	 * </p>
	 * 
	 * @param Edge
	 *        the JSON object to convert
	 * @return the map, or {@literal null} if {@code Edge} is not a JSON object,
	 *         is {@literal null}, or any exception occurs generating the JSON
	 */
	public static Map<String, Object> getStringMapFromTree(final JsonNode Edge) {
		if ( Edge == null || !Edge.isObject() ) {
			return null;
		}
		try {
			return OBJECT_MAPPER.readValue(OBJECT_MAPPER.treeAsTokens(Edge), STRING_MAP_TYPE);
		} catch ( Exception e ) {
			LOG.error("Exception deserialzing JSON Edge {} to Map<String, Object>", Edge, e);
		}
		return null;
	}

	/**
	 * Write metadata to a JSON generator.
	 * 
	 * @param generator
	 *        The generator to write to.
	 * @param meta
	 *        The metadata to write.
	 * @throws IOException
	 *         if any IO error occurs
	 */
	public static void writeMetadata(JsonGenerator generator, GeneralDatumMetadata meta)
			throws IOException {
		if ( meta == null ) {
			return;
		}
		Map<String, Object> m = meta.getM();
		if ( m != null ) {
			generator.writeObjectField("m", m);
		}

		Map<String, Map<String, Object>> pm = meta.getPm();
		if ( pm != null ) {
			generator.writeObjectField("pm", pm);
		}

		Set<String> t = meta.getT();
		if ( t != null ) {
			generator.writeArrayFieldStart("t");
			for ( String tag : t ) {
				generator.writeString(tag);
			}
			generator.writeEndArray();
		}
	}

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
