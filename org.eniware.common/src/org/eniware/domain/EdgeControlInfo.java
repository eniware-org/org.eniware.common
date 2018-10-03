/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;


/**
 * API for a user-manageable Edge component.
 * 
 * @version $Revision$
 */
public interface EdgeControlInfo {

	String getControlId();
	
	String getPropertyName();
	
	EdgeControlPropertyType getType();
	
	String getValue();
	
	Boolean getReadonly();
	
	String getUnit();

}
