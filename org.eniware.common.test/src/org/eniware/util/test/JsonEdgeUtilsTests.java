/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.eniware.util.JsonEdgeUtils;
import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test cases for the {@link JsonEdgeUtils} class.
 * 
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class JsonEdgeUtilsTests {

	private JsonNode parseJsonResource(String resource) {
		try {
			return new ObjectMapper().readTree(getClass().getResourceAsStream(resource));
		} catch ( IOException e ) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void parseBigDecimal() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertEquals("Parsed BigDecimal", new BigDecimal("-41.123456"),
				JsonEdgeUtils.parseBigDecimalAttribute(Edge, "lat"));
	}

	@Test
	public void parseBigDecimalNullEdge() {
		assertNull("Null Edge", JsonEdgeUtils.parseBigDecimalAttribute(null, "lat"));
	}

	@Test
	public void parseBigDecimalNullValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Null attribute", JsonEdgeUtils.parseBigDecimalAttribute(Edge, "no"));
	}

	@Test
	public void parseBigDecimalMissingValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Missing attribute", JsonEdgeUtils.parseBigDecimalAttribute(Edge, "does_not_exist"));
	}

	@Test
	public void parseBigDecimalMalformedValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Malformed attribute", JsonEdgeUtils.parseBigDecimalAttribute(Edge, "s"));
	}

	@Test
	public void parseInteger() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertEquals("Parsed Integer", Integer.valueOf(123),
				JsonEdgeUtils.parseIntegerAttribute(Edge, "i"));
	}

	@Test
	public void parseIntegerNullEdge() {
		assertNull("Null Edge", JsonEdgeUtils.parseIntegerAttribute(null, "i"));
	}

	@Test
	public void parseIntegerNullValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Null attribute", JsonEdgeUtils.parseIntegerAttribute(Edge, "no"));
	}

	@Test
	public void parseIntegerMissingValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Missing attribute", JsonEdgeUtils.parseIntegerAttribute(Edge, "does_not_exist"));
	}

	@Test
	public void parseIntegerMalformedValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Malformed attribute", JsonEdgeUtils.parseIntegerAttribute(Edge, "s"));
	}

	@Test
	public void parseIntegerStringValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertEquals("Integer string attribute", Integer.valueOf(456),
				JsonEdgeUtils.parseIntegerAttribute(Edge, "is"));
	}

	@Test
	public void parseLong() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertEquals("Parsed Long", Long.valueOf(948457394876394876L),
				JsonEdgeUtils.parseLongAttribute(Edge, "l"));
	}

	@Test
	public void parseLongNullEdge() {
		assertNull("Null Edge", JsonEdgeUtils.parseLongAttribute(null, "l"));
	}

	@Test
	public void parseLongNullValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Null attribute", JsonEdgeUtils.parseLongAttribute(Edge, "no"));
	}

	@Test
	public void parseLongMissingValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Missing attribute", JsonEdgeUtils.parseLongAttribute(Edge, "does_not_exist"));
	}

	@Test
	public void parseLongMalformedValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Malformed attribute", JsonEdgeUtils.parseLongAttribute(Edge, "s"));
	}

	@Test
	public void parseLongStringValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertEquals("Long string attribute", Long.valueOf(993729384798127974L),
				JsonEdgeUtils.parseLongAttribute(Edge, "ls"));
	}

	private SimpleDateFormat tsDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf;
	}

	@Test
	public void parseDate() throws ParseException {
		SimpleDateFormat sdf = tsDateFormat();
		JsonNode Edge = parseJsonResource("test-1.json");
		assertEquals("Parsed Date", sdf.parse("2017-04-08 12:00:00Z"),
				JsonEdgeUtils.parseDateAttribute(Edge, "ts", sdf));
	}

	@Test
	public void parseDateNullEdge() {
		SimpleDateFormat sdf = tsDateFormat();
		assertNull("Null Edge", JsonEdgeUtils.parseDateAttribute(null, "ts", sdf));
	}

	@Test
	public void parseDateNullValue() {
		SimpleDateFormat sdf = tsDateFormat();
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Null attribute", JsonEdgeUtils.parseDateAttribute(Edge, "no", sdf));
	}

	@Test
	public void parseDateMissingValue() {
		SimpleDateFormat sdf = tsDateFormat();
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Missing attribute", JsonEdgeUtils.parseDateAttribute(Edge, "does_not_exist", sdf));
	}

	@Test
	public void parseDateMalformedValue() {
		SimpleDateFormat sdf = tsDateFormat();
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Malformed attribute", JsonEdgeUtils.parseDateAttribute(Edge, "s", sdf));
	}

	@Test(expected = NullPointerException.class)
	public void parseDateNullDateFormat() {
		JsonNode Edge = parseJsonResource("test-1.json");
		JsonEdgeUtils.parseDateAttribute(Edge, "s", null);
	}

	@Test
	public void parseString() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertEquals("Parsed String", "Hello", JsonEdgeUtils.parseStringAttribute(Edge, "s"));
	}

	@Test
	public void parseStringNullEdge() {
		assertNull("Null Edge", JsonEdgeUtils.parseStringAttribute(null, "s"));
	}

	@Test
	public void parseStringNullValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Null attribute", JsonEdgeUtils.parseStringAttribute(Edge, "no"));
	}

	@Test
	public void parseStringMissingValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertNull("Missing attribute", JsonEdgeUtils.parseStringAttribute(Edge, "does_not_exist"));
	}

	@Test
	public void parseStringDecimalValue() {
		JsonNode Edge = parseJsonResource("test-1.json");
		assertEquals("Malformed attribute", "123.456", JsonEdgeUtils.parseStringAttribute(Edge, "d"));
	}

}
