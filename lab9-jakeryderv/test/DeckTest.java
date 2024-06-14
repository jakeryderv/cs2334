import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

class DeckTest {

	@Test
	void testDraw() {
		Deck deck = new Deck();
		assertEquals(52, deck.size());
		assertEquals("[" +
				"2C, 3C, 4C, 5C, 6C, 7C, 8C, 9C, 10C, JC, QC, KC, AC, " +
				"2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D, JD, QD, KD, AD, " +
				"2H, 3H, 4H, 5H, 6H, 7H, 8H, 9H, 10H, JH, QH, KH, AH, " +
				"2S, 3S, 4S, 5S, 6S, 7S, 8S, 9S, 10S, JS, QS, KS, AS]",
				deck.toString());

		assertEquals(new Card(Rank.TWO, Suit.CLUBS), deck.draw());
		assertEquals(new Card(Rank.THREE, Suit.CLUBS), deck.draw());
		assertEquals(new Card(Rank.FOUR, Suit.CLUBS), deck.draw());
		assertEquals(49, deck.size());
		assertEquals("[" +
				"5C, 6C, 7C, 8C, 9C, 10C, JC, QC, KC, AC, " +
				"2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D, JD, QD, KD, AD, " +
				"2H, 3H, 4H, 5H, 6H, 7H, 8H, 9H, 10H, JH, QH, KH, AH, " +
				"2S, 3S, 4S, 5S, 6S, 7S, 8S, 9S, 10S, JS, QS, KS, AS]",
				deck.toString());

		for (int count = 0; count < 9; ++count) {
			deck.draw();
		}
		assertEquals(new Card(Rank.ACE, Suit.CLUBS), deck.draw());
		assertEquals(new Card(Rank.TWO, Suit.DIAMONDS), deck.draw());
		assertEquals(new Card(Rank.THREE, Suit.DIAMONDS), deck.draw());
		assertEquals(new Card(Rank.FOUR, Suit.DIAMONDS), deck.draw());
		assertEquals(36, deck.size());
		assertEquals("[" +
				"5D, 6D, 7D, 8D, 9D, 10D, JD, QD, KD, AD, " +
				"2H, 3H, 4H, 5H, 6H, 7H, 8H, 9H, 10H, JH, QH, KH, AH, " +
				"2S, 3S, 4S, 5S, 6S, 7S, 8S, 9S, 10S, JS, QS, KS, AS]",
				deck.toString());

		for (int count = 0; count < 20; ++count) {
			deck.draw();
		}
		assertEquals(new Card(Rank.QUEEN, Suit.HEARTS), deck.draw());
		assertEquals(new Card(Rank.KING, Suit.HEARTS), deck.draw());
		assertEquals(new Card(Rank.ACE, Suit.HEARTS), deck.draw());
		assertEquals(new Card(Rank.TWO, Suit.SPADES), deck.draw());
		assertEquals(12, deck.size());
		assertEquals("[3S, 4S, 5S, 6S, 7S, 8S, 9S, 10S, JS, QS, KS, AS]",
				deck.toString());

		for (int count = 0; count < 10; ++count) {
			deck.draw();
		}
		assertEquals(new Card(Rank.KING, Suit.SPADES), deck.draw());
		assertEquals(new Card(Rank.ACE, Suit.SPADES), deck.draw());
		assertEquals(0, deck.size());
		assertEquals("[]", deck.toString());

		assertNull(deck.draw());
		assertNull(deck.draw());
		assertEquals(0, deck.size());
		assertEquals("[]", deck.toString());
	}

	@Test
	void testDrawInt() {
		Deck deck = new Deck();
		assertEquals(52, deck.size());

		assertEquals(List.of(), deck.draw(-54321));
		assertEquals(List.of(), deck.draw(-1));
		assertEquals(List.of(), deck.draw(0));
		assertEquals(52, deck.size());

		assertEquals(List.of(new Card(Rank.TWO, Suit.CLUBS)), deck.draw(1));
		assertEquals(List.of(
				new Card(Rank.THREE, Suit.CLUBS),
				new Card(Rank.FOUR, Suit.CLUBS)),
				deck.draw(2));
		assertEquals(List.of(
				new Card(Rank.FIVE, Suit.CLUBS),
				new Card(Rank.SIX, Suit.CLUBS),
				new Card(Rank.SEVEN, Suit.CLUBS),
				new Card(Rank.EIGHT, Suit.CLUBS),
				new Card(Rank.NINE, Suit.CLUBS)),
				deck.draw(5));
		assertEquals(44, deck.size());

		deck.draw(24);
		assertEquals(List.of(), deck.draw(0));
		assertEquals(List.of(
				new Card(Rank.EIGHT, Suit.HEARTS),
				new Card(Rank.NINE, Suit.HEARTS),
				new Card(Rank.TEN, Suit.HEARTS),
				new Card(Rank.JACK, Suit.HEARTS)),
				deck.draw(4));
		assertEquals(16, deck.size());

		deck.draw(13);
		assertEquals(List.of(
				new Card(Rank.QUEEN, Suit.SPADES),
				new Card(Rank.KING, Suit.SPADES),
				new Card(Rank.ACE, Suit.SPADES)),
				deck.draw(4));
		assertEquals(0, deck.size());

		assertEquals(List.of(), deck.draw(1));
		assertEquals(List.of(), deck.draw(42));
		assertEquals(0, deck.size());
	}

	@Test
	void testShuffle() {
		Deck deck = new Deck();
		String sortedOrder = "[" +
				"2C, 3C, 4C, 5C, 6C, 7C, 8C, 9C, 10C, JC, QC, KC, AC, " +
				"2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D, JD, QD, KD, AD, " +
				"2H, 3H, 4H, 5H, 6H, 7H, 8H, 9H, 10H, JH, QH, KH, AH, " +
				"2S, 3S, 4S, 5S, 6S, 7S, 8S, 9S, 10S, JS, QS, KS, AS]";
		assertEquals(sortedOrder, deck.toString());
		assertEquals(52, deck.size());

		deck.shuffle();
		String shuffledOrder1 = deck.toString();
		assertNotEquals(sortedOrder, shuffledOrder1);
		assertEquals(52, deck.size());

		deck.shuffle();
		String shuffledOrder2 = deck.toString();
		assertNotEquals(sortedOrder, shuffledOrder2);
		assertNotEquals(shuffledOrder1, shuffledOrder2);
		assertEquals(52, deck.size());
	}

	@Test 
	void testGetCardsByRank() {
		Deck deck = new Deck();
		List<Card> kings = deck.getCardsByRank(Rank.KING);
		assertEquals(4, kings.size());
		for (Card crd: kings) {
			assertEquals(crd.getRank(),Rank.KING);
		}
		
		for (int i = 0 ; i < kings.size(); i++) {
			for (int j = 0; j < kings.size(); j++) {
			
				if (i != j) {
					assertNotEquals(kings.get(i).getSuit(), kings.get(j).getSuit());
				}
				
			}
		}
		
		List<Card> aces = deck.getCardsByRank(Rank.ACE);
		assertEquals(4, aces.size());
		for (Card crd: aces) {
			assertEquals(crd.getRank(),Rank.ACE);
		}
		
		for (int i = 0 ; i < aces.size(); i++) {
			for (int j = 0; j < aces.size(); j++) {
				if (i != j) {
					assertNotEquals(aces.get(i).getSuit(), aces.get(j).getSuit());
				}
				
			}
		}
	}
}
