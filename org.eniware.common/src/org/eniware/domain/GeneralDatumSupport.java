/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eniware.util.SerializeIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Supporting abstract class for general node datum related objects.
 *
 * @version 1.0
 */
public abstract class GeneralDatumSupport implements Serializable {

	private static final long serialVersionUID = -4264640101068495508L;

	private Set<String> tags;

	/**
	 * Get a String value out of a Map. If the key exists but is not a String,
	 * {@link Object#toString()} will be called on that object.
	 * 
	 * @param key
	 *        the key of the object to get
	 * @param map
	 *        the map to inspect, <em>null</em> is allowed
	 * @return the value, or <em>null</em> if not found
	 */
	protected String getMapString(String key, Map<String, ?> map) {
		if ( map == null ) {
			return null;
		}
		Object s = map.get(key);
		if ( s == null ) {
			return null;
		}
		if ( s instanceof String ) {
			return (String) s;
		}
		return s.toString();
	}

	/**
	 * Get an Integer value out of a Map. If the key exists, is not an Integer,
	 * but is a Number, {@link Number#intValue()} will be called on that object.
	 * 
	 * @param key
	 *        the key of the object to get
	 * @param map
	 *        the map to inspect, <em>null</em> is allowed
	 * @return the value, or <em>null</em> if not found
	 */
	protected Integer getMapInteger(String key, Map<String, ?> map) {
		if ( map == null ) {
			return null;
		}
		Object n = map.get(key);
		if ( n == null ) {
			return null;
		}
		if ( n instanceof Integer ) {
			return (Integer) n;
		}
		if ( n instanceof Number ) {
			return ((Number) n).intValue();
		}
		try {
			return Integer.valueOf(n.toString());
		} catch ( NumberFormatException e ) {
			return null;
		}
	}

	/**
	 * Get a Long value out of a Map. If the key exists, is not a Long, but is a
	 * Number, {@link Number#longValue()} will be called on that object.
	 * 
	 * @param key
	 *        the key of the object to get
	 * @param map
	 *        the map to inspect, <em>null</em> is allowed
	 * @return the value, or <em>null</em> if not found
	 */
	protected Long getMapLong(String key, Map<String, ?> map) {
		if ( map == null ) {
			return null;
		}
		Object n = map.get(key);
		if ( n == null ) {
			return null;
		}
		if ( n instanceof Long ) {
			return (Long) n;
		}
		if ( n instanceof Number ) {
			return ((Number) n).longValue();
		}
		try {
			return Long.valueOf(n.toString());
		} catch ( NumberFormatException e ) {
			return null;
		}
	}

	/**
	 * Get a Float value out of a Map. If the key exists, is not a Float, but is
	 * a Number, {@link Number#floatValue()} will be called on that object.
	 * 
	 * @param key
	 *        the key of the object to get
	 * @param map
	 *        the map to inspect, <em>null</em> is allowed
	 * @return the value, or <em>null</em> if not found
	 */
	protected Float getMapFloat(String key, Map<String, ?> map) {
		if ( map == null ) {
			return null;
		}
		Object n = map.get(key);
		if ( n == null ) {
			return null;
		}
		if ( n instanceof Float ) {
			return (Float) n;
		}
		if ( n instanceof Number ) {
			return ((Number) n).floatValue();
		}
		try {
			return Float.valueOf(n.toString());
		} catch ( NumberFormatException e ) {
			return null;
		}
	}

	/**
	 * Get a Double value out of a Map. If the key exists, is not a Double, but
	 * is a Number, {@link Number#doubleValue()} will be called on that object.
	 * 
	 * @param key
	 *        the key of the object to get
	 * @param map
	 *        the map to inspect, <em>null</em> is allowed
	 * @return the value, or <em>null</em> if not found
	 */
	protected Double getMapDouble(String key, Map<String, ?> map) {
		if ( map == null ) {
			return null;
		}
		Object n = map.get(key);
		if ( n == null ) {
			return null;
		}
		if ( n instanceof Double ) {
			return (Double) n;
		}
		if ( n instanceof Number ) {
			return ((Number) n).doubleValue();
		}
		try {
			return Double.valueOf(n.toString());
		} catch ( NumberFormatException e ) {
			return null;
		}
	}

	/**
	 * Get a BigDecimal value out of a Map. If the key exists but is not a
	 * BigDecimal, {@link Object#toString()} will be called on that object and
	 * {@link BigDecimal#BigDecimal(String)} will be returned.
	 * 
	 * @param key
	 *        the key of the object to get
	 * @param map
	 *        the map to inspect, <em>null</em> is allowed
	 * @return the value, or <em>null</em> if not found
	 */
	protected BigDecimal getMapBigDecimal(String key, Map<String, ?> map) {
		if ( map == null ) {
			return null;
		}
		Object n = map.get(key);
		if ( n == null ) {
			return null;
		}
		if ( n instanceof BigDecimal ) {
			return (BigDecimal) n;
		}
		try {
			return new BigDecimal(n.toString());
		} catch ( NumberFormatException e ) {
			return null;
		}
	}

	/**
	 * Get an array of <em>tags</em>.
	 * 
	 * @return array of tags
	 */
	@JsonIgnore
	@SerializeIgnore
	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	/**
	 * Shortcut for {@link #getTags()}.
	 * 
	 * @return map
	 */
	public Set<String> getT() {
		return getTags();
	}

	public void setT(Set<String> set) {
		setTags(set);
	}

	/**
	 * Return <em>true</em> if {@code tags} contains {@code tag}.
	 * 
	 * @param tag
	 *        the tag value to test for existence
	 * @return boolean
	 */
	public boolean hasTag(String tag) {
		return (tags != null && tags.contains(tag));
	}

	/**
	 * Add a tag value.
	 * 
	 * @param tag
	 *        the tag value to add
	 */
	public void addTag(String tag) {
		if ( tag == null ) {
			return;
		}
		Set<String> set = tags;
		if ( set == null ) {
			set = new LinkedHashSet<String>(2);
			tags = set;
		}
		set.add(tag);
	}

	/**
	 * Remove a tag value.
	 * 
	 * @param tag
	 *        the tag value to add
	 */
	public void removeTag(String tag) {
		if ( tag == null ) {
			return;
		}
		Set<String> set = tags;
		if ( set != null ) {
			set.remove(tag);
		}
	}

}
