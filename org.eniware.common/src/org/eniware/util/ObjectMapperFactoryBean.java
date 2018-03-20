/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.FactoryBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Factory for {@link ObjectMapper} that allows configuring an application-wide
 * ObjectMapper.
 * 
 * <p>
 * This factory will generate a {@link Module} and register that with the
 * returned {@link ObjectMapper}.
 * </p>
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>moduleName</dt>
 * <dd>A {@link Module} name to use.</dd>
 * 
 * <dt>moduleVersion</dt>
 * <dd>A {@link Version} to use for the module.</dd>
 * 
 * <dt>serializers</dt>
 * <dd>A list of serializers to register with the module.</dd>
 * 
 * <dt>deserializers</dt>
 * <dd>A list of deserializers to register with the module. Note that these must
 * be subclasses of {@link StdDeserializer}.</dd>
 * 
 * <dt>mapper</dt>
 * <dd>The {@link ObjectMapper} to configure.</dd>
 * 
 * <dt>serializationInclusion</dt>
 * <dd>A serialization inclusion setting to configure.</dd>
 * 
 * <dt>featuresToEnable</dt>
 * <dd>A list of {@link SerializationFeature} or {@link DeserializationFeature}
 * flags to enable.</dd>
 * 
 * <dt>featuresToDisable</dt>
 * <dd>A list of {@link SerializationFeature} or {@link DeserializationFeature}
 * flags to disable.</dd>
 * </dl>
 * 
 * @author matt
 * @version 1.3
 */
public class ObjectMapperFactoryBean extends ObjectMapperModuleSupport
		implements FactoryBean<ObjectMapper> {

	private JsonInclude.Include serializationInclusion = JsonInclude.Include.NON_NULL;
	private List<Object> featuresToEnable = null;
	private List<Object> featuresToDisable = null;

	@Override
	public ObjectMapper getObject() throws Exception {
		ObjectMapper mapper = getObjectMapper();
		if ( mapper == null ) {
			mapper = new ObjectMapper();
			setObjectMapper(mapper);
		}
		SimpleModule module = new SimpleModule(getModuleName(), getModuleVersion());
		if ( getSerializers() != null ) {
			for ( JsonSerializer<?> serializer : getSerializers() ) {
				module.addSerializer(serializer);
			}
		}
		if ( getDeserializers() != null ) {
			for ( JsonDeserializer<?> deserializer : getDeserializers() ) {
				registerDeserializer(module, deserializer);
			}
		}
		if ( getKeyDeserializers() != null ) {
			for ( TypedKeyDeserializer deserializer : getKeyDeserializers() ) {
				module.addKeyDeserializer(deserializer.getClass(), deserializer.getKeyDeserializer());
			}
		}
		if ( getKeySerializers() != null ) {
			for ( JsonSerializer<?> serializer : getKeySerializers() ) {
				registerKeySerializer(module, serializer);
			}
		}
		if ( serializationInclusion != null ) {
			mapper.setSerializationInclusion(serializationInclusion);
		}
		setupFeatures(mapper, featuresToEnable, true);
		setupFeatures(mapper, featuresToDisable, false);
		mapper.registerModule(module);
		return mapper;
	}

	private void setupFeatures(final ObjectMapper m, final Collection<?> features, final boolean state) {
		if ( features == null ) {
			return;
		}
		for ( Object o : features ) {
			if ( o instanceof SerializationFeature ) {
				m.configure((SerializationFeature) o, state);
			} else if ( o instanceof DeserializationFeature ) {
				m.configure((DeserializationFeature) o, state);
			}
		}
	}

	@Override
	public Class<?> getObjectType() {
		return ObjectMapper.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	/**
	 * Get the {@code ObjectMapper} to use.
	 * 
	 * @return the mapper
	 * @deprecated see {@link #getObjectMapper()}
	 */
	@Deprecated
	public ObjectMapper getMapper() {
		return getObjectMapper();
	}

	/**
	 * Set the {@code ObjectMapper} to use.
	 * 
	 * @param obj
	 *        the mapper to use
	 * @deprecated see @link {@link #setObjectMapper(ObjectMapper)}
	 */
	@Deprecated
	public void setMapper(ObjectMapper obj) {
		setObjectMapper(obj);
	}

	/**
	 * Get the serialization inclusion setting.
	 * 
	 * @return the serialization inclusion
	 * @since 1.2
	 */
	public JsonInclude.Include getSerializationInclusion() {
		return serializationInclusion;
	}

	/**
	 * Set the serialization inclusion to use.
	 * 
	 * @param serializationInclusion
	 *        the inclusion setting
	 * @since 1.2
	 */
	public void setSerializationInclusion(JsonInclude.Include serializationInclusion) {
		this.serializationInclusion = serializationInclusion;
	}

	public List<Object> getFeaturesToEnable() {
		return featuresToEnable;
	}

	/**
	 * Set a list of {@link SerializationFeature} or
	 * {@link DeserializationFeature} flags to enable.
	 * 
	 * @param featuresToEnable
	 * @since 1.2
	 */
	public void setFeaturesToEnable(List<Object> featuresToEnable) {
		this.featuresToEnable = featuresToEnable;
	}

	public List<Object> getFeaturesToDisable() {
		return featuresToDisable;
	}

	/**
	 * Set a list of {@link SerializationFeature} or
	 * {@link DeserializationFeature} flags to disable.
	 * 
	 * @param featuresToDisable
	 * @since 1.2
	 */
	public void setFeaturesToDisable(List<Object> featuresToDisable) {
		this.featuresToDisable = featuresToDisable;
	}

}
