/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.beans.PropertyEditorSupport;

import org.joda.time.Period;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * PropertyEditor using Joda Time's PeriodFormatter for thread-safe date
 * parsing and formatting.
 * 
 * <p>This class has been designed with {@link CloningPropertyEditorRegistrar}
 * in mind, so that one instance of a {@link JodaPeriodFormatEditor} can be shared
 * between multiple threads to parse or format Joda date objects.</p>
 * 
 * @version $Revision$
 */
public class JodaPeriodFormatEditor extends PropertyEditorSupport
implements Cloneable {

	private PeriodFormatter[] periodFormatters = null;

	/**
	 * Default constructor.
	 */
	public JodaPeriodFormatEditor() {
		super();
		periodFormatters = new PeriodFormatter[]{ISOPeriodFormat.standard()};
	}
	
	@Override
	public String getAsText() {
		Object val = getValue();
		if ( val == null ) {
			return null;
		}
		PeriodFormatter format = this.periodFormatters[0];
		if ( val instanceof ReadablePeriod  ) {
			return format.print((ReadablePeriod)val);
		} 
		throw new IllegalArgumentException("Unsupported period object [" 
				+val.getClass() +"]: " +val);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		IllegalArgumentException iae = null;
		// try patterns one at a time, if all fail to parse then throw exception
		for ( PeriodFormatter pf : this.periodFormatters ) {
			try {
				Period p = pf.parsePeriod(text);
				setValue(p);
				iae = null;
				break;
			} catch ( IllegalArgumentException e ) {
				iae = e;
			}
		}
		if ( iae != null ) {
			throw iae;
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
