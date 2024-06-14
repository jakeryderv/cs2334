import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SuitTest {

	void testEnum() {
		assertTrue(Suit.CLUBS instanceof Enum);
		assertTrue(Suit.DIAMONDS instanceof Enum);
		assertTrue(Suit.HEARTS instanceof Enum);
		assertTrue(Suit.SPADES instanceof Enum);
	}

	@Test
	void testCompareTo() {
		assertTrue(Suit.CLUBS.compareTo(Suit.DIAMONDS) < 0);
		assertTrue(Suit.DIAMONDS.compareTo(Suit.HEARTS) < 0);
		assertTrue(Suit.HEARTS.compareTo(Suit.SPADES) < 0);
	}

	@Test
	void testToString() {
		assertEquals("C", Suit.CLUBS.toString());
		assertEquals("D", Suit.DIAMONDS.toString());
		assertEquals("H", Suit.HEARTS.toString());
		assertEquals("S", Suit.SPADES.toString());
	}
}
