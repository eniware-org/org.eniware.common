/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Metadata about a source associated with a node.
 * 
 * @version 1.0
 */
@JsonPropertyOrder({ "created", "updated", "nodeId", "sourceId" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralNodeSourceMetadata extends GeneralSourceMetadata {

	private Long nodeId;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

}
