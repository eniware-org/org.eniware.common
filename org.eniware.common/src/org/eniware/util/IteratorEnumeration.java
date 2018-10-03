/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Utility that adapts an {@link Iterator} into an {@link Enumeration}.
 * 
 * <p>
 * This is useful when dealing with older APIs that still rely on
 * {@link Enumeration}, such as the Servlet API.
 * </p>
 * 
 * @version 1.0
 * @since 1.41
 */
public class IteratorEnumeration<E> implements Enumeration<E> {

	private final Iterator<E> iterator;

	public IteratorEnumeration(Iterator<E> iterator) {
		this.iterator = iterator;
	}

	@Override
	public E nextElement() {
		return iterator.next();
	}

	@Override
	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

}
