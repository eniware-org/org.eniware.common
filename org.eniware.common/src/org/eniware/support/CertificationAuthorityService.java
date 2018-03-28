/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.support;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

/**
 * Certification authority service.
 * 
 * @version 1.0
 */
public interface CertificationAuthorityService {

	/**
	 * Sign a CSR with a given key.
	 * 
	 * @param csr
	 *        the PKCS10 encoded certificate signing request.
	 * @param caCert
	 *        The certification authority certificate to sign the CSR with.
	 * @param privateKey
	 *        The certification authority's private key to sign the CSR with.
	 * @return The generated, signed certificate.
	 * @throws CertificateException
	 *         If any error occurs.
	 */
	X509Certificate signCertificate(String csr, X509Certificate caCert, PrivateKey privateKey)
			throws CertificateException;

	/**
	 * Create a new self-signed certification authority certificate.
	 * 
	 * @param dn
	 *        The distinguished name of the certificate.
	 * @param publicKey
	 *        The public key.
	 * @param privateKey
	 *        The private key.
	 * @return The self-signed certificate, with certification authority
	 *         extensions applied.
	 */
	X509Certificate generateCertificationAuthorityCertificate(String dn, PublicKey publicKey,
			PrivateKey privateKey);
}
