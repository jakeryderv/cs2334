import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class RangeListTest {

	@Test
	void testInheritance() {
		IntegerList list = new RangeList();
		assertTrue(list instanceof IntegerList);
		assertTrue(list instanceof RangeList);
	}

	@Test
	void testConstructors1() {
		IntegerList list = new RangeList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		
		list = new RangeList(-1, 5);
		assertEquals(7, list.size());
		assertEquals(-1, list.get(0));
		assertEquals(0, list.get(1));
		assertEquals(1, list.get(2));
		assertEquals("[-1, 0, 1, 2, 3, 4, 5]", list.toString());
	}
	
	void testConstructors2() {
		RangeList list = new RangeList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		
		try {
			list = new RangeList(3, -2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The upper bound must be greater than or equal to the lower bound.", 
					e.getMessage());
		}
		
		try {
			list = new RangeList(-2, -6);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The upper bound must be greater than or equal to the lower bound.", 
					e.getMessage());
		}
		
		
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());

	}
	
	@Test
	void testAdd() {
		RangeList list = new RangeList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		try {
			list.add(10, -5);
			fail();
		}catch (IllegalArgumentException e) {
			assertEquals("The upper bound must be greater than or equal to the lower bound.", e.getMessage());
		}
		
		
		list.add(-1, 10);
		assertEquals(12, list.size());
		assertEquals(-1, list.get(0));
		assertEquals(9, list.get(10));
		assertEquals("[-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", list.toString());
		
		list.add(-2, 12);
		assertEquals(15, list.size());
		assertEquals(-2, list.get(0));
		assertEquals(11, list.get(13));
		assertEquals(12, list.get(14));
		assertEquals("[-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]", list.toString());
		
		list.add(5, 13);
		assertEquals(16, list.size());
		assertEquals(13, list.get(15));
		assertEquals("[-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]", list.toString());
		
		list.add(-4, 13);
		assertEquals(18, list.size());
		assertEquals(-4, list.get(0));
		assertEquals(-3, list.get(1));
		assertEquals("[-4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]", list.toString());
		
	
	}
	
	
	@Test
	void testRemove() {
		RangeList list = new RangeList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		list = new RangeList(-5, 10);
		assertEquals(16, list.size());
		
		list.remove(-5, 5);
		assertEquals(5, list.size());
		assertEquals(6, list.get(0));
		assertEquals("[6, 7, 8, 9, 10]", list.toString());
		
		list.remove(10, 10);
		assertEquals(4, list.size());
		assertEquals(6, list.get(0));
		assertEquals(9, list.get(list.size()-1));
		assertEquals("[6, 7, 8, 9]", list.toString());
		
		try {
			list.remove(12,10);
			fail();
		}catch(IllegalArgumentException e) {
			assertEquals("The upper bound must be greater than or equal to the lower bound.", e.getMessage());
		}
		
		try {
			list.remove(10, 13);
			fail();
		}catch (IllegalArgumentException e) {
			assertEquals("Lower and/or upper bounds are out of the current list range.", e.getMessage());
		}
		
		list.remove(6,9);
		assertEquals(0, list.size());
		
		try {
			list.remove(6,9);
			fail();
		}catch (UnsupportedOperationException e) {
			assertEquals("Cannot remove range from an empty list.", e.getMessage());
		}
		
		
		
			
	}

	
	@Test
	void testInsert() {
		RangeList list = new RangeList(5,10);
		
		try {
			list.insert(5, 11);
			fail();
		}catch (UnsupportedOperationException e) {}
	
	}
	
	@Test
	void testAdd2() {
	
		RangeList list = new RangeList(0,2);
		
		try {
			list.add(3);
			fail();
		}catch (UnsupportedOperationException e) {}

	}
}

