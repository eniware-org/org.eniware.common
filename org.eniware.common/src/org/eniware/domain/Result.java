/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * A simple service result envelope object.
 *
 * @version 1.1
 * @param <T>
 *        the object type
 */
@JsonPropertyOrder({ "success", "code", "message", "data" })
public class Result<T> {

	private final Boolean success;
	private final String code;
	private final String message;
	private final T data;

	/**
	 * Construct a successful response with no data.
	 */
	public Result() {
		this(Boolean.TRUE, null, null, null);
	}

	/**
	 * Construct a successful response with just data.
	 * 
	 * @param data
	 *        the data
	 */
	public Result(T data) {
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
	public Result(Boolean success, String code, String message, T data) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * Helper method to construct instance using generic return type inference.
	 * If you import this static method, then in your code you can write
	 * {@code return result(myData)} instead of
	 * {@code new Result&lt;Object&gt;(myData)}.
	 * 
	 * @param data
	 *        the data
	 * @return the response
	 */
	public static <V> Result<V> result(V data) {
		return new Result<V>(data);
	}

	public Boolean getSuccess() {
		return success;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

}
