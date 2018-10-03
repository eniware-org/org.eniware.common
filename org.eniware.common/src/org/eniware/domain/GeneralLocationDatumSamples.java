/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * A collection of different types of sample data, grouped by logical sample
 * type.
 * 
 * @version 1.0
 */
@JsonPropertyOrder({ "i", "a", "s", "t" })
public class GeneralLocationDatumSamples extends GeneralDatumSamples implements Serializable {

	private static final long serialVersionUID = 3113752106266124096L;

}
