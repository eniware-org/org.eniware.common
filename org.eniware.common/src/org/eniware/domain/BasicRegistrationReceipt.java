/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Basic implementation of {@link RegistrationReceipt}.
 * 
 * @author matt
 * @version 1.1
 */
public class BasicRegistrationReceipt implements RegistrationReceipt, Cloneable {

	private static final long serialVersionUID = -8288922092122946581L;

	private String username;
	private String confirmationCode;

	/**
	 * Default constructor.
	 */
	public BasicRegistrationReceipt() {
		this(null, null);
	}

	public BasicRegistrationReceipt(String username, String confirmationCode) {
		super();
		this.username = username;
		this.confirmationCode = confirmationCode;
	}

	@Override
	public String getConfirmationCode() {
		return confirmationCode;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getUsernameURLComponent() {
		String result = getUsername();
		try {
			return (result == null ? null : URLEncoder.encode(result, "UTF-8"));
		} catch ( UnsupportedEncodingException e ) {
			// this should not happen
			throw new RuntimeException("Error encoding username for URL", e);
		}
	}

	@Override
	protected Object clone() {
		try {
			return super.clone();
		} catch ( CloneNotSupportedException e ) {
			// should not get here
			throw new RuntimeException(e);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmationCode == null) ? 0 : confirmationCode.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		BasicRegistrationReceipt other = (BasicRegistrationReceipt) obj;
		if ( confirmationCode == null ) {
			if ( other.confirmationCode != null )
				return false;
		} else if ( !confirmationCode.equals(other.confirmationCode) )
			return false;
		if ( username == null ) {
			if ( other.username != null )
				return false;
		} else if ( !username.equals(other.username) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegistrationReceipt{username=" + username + ",confirmationCode=" + confirmationCode
				+ '}';
	}

	/**
	 * @param username
	 *        the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param confirmationCode
	 *        the confirmationCode to set
	 */
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

}
