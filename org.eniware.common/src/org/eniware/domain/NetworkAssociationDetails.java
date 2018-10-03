/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Command object for initial EniwareEdge and EniwareNet association data.
 * 
 * @version 1.1
 */
public class NetworkAssociationDetails extends BasicNetworkIdentity implements NetworkAssociation,
		NetworkCertificate, Serializable {

	private static final long serialVersionUID = -6264228260215100345L;

	private String confirmationKey;
	private String username;
	private Date expiration;
	private String securityPhrase;
	private Long networkId;
	private String networkCertificate;
	private String networkCertificateStatus;
	private String networkCertificateSubjectDN;
	private String keystorePassword;

	/**
	 * Default constructor.
	 */
	public NetworkAssociationDetails() {
		super();
	}

	/**
	 * Copy constructor.
	 * 
	 * @param other
	 *        the NetworkAssociation to copy
	 */
	public NetworkAssociationDetails(NetworkAssociation other) {
		super();
		setConfirmationKey(other.getConfirmationKey());
		setHost(other.getHost());
		setIdentityKey(other.getIdentityKey());
		setPort(other.getPort());
		setSecurityPhrase(other.getSecurityPhrase());
		setTermsOfService(other.getTermsOfService());
		setForceTLS(other.isForceTLS());
	}

	/**
	 * Construct with association details.
	 * 
	 * @param username
	 *        the username
	 * @param confirmationKey
	 *        the confirmation key
	 * @param keystorePassword
	 *        the keystore password
	 * @since 1.1
	 */
	public NetworkAssociationDetails(String username, String confirmationKey, String keystorePassword) {
		super();
		setUsername(username);
		setConfirmationKey(confirmationKey);
		setKeystorePassword(keystorePassword);
	}

	@Override
	public String toString() {
		return "NetworkAssociationDetails{host=" + getHost() + ",username=" + username + ",networkId="
				+ networkId + '}';
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	@Override
	public String getConfirmationKey() {
		return confirmationKey;
	}

	public void setConfirmationKey(String confirmationKey) {
		this.confirmationKey = confirmationKey;
	}

	@Override
	public String getSecurityPhrase() {
		return securityPhrase;
	}

	public void setSecurityPhrase(String secretPhrase) {
		this.securityPhrase = secretPhrase;
	}

	@Override
	public Long getNetworkId() {
		return networkId;
	}

	public void setNetworkId(Long networkId) {
		this.networkId = networkId;
	}

	@Override
	public String getNetworkCertificate() {
		return networkCertificate;
	}

	public void setNetworkCertificate(String networkCertificate) {
		this.networkCertificate = networkCertificate;
	}

	@Override
	public String getNetworkCertificateStatus() {
		return networkCertificateStatus;
	}

	public void setNetworkCertificateStatus(String networkCertificateStatus) {
		this.networkCertificateStatus = networkCertificateStatus;
	}

	@Override
	public String getNetworkCertificateSubjectDN() {
		return networkCertificateSubjectDN;
	}

	public void setNetworkCertificateSubjectDN(String networkCertificateSubjectDN) {
		this.networkCertificateSubjectDN = networkCertificateSubjectDN;
	}

	@Override
	public String getKeystorePassword() {
		return keystorePassword;
	}

	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

}
