/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Encode binary data into ASCII85 form based on RFC 1924.
 * 
 * @author matt
 * @version 1.0
 */
public class RFC1924OutputStream extends FilterOutputStream {

	static final byte[] ENCODABET = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '!', '#', '$',
			'%', '&', '(', ')', '*', '+', '-', ';', '<', '=', '>', '?', '@', '^', '_', '`', '{', '|',
			'}', '~' };

	static final long[] FACTORS = { 1, // 85^0
			85, // 85^1
			7225, // 85^2
			614125, // 85^3
			52200625 // 85^4
	};

	private final int[] tuple = new int[4];
	private int bIndex = 0;

	/**
	 * Construct with OutputStream to filter.
	 * 
	 * @param out
	 *        the output stream
	 */
	public RFC1924OutputStream(OutputStream out) {
		super(out);
	}

	private void writeTuple() throws IOException {
		int bytes;
		long sum = 0;
		for ( bytes = 0; bytes < bIndex; bytes++ ) {
			sum = (sum << 8) | tuple[bytes];
		}
		if ( bytes == 4 ) {
			for ( int e = 4; e >= 0; e-- ) {
				super.write(ENCODABET[(int) (sum / FACTORS[e])]);
				sum %= FACTORS[e];
			}
			sum = 0;
			bytes = 0;
		} else if ( bytes > 0 ) {
			for ( int e = bytes; e >= 0; e-- ) {
				super.write(ENCODABET[(int) (sum / FACTORS[e])]);
				sum %= FACTORS[e];
			}
		}
	}

	@Override
	public void write(int b) throws IOException {
		tuple[bIndex++] = b & 0xFF;
		if ( bIndex >= tuple.length ) {
			writeTuple();
			bIndex = 0;
		}
	}

	@Override
	public void close() throws IOException {
		if ( bIndex > 0 && bIndex < tuple.length ) {
			writeTuple();
			bIndex = -1;
			flush();
		}
	}

}
