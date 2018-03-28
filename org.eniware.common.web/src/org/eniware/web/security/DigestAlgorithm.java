/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.security;

/**
 * Supported algorithms for the HTTP {@code Digest} header.
 * 
 * @version 1.0
 * @since 1.11
 */
public enum DigestAlgorithm {

	MD5("md5"),

	SHA1("sha"),

	SHA256("sha-256");

	private String algorithmName;

	private DigestAlgorithm(String name) {
		this.algorithmName = name;
	}

	/**
	 * Get the header algorithm name associated with the digest.
	 * 
	 * @return The name.
	 */
	public String getAlgorithmName() {
		return algorithmName;
	}

	/**
	 * Get a {@code DigestAlgorithm} for a given algorithm name.
	 * 
	 * @param name
	 *        The name to get the instance for.
	 * @return The instance.
	 * @throws IllegalArgumentException
	 *         if the name is not supported
	 */
	public static DigestAlgorithm forAlgorithmName(String name) {
		for ( DigestAlgorithm alg : DigestAlgorithm.values() ) {
			if ( alg.getAlgorithmName().equalsIgnoreCase(name) ) {
				return alg;
			}
		}
		throw new IllegalArgumentException("Algorithm [" + name + "] not supported");
	}

}
