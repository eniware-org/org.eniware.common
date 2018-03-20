/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.beans.PropertyEditorSupport;

import org.joda.time.Duration;
import org.joda.time.ReadableDuration;

/**
 * PropertyEditor for Joda Time's Duration objects.
 * 
 * <p>This class has been designed with {@link CloningPropertyEditorRegistrar}
 * in mind, so that one instance of a {@link JodaDurationEditor} can be shared
 * between multiple threads to parse or format Joda date objects.</p>
 * 
 * @author matt
 * @version $Revision$
 */
public class JodaDurationEditor extends PropertyEditorSupport
implements Cloneable {
	
	/**
	 * Default constructor.
	 */
	public JodaDurationEditor() {
		super();
	}
	
	@Override
	public String getAsText() {
		Object val = getValue();
		if ( val == null ) {
			return null;
		}
		if ( val instanceof ReadableDuration  ) {
			return val.toString();
		} 
		throw new IllegalArgumentException("Unsupported duration object [" 
				+val.getClass() +"]: " +val);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			Long ms = Long.valueOf(text);
			setValue(new Duration(ms));
		} catch ( NumberFormatException e ) {
			throw new IllegalArgumentException("Not a valid ms duration");
		}
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch ( CloneNotSupportedException e ) {
			// should never get here
			throw new RuntimeException(e);
		}
	}
	
}
