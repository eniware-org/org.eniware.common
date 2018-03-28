/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

/**
 * Implementation of {@link SortDescriptor}.
 * 
 * @version 1.0
 */
public class SimpleSortDescriptor implements SortDescriptor {

	private final String sortKey;
	private final boolean descending;

	/**
	 * Construct with a sort key.
	 * 
	 * Ascending order will be used.
	 * 
	 * @param sortKey
	 *        the sort key
	 */
	public SimpleSortDescriptor(String sortKey) {
		this(sortKey, false);
	}

	public SimpleSortDescriptor(String sortKey, boolean descending) {
		super();
		this.sortKey = sortKey;
		this.descending = descending;
	}

	@Override
	public String getSortKey() {
		return sortKey;
	}

	@Override
	public boolean isDescending() {
		return descending;
	}

}
