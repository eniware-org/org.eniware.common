/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
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
 * @version 1.2
 */
@JsonPropertyOrder({ "i", "a", "s", "t" })
public class GeneralNodeDatumSamples extends GeneralDatumSamples implements Serializable {

	private static final long serialVersionUID = 1412166842747615064L;

}
