import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class UniqueListTest {

	@Test
	void testInheritance() {
		IntegerList list = new UniqueList();
		assertTrue(list instanceof IntegerList);
		assertTrue(list instanceof UniqueList);
	}
	
	@Test
	void testConstructors() {
		IntegerList list = new UniqueList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		try {
			list = new UniqueList(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The capacity cannot be less than 2.", e.getMessage());
		}
		
		list = new UniqueList(3);
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
	}
	
	@Test
	void testAdd() {
		IntegerList list = new UniqueList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		list.add(5);
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		assertEquals("[5]", list.toString());
		
		try {
			list.add(5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The integer 5 is already in the list.", 
					e.getMessage());
		}
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		assertEquals("[5]", list.toString());
		
		list.add(6);
		assertEquals(2, list.size());
		assertEquals(5, list.get(0));
		assertEquals(6, list.get(1));
		assertEquals("[5, 6]", list.toString());
		
		list.add(7);
		assertEquals(3, list.size());
		assertEquals(5, list.get(0));
		assertEquals(6, list.get(1));
		assertEquals(7, list.get(2));
		assertEquals("[5, 6, 7]", list.toString());
		
		try {
			list.add(6);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The integer 6 is already in the list.", 
					e.getMessage());
		}
		assertEquals(3, list.size());
		assertEquals(5, list.get(0));
		assertEquals(6, list.get(1));
		assertEquals(7, list.get(2));
		assertEquals("[5, 6, 7]", list.toString());
	}
	
	@Test
	void testInsert() {
		IntegerList list = new UniqueList();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
		list.insert(0, 7);
		assertEquals(1, list.size());
		assertEquals(7, list.get(0));
		assertEquals("[7]", list.toString());
		
		list.insert(0, 5);
		assertEquals(2, list.size());
		assertEquals(5, list.get(0));
		assertEquals(7, list.get(1));
		assertEquals("[5, 7]", list.toString());
		
		try {
			list.insert(1, 7);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The integer 7 is already in the list.", 
					e.getMessage());
		}
		assertEquals(2, list.size());
		assertEquals(5, list.get(0));
		assertEquals(7, list.get(1));
		assertEquals("[5, 7]", list.toString());
		
		list.insert(1, 6);
		assertEquals(3, list.size());
		assertEquals(5, list.get(0));
		assertEquals(6, list.get(1));
		assertEquals(7, list.get(2));
		assertEquals("[5, 6, 7]", list.toString());
		
		try {
			list.insert(4, 8);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The index is outside the range [0, 3].", 
					e.getMessage());
		}
		assertEquals(3, list.size());
		assertEquals(5, list.get(0));
		assertEquals(6, list.get(1));
		assertEquals(7, list.get(2));
		assertEquals("[5, 6, 7]", list.toString());
		
		list.insert(3, 8);
		assertEquals(4, list.size());
		assertEquals(5, list.get(0));
		assertEquals(6, list.get(1));
		assertEquals(7, list.get(2));
		assertEquals(8, list.get(3));
		assertEquals("[5, 6, 7, 8]", list.toString());
	}
}
