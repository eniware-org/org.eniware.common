/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Metadata about a source associated with a location.
 * 
 * @author matt
 * @version 1.0
 */
@JsonPropertyOrder({ "created", "updated", "locationId", "sourceId", "location" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralLocationSourceMetadata extends GeneralSourceMetadata {

	private Long locationId;

	private Location location;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Location getLocation() {
		return location;
	}

	@JsonDeserialize(as = BasicLocation.class)
	public void setLocation(Location location) {
		this.location = location;
	}

}
