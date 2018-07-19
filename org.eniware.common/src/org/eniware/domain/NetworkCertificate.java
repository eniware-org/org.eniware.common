/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

/**
 * API for a network certificate.
 *
 * @version 1.0
 */
public interface NetworkCertificate {

	/**
	 * Get an ID associated with this certificate.
	 * 
	 * @return a unique ID, e.g. Edge ID, never <em>null</em>
	 */
	Long getNetworkId();

	/**
	 * Get a confirmation key, which can be used to later retrieve the network
	 * certificate if not immediately available in
	 * {@link #getNetworkCertificate()}.
	 * 
	 * @return confirmation key, never <em>null</em>
	 */
	String getConfirmationKey();

	/**
	 * Get a status associated with the certificate.
	 * 
	 * @return the status, which may indicate if the certificate is being
	 *         processed, etc
	 */
	String getNetworkCertificateStatus();

	/**
	 * Get the value of the Edge's expected public key certificate subject name.
	 * 
	 * <p>
	 * The Edge must generate a certificate signing request (CSR) using this
	 * subject name and then install the signed certificate when granted by the
	 * EniwareNet certification authority (CA).
	 * </p>
	 * 
	 * @return the Edge's subject DN
	 */
	String getNetworkCertificateSubjectDN();

	/**
	 * Get the certificate, as Base64-encoded string.
	 * 
	 * @return the certificate, or <em>null</em> if the certificate is not
	 *         available yet
	 */
	String getNetworkCertificate();

}
