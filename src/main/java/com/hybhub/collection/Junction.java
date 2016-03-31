package com.hybhub.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiPredicate;

/**
 * Find junctions for java.util.Collection of classes that are final and don't
 * implement a equal method.
 *
 * You can find 2 types of junctions :
 * <ul>
 * <li>Conjunction, objects present in both collections</li>
 * <li>Disjunction, objects present only in one of the 2 collections</li>
 * </ul>
 *
 * @author b.vanalderweireldt
 *
 * @param <T>
 *            The type of object the Junction will handle
 */
public class Junction<T> {

	/**
	 * The functional interface java.util.function.BiPredicate that will handle
	 * the equal test between objects
	 */
	private final BiPredicate<T, T> equal;

	/**
	 * First java.util.Collection
	 */
	private final Collection<T> list1;
	/**
	 * Second java.util.Collection
	 */
	private final Collection<T> list2;

	/**
	 * Construct a new Junction
	 *
	 * @param equal
	 * @param list1
	 * @param list2
	 */
	public Junction(final BiPredicate<T, T> equal, final Collection<T> list1, final Collection<T> list2) {
		super();
		this.equal = equal;
		this.list1 = list1;
		this.list2 = list2;
	}

	/**
	 * Return a list of element present in list1 and list2
	 *
	 * @return a java.util.Collection of type T
	 */
	public Collection<T> getConjunction() {
		final Collection<T> conjunction = new ArrayList<>();
		for (final T t1 : list1) {
			if (list2.stream().anyMatch((final T t2) -> equal.test(t1, t2))) {
				conjunction.add(t1);
			}
		}
		return conjunction;
	}

	/**
	 * Return a list of element present only in list1
	 *
	 * @return a java.util.Collection of type T
	 */
	public Collection<T> getDisjonctionList1() {
		return this.getDisjonction(list1, list2);
	}

	/**
	 * Return a list of element present only in list2
	 *
	 * @return a java.util.Collection of type T
	 */
	public Collection<T> getDisjonctionList2() {
		return this.getDisjonction(list2, list1);
	}

	/**
	 * Return a list of element present only in @param l1
	 *
	 * @param l1
	 * @param l2
	 * @return a java.util.Collection of type T
	 */
	private Collection<T> getDisjonction(final Collection<T> l1, final Collection<T> l2) {
		final Collection<T> disjonction = new ArrayList<>();
		for (final T t1 : l1) {
			if (!l2.stream().anyMatch((final T t2) -> equal.test(t1, t2))) {
				disjonction.add(t1);
			}
		}
		return disjonction;
	}
}