/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.domain;

/**
 * Mutable implementation of {@link SortDescriptor}.
 * 
 * The {@code descending} property defaults to <em>false</em>.
 * 
 * @version 1.0
 */
public class MutableSortDescriptor implements SortDescriptor {

	private String sortKey;
	private boolean descending;

	/**
	 * Construct with ascending order.
	 */
	public MutableSortDescriptor() {
		super();
		this.descending = false;
	}

	/**
	 * Construct with a sort key.
	 * 
	 * Ascending order will be used.
	 * 
	 * @param sortKey
	 *        the sort key
	 */
	public MutableSortDescriptor(String sortKey) {
		this(sortKey, false);
	}

	/**
	 * Construct with a sort key and order.
	 * 
	 * @param sortKey
	 *        the sort key
	 * @param descending
	 *        {@code true} to sort in descending order, {@code false} for
	 *        ascending
	 */
	public MutableSortDescriptor(String sortKey, boolean descending) {
		super();
		this.sortKey = sortKey;
		this.descending = descending;
	}

	@Override
	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	@Override
	public boolean isDescending() {
		return descending;
	}

	public void setDescending(boolean descending) {
		this.descending = descending;
	}

}
