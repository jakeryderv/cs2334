import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Objects;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void testConstructor() {
		Card card = new Card(Rank.SEVEN, Suit.CLUBS);
		assertEquals(Rank.SEVEN, card.getRank());
		assertEquals(Suit.CLUBS, card.getSuit());

		card = new Card(Rank.QUEEN, Suit.SPADES);
		assertEquals(Rank.QUEEN, card.getRank());
		assertEquals(Suit.SPADES, card.getSuit());

		try {
			new Card(null, Suit.HEARTS);
			fail();
		} catch (NullPointerException e) {}

		try {
			new Card(Rank.NINE, null);
			fail();
		} catch (NullPointerException e) {}

		try {
			new Card(null, null);
			fail();
		} catch (NullPointerException e) {}
	}

	@Test
	void testToString() {
		Card card = new Card(Rank.KING, Suit.HEARTS);
		assertEquals("KH", card.toString());

		card = new Card(Rank.TEN, Suit.DIAMONDS);
		assertEquals("10D", card.toString());

		card = new Card(Rank.FOUR, Suit.SPADES);
		assertEquals("4S", card.toString());
	}

	@Test
	void testCompareTo() {
		Card c1 = new Card(Rank.FIVE, Suit.DIAMONDS);
		Card c2 = new Card(Rank.FIVE, Suit.DIAMONDS);
		assertTrue(c1.compareTo(c1) == 0);
		assertTrue(c2.compareTo(c2) == 0);
		assertTrue(c1.compareTo(c2) == 0);
		assertTrue(c2.compareTo(c1) == 0);

		c1 = new Card(Rank.FIVE, Suit.CLUBS);
		assertTrue(c1.compareTo(c1) == 0);
		assertTrue(c1.compareTo(c2) < 0);
		assertTrue(c2.compareTo(c1) > 0);

		c1 = new Card(Rank.FIVE, Suit.HEARTS);
		assertTrue(c1.compareTo(c1) == 0);
		assertTrue(c1.compareTo(c2) > 0);
		assertTrue(c2.compareTo(c1) < 0);

		c1 = new Card(Rank.FOUR, Suit.DIAMONDS);
		assertTrue(c1.compareTo(c1) == 0);
		assertTrue(c1.compareTo(c2) < 0);
		assertTrue(c2.compareTo(c1) > 0);

		c1 = new Card(Rank.SIX, Suit.DIAMONDS);
		assertTrue(c1.compareTo(c1) == 0);
		assertTrue(c1.compareTo(c2) > 0);
		assertTrue(c2.compareTo(c1) < 0);

		c1 = new Card(Rank.TWO, Suit.CLUBS);
		c2 = new Card(Rank.ACE, Suit.SPADES);
		assertTrue(c1.compareTo(c2) < 0);
		assertTrue(c2.compareTo(c1) > 0);

		c1 = new Card(Rank.ACE, Suit.CLUBS);
		c2 = new Card(Rank.TWO, Suit.SPADES);
		assertTrue(c1.compareTo(c2) < 0);
		assertTrue(c2.compareTo(c1) > 0);
	}

	@Test
	void testEquals() {
		Card c1 = new Card(Rank.SIX, Suit.DIAMONDS);
		assertTrue(c1.equals(c1));
		assertFalse(c1.equals(null));
		assertFalse(c1.equals("6D"));

		Card c2 = new Card(Rank.SIX, Suit.DIAMONDS);
		assertTrue(c2.equals(c1));
		assertTrue(c1.equals(c2));

		c2 = new Card(Rank.SIX, Suit.HEARTS);
		assertFalse(c2.equals(c1));
		assertFalse(c1.equals(c2));

		c2 = new Card(Rank.EIGHT, Suit.DIAMONDS);
		assertFalse(c2.equals(c1));
		assertFalse(c1.equals(c2));
	}

	@Test
	void testHashCode() {
		Card c1 = new Card(Rank.THREE, Suit.CLUBS);
		Card c2 = new Card(Rank.THREE, Suit.CLUBS);
		assertEquals(Objects.hash(Rank.THREE, Suit.CLUBS), c1.hashCode());
		assertEquals(c1.hashCode(), c2.hashCode());

		c2 = new Card(Rank.QUEEN, Suit.HEARTS);
		assertEquals(Objects.hash(Rank.QUEEN, Suit.HEARTS), c2.hashCode());
		assertNotEquals(c1.hashCode(), c2.hashCode());
	}
}
