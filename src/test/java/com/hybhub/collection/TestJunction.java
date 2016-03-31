package com.hybhub.collection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJunction {

	// Example of a class that have an ID but doesn't use it for equal
	// comparisons
	private static final class CannotChangeMe {
		String id;

		public CannotChangeMe(final String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

	}

	List<CannotChangeMe> list1 = new ArrayList<>();

	List<CannotChangeMe> list2 = new ArrayList<>();;

	@Before
	public void init() {
		list1.add(new CannotChangeMe("l1-1"));
		list1.add(new CannotChangeMe("l1-2"));
		list1.add(new CannotChangeMe("l1-3"));
		list1.add(new CannotChangeMe("l1-4"));

		list2.add(new CannotChangeMe("l2-1"));
		list2.add(new CannotChangeMe("l2-2"));
		list2.add(new CannotChangeMe("l2-3"));
		list2.add(new CannotChangeMe("l2-4"));
		list2.add(new CannotChangeMe("l2-5"));

		final TestJunction.CannotChangeMe ccm1 = new CannotChangeMe("l1/2-1");
		final TestJunction.CannotChangeMe ccm2 = new CannotChangeMe("l1/2-2");
		final TestJunction.CannotChangeMe ccm3 = new CannotChangeMe("l1/2-3");
		list1.add(ccm1);
		list1.add(ccm2);
		list1.add(ccm3);
		list2.add(ccm1);
		list2.add(ccm2);
		list2.add(ccm3);
	}

	@Test
	public void testJunction() {
		final Junction<CannotChangeMe> junction = new Junction<>(
				(final CannotChangeMe ccm1, final CannotChangeMe ccm2) -> ccm1.getId().equals(ccm2.getId()), list1,
				list2);

		Assert.assertEquals(4, junction.getDisjonctionList1().size());
		Assert.assertEquals(5, junction.getDisjonctionList2().size());
		Assert.assertEquals(3, junction.getConjunction().size());
	}
}
