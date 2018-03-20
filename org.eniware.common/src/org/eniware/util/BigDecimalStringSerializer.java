/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Specialized serializer of {@link BigDecimal} to string values.
 * 
 * @author matt
 * @version 1.0
 * @since 1.37
 */
public class BigDecimalStringSerializer extends StdSerializer<BigDecimal> {

	private static final long serialVersionUID = 4462532770316408808L;

	/**
	 * Singleton instance to use.
	 */
	public final static BigDecimalStringSerializer INSTANCE = new BigDecimalStringSerializer();

	/**
	 * Default constructor.
	 * <p>
	 * Note: usually you should NOT create new instances, but instead use
	 * {@link #INSTANCE} which is stateless and fully thread-safe. However,
	 * there are cases where constructor is needed; for example, when using
	 * explicit serializer annotations like
	 * {@link com.fasterxml.jackson.databind.annotation.JsonSerialize#using}.
	 * </p>
	 */
	public BigDecimalStringSerializer() {
		super(BigDecimal.class);
	}

	/**
	 * Construct with specific class.
	 * 
	 * @param handledType
	 *        the type to use
	 */
	public BigDecimalStringSerializer(Class<? extends BigDecimal> handledType) {
		super(handledType, false);
	}

	@Override
	public boolean isEmpty(SerializerProvider prov, BigDecimal value) {
		if ( value == null ) {
			return true;
		}
		String str = value.toString();
		return str.isEmpty();
	}

	@Override
	public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		gen.writeString(value.toPlainString());
	}

	@Override
	public void serializeWithType(BigDecimal value, JsonGenerator gen, SerializerProvider provider,
			TypeSerializer typeSer) throws IOException {
		typeSer.writeTypePrefixForScalar(value, gen);
		serialize(value, gen, provider);
		typeSer.writeTypeSuffixForScalar(value, gen);
	}

	@Override
	public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
		return createSchemaNode("string", true);
	}

	@Override
	public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint)
			throws JsonMappingException {
		if ( visitor != null ) {
			visitor.expectStringFormat(typeHint);
		}
	}
}
