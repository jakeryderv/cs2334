import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class SortedListTest {
	
	@Test
	void testInheritance() {
		IntegerList list = new SortedList();
		assertTrue(list instanceof IntegerList);
		assertTrue(list instanceof SortedList);
	}
	
	@Test
	void testConstructors() {
		IntegerList list = new SortedList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		try {
			list = new SortedList(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The capacity cannot be less than 2.", e.getMessage());
		}
		
		list = new SortedList(10);
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
	}
	
	@Test
	void testAdd() {
		IntegerList list = new SortedList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		list.add(7);
		assertEquals(1, list.size());
		assertEquals(7, list.get(0));
		assertEquals("[7]", list.toString());
		
		list.add(4);
		assertEquals(2, list.size());
		assertEquals(4, list.get(0));
		assertEquals(7, list.get(1));
		assertEquals("[4, 7]", list.toString());
		
		list.add(9);
		assertEquals(3, list.size());
		assertEquals(4, list.get(0));
		assertEquals(7, list.get(1));
		assertEquals(9, list.get(2));
		assertEquals("[4, 7, 9]", list.toString());
		
		list.add(5);
		assertEquals(4, list.size());
		assertEquals(4, list.get(0));
		assertEquals(5, list.get(1));
		assertEquals(7, list.get(2));
		assertEquals(9, list.get(3));
		assertEquals("[4, 5, 7, 9]", list.toString());
		
		list.add(8);
		assertEquals(5, list.size());
		assertEquals(4, list.get(0));
		assertEquals(5, list.get(1));
		assertEquals(7, list.get(2));
		assertEquals(8, list.get(3));
		assertEquals(9, list.get(4));
		assertEquals("[4, 5, 7, 8, 9]", list.toString());
		
		list.add(6);
		assertEquals(6, list.size());
		assertEquals(4, list.get(0));
		assertEquals(5, list.get(1));
		assertEquals(6, list.get(2));
		assertEquals(7, list.get(3));
		assertEquals(8, list.get(4));
		assertEquals(9, list.get(5));
		assertEquals("[4, 5, 6, 7, 8, 9]", list.toString());
		
		list.add(7);
		assertEquals(7, list.size());
		assertEquals(4, list.get(0));
		assertEquals(5, list.get(1));
		assertEquals(6, list.get(2));
		assertEquals(7, list.get(3));
		assertEquals(7, list.get(4));
		assertEquals(8, list.get(5));
		assertEquals(9, list.get(6));
		assertEquals("[4, 5, 6, 7, 7, 8, 9]", list.toString());
	}
	
	@Test
	void testInsert() {
		IntegerList list = new SortedList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		try {
			list.insert(0, 42);
			fail();
		} catch (UnsupportedOperationException e) {}
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
	}
}
