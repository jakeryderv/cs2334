import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RankTest {

	@Test
	void testEnum() {
		assertTrue(Rank.TWO instanceof Enum);
		assertTrue(Rank.THREE instanceof Enum);
		assertTrue(Rank.FOUR instanceof Enum);
		assertTrue(Rank.FIVE instanceof Enum);
		assertTrue(Rank.SIX instanceof Enum);
		assertTrue(Rank.SEVEN instanceof Enum);
		assertTrue(Rank.EIGHT instanceof Enum);
		assertTrue(Rank.NINE instanceof Enum);
		assertTrue(Rank.TEN instanceof Enum);
		assertTrue(Rank.JACK instanceof Enum);
		assertTrue(Rank.QUEEN instanceof Enum);
		assertTrue(Rank.KING instanceof Enum);
		assertTrue(Rank.ACE instanceof Enum);
	}

	@Test
	void testCompareTo() {
		assertTrue(Rank.TWO.compareTo(Rank.THREE) < 0);
		assertTrue(Rank.THREE.compareTo(Rank.FOUR) < 0);
		assertTrue(Rank.FOUR.compareTo(Rank.FIVE) < 0);
		assertTrue(Rank.FIVE.compareTo(Rank.SIX) < 0);
		assertTrue(Rank.SIX.compareTo(Rank.SEVEN) < 0);
		assertTrue(Rank.SEVEN.compareTo(Rank.EIGHT) < 0);
		assertTrue(Rank.EIGHT.compareTo(Rank.NINE) < 0);
		assertTrue(Rank.NINE.compareTo(Rank.TEN) < 0);
		assertTrue(Rank.TEN.compareTo(Rank.JACK) < 0);
		assertTrue(Rank.JACK.compareTo(Rank.QUEEN) < 0);
		assertTrue(Rank.QUEEN.compareTo(Rank.KING) < 0);
		assertTrue(Rank.KING.compareTo(Rank.ACE) < 0);
	}

	@Test
	void testToString() {
		assertEquals("2", Rank.TWO.toString());
		assertEquals("3", Rank.THREE.toString());
		assertEquals("4", Rank.FOUR.toString());
		assertEquals("5", Rank.FIVE.toString());
		assertEquals("6", Rank.SIX.toString());
		assertEquals("7", Rank.SEVEN.toString());
		assertEquals("8", Rank.EIGHT.toString());
		assertEquals("9", Rank.NINE.toString());
		assertEquals("10", Rank.TEN.toString());
		assertEquals("J", Rank.JACK.toString());
		assertEquals("Q", Rank.QUEEN.toString());
		assertEquals("K", Rank.KING.toString());
		assertEquals("A", Rank.ACE.toString());
	}
}
