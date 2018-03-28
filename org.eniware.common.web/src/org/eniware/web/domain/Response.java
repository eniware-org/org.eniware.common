/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.web.domain;

/**
 * A simple service response envelope object.
 *
 * @version 1.1
 * @param <T>
 *        the object type
 */
public class Response<T> extends org.eniware.domain.Result<T> {

	/**
	 * Construct a successful response with no data.
	 */
	public Response() {
		this(Boolean.TRUE, null, null, null);
	}

	/**
	 * Construct a successful response with just data.
	 * 
	 * @param data
	 *        the data
	 */
	public Response(T data) {
		this(Boolean.TRUE, null, null, data);
	}

	/**
	 * Constructor.
	 * 
	 * @param success
	 *        flag of success
	 * @param code
	 *        optional code, e.g. error code
	 * @param message
	 *        optional descriptive message
	 * @param data
	 *        optional data in the response
	 */
	public Response(Boolean success, String code, String message, T data) {
		super(success, code, message, data);
	}

	/**
	 * Helper method to construct instance using generic return type inference.
	 * If you import this static method, then in your code you can write
	 * {@code return response(myData)} instead of
	 * {@code new Response&lt;Object&gt;(myData)}.
	 * 
	 * @param data
	 *        the data
	 * @return the response
	 */
	public static <V> Response<V> response(V data) {
		return new Response<V>(data);
	}

}
