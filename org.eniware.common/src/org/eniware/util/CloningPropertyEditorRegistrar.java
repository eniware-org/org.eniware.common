/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.beans.PropertyEditor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * PropertyEditorRegistrar implementation that creates new PropertyEditor instances
 * by cloning the ones configured on this class.
 * 
 * <p>This implementation makes it easy to cofnigure PropertyEditors that are wired up
 * to Spring components but need to be used in a thread-safe manner. Each PropertyEditor
 * must provide a no-argument <code>clone()</code> method to create fully-configured
 * copy of itself.</p>
 * 
 * <p>The configurable properties of this class are:</p>
 * 
 * <dl class="class-properties">
 *   <dt>propertyEditors</dt>
 *   <dd>Map of property names to associated PropertyEditor instances.</dd>
 *   
 *   <dt>classEditors</dt>
 *   <dd>Map of Class objects to associated PropertyEditor instances.</dd>
 *   
 *   <dt>lenient</dt>
 *   <dd>If <em>true</em> (the default) then if a configured PropertyEditor does
 *   not provide a public {@code clone()} method, this will configure the 
 *   PropertyEditor as-is, without creating a new copy. If <em>false</em> then
 *   a RuntimeException will be thrown if this situation is encountered.</dd>
 * </dl>
 *
 * @author matt
 * @version $Revision$ $Date$
 */
public class CloningPropertyEditorRegistrar implements PropertyEditorRegistrar {

	private Map<String, PropertyEditor> propertyEditors = null;
	private Map<Class<?>, PropertyEditor> classEditors = null;
	private boolean lenient = true;
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.PropertyEditorRegistrar#registerCustomEditors(org.springframework.beans.PropertyEditorRegistry)
	 */
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		if ( propertyEditors != null ) {
			for ( Map.Entry<String, PropertyEditor> me : propertyEditors.entrySet() ) {
				registerEditor(registry, me);
			}
		}
		if ( classEditors != null ) {
			for ( Map.Entry<Class<?>, PropertyEditor> me : classEditors.entrySet() ) {
				registerEditor(registry, me);
			}
		}
	}

	/**
	 * Set a {@link PropertyEditor} for a specific key.
	 * 
	 * @param key the key (property name)
	 * @param editor the associated editor
	 */
	public void setPropertyEditor(String key, PropertyEditor editor) {
		if ( this.propertyEditors == null ) {
			this.propertyEditors = new LinkedHashMap<String, PropertyEditor>();
		}
		this.propertyEditors.put(key, editor);
	}
	
	/**
	 * Set a {@link PropertyEditor} for a specific Class.
	 * 
	 * @param clazz the Class
	 * @param editor the associated editor
	 */
	public void setPropertyEditor(Class<?> clazz, PropertyEditor editor) {
		if ( this.classEditors == null ) {
			this.classEditors = new LinkedHashMap<Class<?>, PropertyEditor>();
		}
		this.classEditors.put(clazz, editor);
	}
	
	private void registerEditor(PropertyEditorRegistry registry,
			Map.Entry<?, PropertyEditor> me) {
		PropertyEditor ed = me.getValue();
		Object key = me.getKey();
		try {
			Method m = ed.getClass().getMethod("clone", (Class[])null);
			PropertyEditor copy = (PropertyEditor)m.invoke(ed, (Object[])null);
			registerValue(registry, copy, key);
		} catch ( NoSuchMethodException e ) {
			if ( lenient ) {
				// fall back to non-cloned copy, assume it's thread safe!
				registerValue(registry, ed, key);
			} else {
				throw new RuntimeException(e);
			}
		} catch ( IllegalArgumentException e ) {
			throw new RuntimeException(e);
		} catch ( IllegalAccessException e ) {
			throw new RuntimeException(e);
		} catch ( InvocationTargetException e ) {
			throw new RuntimeException(e);
		}
	}

	private void registerValue(PropertyEditorRegistry registry,
			PropertyEditor copy, Object key) {
		if ( key instanceof Class<?> ) {
			registry.registerCustomEditor((Class<?>)key, null, copy);
		} else {
			// assume key is String
			registry.registerCustomEditor(null, (String)key, copy);
		}
	}
	
	/**
	 * @return the propertyEditors
	 */
	public Map<String, PropertyEditor> getPropertyEditors() {
		return propertyEditors;
	}
	
	/**
	 * @param propertyEditors the propertyEditors to set
	 */
	public void setPropertyEditors(Map<String, PropertyEditor> propertyEditors) {
		this.propertyEditors = propertyEditors;
	}
	
	/**
	 * @return the lenient
	 */
	public boolean isLenient() {
		return lenient;
	}
	
	/**
	 * @param lenient the lenient to set
	 */
	public void setLenient(boolean lenient) {
		this.lenient = lenient;
	}
	
	/**
	 * @return the classEditors
	 */
	public Map<Class<?>, PropertyEditor> getClassEditors() {
		return classEditors;
	}
	
	/**
	 * @param classEditors the classEditors to set
	 */
	public void setClassEditors(Map<Class<?>, PropertyEditor> classEditors) {
		this.classEditors = classEditors;
	}

}
