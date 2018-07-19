/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Metadata about a source associated with a Edge.
 * 
 * @version 1.0
 */
@JsonPropertyOrder({ "created", "updated", "EdgeId", "sourceId" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralEdgeSourceMetadata extends GeneralSourceMetadata {

	private Long EdgeId;

	public Long getEdgeId() {
		return EdgeId;
	}

	public void setEdgeId(Long EdgeId) {
		this.EdgeId = EdgeId;
	}

}
