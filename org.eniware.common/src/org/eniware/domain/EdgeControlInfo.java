/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;


/**
 * API for a user-manageable node component.
 * 
 * @version $Revision$
 */
public interface NodeControlInfo {

	String getControlId();
	
	String getPropertyName();
	
	NodeControlPropertyType getType();
	
	String getValue();
	
	Boolean getReadonly();
	
	String getUnit();

}
