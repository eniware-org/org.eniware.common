/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Decode RFC 1924 encoded stream back to its original form.
 * 
 * @author matt
 * @version 1.0
 */
public class RFC1924InputStream extends FilterInputStream {

	private static final byte[] DECODABET;

	static {
		DECODABET = new byte[256];
		for ( int i = 0; i < DECODABET.length; ++i ) {
			DECODABET[i] = -1;
		}

		for ( int i = 0; i < RFC1924OutputStream.ENCODABET.length; ++i ) {
			DECODABET[(RFC1924OutputStream.ENCODABET[i] & 0xFF)] = (byte) i;
		}
	}

	private int factor = 5;
	private long sum = 0;
	private int e = 24;
	private boolean eof = false;

	/**
	 * Construct with InputStream to filter.
	 * 
	 * @param in
	 *        the input stream
	 */
	public RFC1924InputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException {
		int b;
		while ( eof == false && factor > 0 ) {
			b = super.read();
			if ( b == -1 ) {
				eof = true;
				break;
			}
			sum += DECODABET[b] * RFC1924OutputStream.FACTORS[--factor];
		}

		if ( factor == 0 && eof == false ) {
			int result = (int) ((sum >>> e) & 0xFF);
			e -= 8;
			if ( e < 0 ) {
				e = 24;
				factor = 5;
				sum = 0;
			}
			return result;
		} else if ( factor < 5 && e >= 0 ) {
			if ( e == 24 ) {
				sum /= RFC1924OutputStream.FACTORS[factor];
				e = (3 - factor) * 8;
			}
			int result = (int) ((sum >>> e) & 0xFF);
			e -= 8;
			return result;
		}
		return -1;
	}

	@Override
	public int read(byte[] b) throws IOException {
		return read(b, 0, b.length);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		if ( eof == true ) {
			return -1;
		}
		int i, d, stop;
		for ( i = off, stop = off + len; i < b.length && i < stop; i++ ) {
			d = read();
			if ( d == -1 ) {
				break;
			}
			b[i] = (byte) d;
		}
		return (i - off);
	}

}
