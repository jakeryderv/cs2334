import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class IntegerListTest {

	@Test
	void testConstructors() {
		IntegerList list = new IntegerList();
		assertEquals(0, list.size());
		
		try {
			list = new IntegerList(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The capacity cannot be less than 2.", e.getMessage());
		}
		
		list = new IntegerList(2);
		assertEquals(0, list.size());
	}

	@Test
	void testInvalidGetIndex() {
		IntegerList list = new IntegerList();
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, -1].", 
					e.getMessage());
		}
	}
	
	@Test
	void testAdd() {
		IntegerList list = new IntegerList();
		list.add(2334);
		list.add(-42);
		list.add(31337);
		
		assertEquals(3, list.size());
		assertEquals(2334, list.get(0));
		assertEquals(-42, list.get(1));
		assertEquals(31337, list.get(2));
		
		try {
			list.get(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 2].", 
					e.getMessage());
		}
	}
	
	@Test
	void testContains() {
		IntegerList list = new IntegerList();		
		assertEquals(list.indexOf(-101), -1);
		assertEquals(list.indexOf(451), -1);
		
		list.add(451);
		assertEquals(list.indexOf(-101), -1);
		assertEquals(list.indexOf(451), 0);
		
		list.add(-101);
		assertEquals(list.indexOf(-101),1 );
		assertEquals(list.indexOf(451), 0);
	}
	
	@Test
	void testInsert() {
		IntegerList list = new IntegerList();
		
		try {
			list.insert(1, 2334);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 0].", 
					e.getMessage());
		}
		
		list.insert(0, 2334);
		assertEquals(2334, list.get(0));
		
		try {
			list.insert(-1, 1323);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 1].", 
					e.getMessage());
		}
		
		list.insert(0, 1323);
		assertEquals(1323, list.get(0));
		assertEquals(2334, list.get(1));
		
		try {
			list.insert(3, 1324);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 2].", 
					e.getMessage());
		}
		
		list.insert(1, 1324);
		assertEquals(1323, list.get(0));
		assertEquals(1324, list.get(1));
		assertEquals(2334, list.get(2));
	}
	
	@Test
	void testRemove() {
		IntegerList list = new IntegerList();
		list.add(0);
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(5);
		
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 5].", 
					e.getMessage());
		}
		
		assertEquals(2, list.remove(3));
		assertEquals(5, list.size());
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(1, list.get(2));
		assertEquals(3, list.get(3));
		assertEquals(5, list.get(4));
		
		try {
			list.remove(5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 4].", 
					e.getMessage());
		}
		
		assertEquals(1, list.remove(2));
		assertEquals(3, list.remove(2));
		assertEquals(3, list.size());
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(5, list.get(2));
		
		try {
			list.remove(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 2].", 
					e.getMessage());
		}
	}
	
	@Test
	void testToString() {
		IntegerList list = new IntegerList();
		assertEquals("[]", list.toString());
		
		list.add(360);
		assertEquals("[360]", list.toString());
		
		list.add(180);
		assertEquals("[360, 180]", list.toString());
	}
}
