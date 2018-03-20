/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Joins multiple Iterator instances into a single Iterator.
 * 
 * @param <E>
 *        the element type
 * @author matt
 * @version 1.0
 */
public class UnionIterator<E> implements Iterator<E> {

	private final Collection<Iterator<E>> iterators;

	/**
	 * Construct from a collection of iterators.
	 * 
	 * @param iterators
	 *        the iterators to merge
	 */
	public UnionIterator(Collection<Iterator<E>> iterators) {
		assert iterators != null;
		this.iterators = iterators;
	}

	@Override
	public boolean hasNext() {
		Iterator<Iterator<E>> itr;
		for ( itr = iterators.iterator(); itr.hasNext(); ) {
			Iterator<E> e = itr.next();
			if ( e.hasNext() ) {
				return true;
			}
			finishedIterator(e);
			itr.remove();
		}
		return false;
	}

	protected void finishedIterator(Iterator<E> itr) {
		// extending classes can override
	}

	@Override
	public E next() {
		assert iterators.size() > 0;
		return iterators.iterator().next().next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
