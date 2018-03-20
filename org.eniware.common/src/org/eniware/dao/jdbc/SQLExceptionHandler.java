/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A handler for SQL exceptions.
 * 
 * @author matt
 * @version 1.0
 */
public interface SQLExceptionHandler {

	/**
	 * Handle an exception triggered when a connection cannot be obtained.
	 * 
	 * @param e
	 *        The exception to handle.
	 */
	void handleGetConnectionException(SQLException e);

	/**
	 * Handle an exception triggered on an active Connection.
	 * 
	 * @param conn
	 *        The {@code Connection} the exception occurred on.
	 * @param e
	 *        The exception.
	 */
	void handleConnectionException(Connection conn, SQLException e);

}
