/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to signal a field or method should not be used
 * during introspection-based serialization or de-serialization.
 * 
 * <p>This is designed to be used during view rendering, for
 * example, where specific properties should not be rendered
 * into the view output format (e.g. JSON, XML, etc).
 * 
 * @author matt
 * @version $Revision$
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SerializeIgnore {
    
	/**
     * Optional argument that defines if this annotation is active.
     */
    boolean value() default true;

}
