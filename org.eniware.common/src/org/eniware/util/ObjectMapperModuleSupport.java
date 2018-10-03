/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.List;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Helper class for {@link ObjectMapper} configuration.
 * 
 * @version 1.0
 */
public class ObjectMapperModuleSupport extends SimpleObjectMapperService {

	private String moduleName = "EniwareNetworkModule";
	private Version moduleVersion = new Version(1, 0, 0, null, null, null);
	private List<JsonSerializer<?>> serializers;
	private List<JsonDeserializer<?>> deserializers;
	private List<TypedKeyDeserializer> keyDeserializers;
	private List<JsonSerializer<?>> keySerializers;

	/**
	 * Helper method for registering {@link JsonDeserializer} instances of
	 * unknown types at runtime, ignoring compiler warnings.
	 * 
	 * @param module
	 *        The module to register with.
	 * @param deserializer
	 *        A {@link JsonDeserializer} instance.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void registerDeserializer(SimpleModule module, JsonDeserializer deserializer) {
		Class deserType = deserializer.handledType();
		module.addDeserializer(deserType, deserializer);
	}

	/**
	 * Helper method for registering key {@link JsonSerializer} instances of
	 * unknown types at runtime, ignoring compiler warnings.
	 * 
	 * @param module
	 *        The module to register with.
	 * @param serializer
	 *        A {@link JsonSerializer} instance.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void registerKeySerializer(SimpleModule module, JsonSerializer serializer) {
		Class serType = serializer.handledType();
		module.addKeySerializer(serType, serializer);
	}

	public String getModuleName() {
		return moduleName;
	}

	/**
	 * Set the module name.
	 * 
	 * @param moduleName
	 *        The module name to set.
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Version getModuleVersion() {
		return moduleVersion;
	}

	/**
	 * Set the module version.
	 * 
	 * @param moduleVersion
	 *        The version to set.
	 * @see #setVersion(String)
	 */
	public void setModuleVersion(Version moduleVersion) {
		this.moduleVersion = moduleVersion;
	}

	/**
	 * Set the module version as a period-delimited version string, e.g.
	 * {@code 1.0.0}.
	 * 
	 * @param versionString
	 *        The version string to set.
	 * @see #setModuleVersion(Version)
	 */
	public void setVersion(String versionString) {
		String[] a = StringUtils.split(versionString, ".");
		int v1 = 1, v2 = 0, v3 = 0;
		String s1 = null, s2 = null, s3 = null;
		int i;
		for ( i = 0; i < 3 && i < a.length; i++ ) {
			int v = Integer.parseInt(a[i]);
			switch (i) {
				case 0:
					v1 = v;
					break;
				case 1:
					v2 = v;
					break;
				case 2:
					v3 = v;
					break;
			}
		}
		for ( ; i < 6 && i < a.length; i++ ) {
			switch (i) {
				case 3:
					s1 = a[i];
					break;
				case 4:
					s2 = a[i];
					break;
				case 5:
					s3 = a[i];
					break;
			}
		}
		setModuleVersion(new Version(v1, v2, v3, s1, s2, s3));
	}

	public List<JsonSerializer<?>> getSerializers() {
		return serializers;
	}

	/**
	 * Set a list of {@link JsonSerializer} objects to configure on the module.
	 * 
	 * @param serializers
	 *        the serializers
	 */
	public void setSerializers(List<JsonSerializer<?>> serializers) {
		this.serializers = serializers;
	}

	public List<JsonDeserializer<?>> getDeserializers() {
		return deserializers;
	}

	/**
	 * Set a list of {@link JsonDeserializer} objects to configure on the
	 * module.
	 * 
	 * @param deserializers
	 *        the deserializers
	 */
	public void setDeserializers(List<JsonDeserializer<?>> deserializers) {
		this.deserializers = deserializers;
	}

	/**
	 * Get the configured list of {@link KeyDeserializer} objects to register.
	 * 
	 * @return List of key deserializers.
	 */
	public List<TypedKeyDeserializer> getKeyDeserializers() {
		return keyDeserializers;
	}

	/**
	 * Set a list of {@link TypedKeyDeserializer} objects.
	 * 
	 * @param keyDeserializers
	 *        The key deserializers.
	 */
	public void setKeyDeserializers(List<TypedKeyDeserializer> keyDeserializers) {
		this.keyDeserializers = keyDeserializers;
	}

	public List<JsonSerializer<?>> getKeySerializers() {
		return keySerializers;
	}

	/**
	 * Set a list of key serializers to register.
	 * 
	 * @param keySerializers
	 *        The key serializers to register.
	 */
	public void setKeySerializers(List<JsonSerializer<?>> keySerializers) {
		this.keySerializers = keySerializers;
	}

}
