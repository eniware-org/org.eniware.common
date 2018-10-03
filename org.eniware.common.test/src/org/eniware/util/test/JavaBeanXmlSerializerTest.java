/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.codec.binary.Base64InputStream;
import org.eniware.util.JavaBeanXmlSerializer;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test case for the {@link JavaBeanXmlSerializer} class.
 * 
 * @version $Revision$
 */
@ContextConfiguration(locations={"classpath:/net/solarnetwork/test/test-context.xml"})
public class JavaBeanXmlSerializerTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testParseSimple() {
		JavaBeanXmlSerializer helper = new JavaBeanXmlSerializer();
		Map<String, Object> result = helper.parseXml(getClass().getResourceAsStream("test1.xml"));
		assertNotNull(result);
		assertEquals(4, result.size());
		assertEquals("23466f06", result.get("confirmationKey"));
		assertEquals("2011-09-16T05:07:32.579Z", result.get("expiration"));
		assertEquals("123123", result.get("EdgeId"));
		assertEquals("foo@localhost", result.get("username"));
	}
	
	@Test
	public void testParseSimpleEncoded() throws IOException {
		JavaBeanXmlSerializer helper = new JavaBeanXmlSerializer();
		InputStream in = new GZIPInputStream(new Base64InputStream(
				getClass().getResourceAsStream("test1.b64")));
		Map<String, Object> result = helper.parseXml(in);
		assertNotNull(result);
		assertEquals(4, result.size());
		assertEquals("23466f06", result.get("confirmationKey"));
		assertEquals("2011-09-16T05:07:32.579Z", result.get("expiration"));
		assertEquals("123123", result.get("EdgeId"));
		assertEquals("foo@localhost", result.get("username"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testParseNested() {
		JavaBeanXmlSerializer helper = new JavaBeanXmlSerializer();
		Map<String, Object> result = helper.parseXml(getClass().getResourceAsStream("test2.xml"));
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.get("Foo") instanceof Map);
		Map<String, Object> map = (Map<String, Object>) result.get("Foo");
		assertEquals(2, map.size());
		assertEquals("baz", map.get("bar"));
		assertTrue(map.get("Bar") instanceof Map);
		map = (Map<String, Object>) map.get("Bar");
		assertEquals(1, map.size());
		assertEquals("baz", map.get("foo"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testParseCollections() {
		JavaBeanXmlSerializer helper = new JavaBeanXmlSerializer();
		Map<String, Object> result = helper.parseXml(getClass().getResourceAsStream("test3.xml"));
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.get("Foo") instanceof List);
		List<Map<String, Object>> list = (List<Map<String, Object>>)result.get("Foo");
		assertEquals(2, list.size());
		
		Map<String, Object> map = list.get(0);
		assertEquals(2, map.size());
		assertEquals("baz", map.get("bar"));
		assertTrue(map.get("Bar") instanceof List);
		List<Map<String, Object>> list2 = (List<Map<String, Object>>)map.get("Bar");
		assertEquals(3, list2.size());
		assertEquals(1, list2.get(0).size());
		assertEquals("baz", list2.get(0).get("foo"));
		assertEquals(1, list2.get(1).size());
		assertEquals("bam", list2.get(1).get("foo"));
		assertEquals(1, list2.get(2).size());
		assertEquals("wham", list2.get(2).get("foo"));
		
		map = list.get(1);
		assertEquals(1, map.size());
		assertEquals("bam", map.get("bar"));
	}
	
}
