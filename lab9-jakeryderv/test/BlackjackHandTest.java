import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class BlackjackHandTest {

	@Test
	@Order(1)
	void testGetCardValues() {
		Map<Rank, Integer> cardValues = BlackjackHand.getCardValues();
		assertEquals(2, cardValues.get(Rank.TWO));
		assertEquals(3, cardValues.get(Rank.THREE));
		assertEquals(4, cardValues.get(Rank.FOUR));
		assertEquals(7, cardValues.get(Rank.SEVEN));
		assertEquals(8, cardValues.get(Rank.EIGHT));
		assertEquals(9, cardValues.get(Rank.NINE));
		assertEquals(10, cardValues.get(Rank.QUEEN));
		assertEquals(10, cardValues.get(Rank.KING));
		assertEquals(11, cardValues.get(Rank.ACE));
		assertEquals(13, cardValues.size());

		// Test encapsulation.
		cardValues.clear();
		cardValues = BlackjackHand.getCardValues();
		assertEquals(4, cardValues.get(Rank.FOUR));
		assertEquals(5, cardValues.get(Rank.FIVE));
		assertEquals(6, cardValues.get(Rank.SIX));
		assertEquals(9, cardValues.get(Rank.NINE));
		assertEquals(10, cardValues.get(Rank.TEN));
		assertEquals(10, cardValues.get(Rank.JACK));
		assertEquals(13, cardValues.size());
	}

	@Test
	void testConstructor() {
		BlackjackHand hand = new BlackjackHand(
				new Card(Rank.TWO, Suit.CLUBS),
				new Card(Rank.THREE, Suit.CLUBS));
		assertEquals(List.of(
				new Card(Rank.TWO, Suit.CLUBS),
				new Card(Rank.THREE, Suit.CLUBS)), hand.getCards());
		assertEquals(2, hand.size());
		assertEquals("[2C, 3C]", hand.toString());
	}

	@Test
	void testCardsEncapsulation() {
		BlackjackHand hand = new BlackjackHand(
				new Card(Rank.SEVEN, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.DIAMONDS));

		List<Card> cards = hand.getCards();
		assertEquals(List.of(
				new Card(Rank.SEVEN, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.DIAMONDS)), cards);
		assertEquals(2, cards.size());
		assertEquals("[7H, 5D]", cards.toString());

		cards.clear();
		cards = hand.getCards();
		assertEquals(List.of(
				new Card(Rank.SEVEN, Suit.HEARTS),
				new Card(Rank.FIVE, Suit.DIAMONDS)), cards);
		assertEquals(2, cards.size());
		assertEquals("[7H, 5D]", cards.toString());
	}

	@Test
	void testAddCard() {
		BlackjackHand hand = new BlackjackHand(
				new Card(Rank.JACK, Suit.DIAMONDS),
				new Card(Rank.TWO, Suit.SPADES));
		assertEquals(List.of(
				new Card(Rank.JACK, Suit.DIAMONDS),
				new Card(Rank.TWO, Suit.SPADES)), hand.getCards());
		assertEquals(2, hand.size());
		assertEquals("[JD, 2S]", hand.toString());

		hand.addCard(new Card(Rank.FOUR, Suit.DIAMONDS));
		assertEquals(List.of(
				new Card(Rank.JACK, Suit.DIAMONDS),
				new Card(Rank.TWO, Suit.SPADES),
				new Card(Rank.FOUR, Suit.DIAMONDS)), hand.getCards());
		assertEquals(3, hand.size());
		assertEquals("[JD, 2S, 4D]", hand.toString());

		hand.addCard(new Card(Rank.FOUR, Suit.SPADES));
		assertEquals(List.of(
				new Card(Rank.JACK, Suit.DIAMONDS),
				new Card(Rank.TWO, Suit.SPADES),
				new Card(Rank.FOUR, Suit.DIAMONDS),
				new Card(Rank.FOUR, Suit.SPADES)), hand.getCards());
		assertEquals(4, hand.size());
		assertEquals("[JD, 2S, 4D, 4S]", hand.toString());
	}

	@Test
	void testGetValue() {
		BlackjackHand hand = new BlackjackHand(
				new Card(Rank.JACK, Suit.HEARTS),
				new Card(Rank.ACE, Suit.SPADES));
		assertEquals("[JH, AS]", hand.toString());
		assertEquals(2, hand.size());
		assertEquals(21, hand.getValue());
		hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
		assertEquals("[JH, AS]", hand.toString());
		assertEquals(2, hand.size());
		assertEquals(21, hand.getValue());

		hand = new BlackjackHand(
				new Card(Rank.KING, Suit.CLUBS),
				new Card(Rank.NINE, Suit.SPADES));
		assertEquals("[KC, 9S]", hand.toString());
		assertEquals(2, hand.size());
		assertEquals(19, hand.getValue());
		hand.addCard(new Card(Rank.THREE, Suit.DIAMONDS));
		assertEquals("[KC, 9S, 3D]", hand.toString());
		assertEquals(3, hand.size());
		assertEquals(22, hand.getValue());
		hand.addCard(new Card(Rank.TWO, Suit.SPADES));
		assertEquals("[KC, 9S, 3D]", hand.toString());
		assertEquals(3, hand.size());
		assertEquals(22, hand.getValue());

		hand = new BlackjackHand(
				new Card(Rank.THREE, Suit.HEARTS),
				new Card(Rank.ACE, Suit.CLUBS));
		assertEquals("[3H, AC]", hand.toString());
		assertEquals(2, hand.size());
		assertEquals(14, hand.getValue());
		hand.addCard(new Card(Rank.ACE, Suit.SPADES));
		assertEquals("[3H, AC, AS]", hand.toString());
		assertEquals(3, hand.size());
		assertEquals(15, hand.getValue());
		hand.addCard(new Card(Rank.FIVE, Suit.DIAMONDS));
		assertEquals("[3H, AC, AS, 5D]", hand.toString());
		assertEquals(4, hand.size());
		assertEquals(20, hand.getValue());
		hand.addCard(new Card(Rank.SIX, Suit.DIAMONDS));
		assertEquals("[3H, AC, AS, 5D, 6D]", hand.toString());
		assertEquals(5, hand.size());
		assertEquals(16, hand.getValue());
		hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
		assertEquals("[3H, AC, AS, 5D, 6D, AH]", hand.toString());
		assertEquals(6, hand.size());
		assertEquals(17, hand.getValue());
		hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
		assertEquals("[3H, AC, AS, 5D, 6D, AH, 2C]", hand.toString());
		assertEquals(7, hand.size());
		assertEquals(19, hand.getValue());
		hand.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		assertEquals("[3H, AC, AS, 5D, 6D, AH, 2C, AD]", hand.toString());
		assertEquals(8, hand.size());
		assertEquals(20, hand.getValue());
		hand.addCard(new Card(Rank.EIGHT, Suit.SPADES));
		assertEquals("[3H, AC, AS, 5D, 6D, AH, 2C, AD, 8S]", hand.toString());
		assertEquals(9, hand.size());
		assertEquals(28, hand.getValue());
		hand.addCard(new Card(Rank.JACK, Suit.HEARTS));
		assertEquals("[3H, AC, AS, 5D, 6D, AH, 2C, AD, 8S]", hand.toString());
		assertEquals(9, hand.size());
		assertEquals(28, hand.getValue());

		hand = new BlackjackHand(
				new Card(Rank.ACE, Suit.CLUBS),
				new Card(Rank.ACE, Suit.DIAMONDS));
		assertEquals("[AC, AD]", hand.toString());
		assertEquals(2, hand.size());
		assertEquals(12, hand.getValue());
		hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
		assertEquals("[AC, AD, 10H]", hand.toString());
		assertEquals(3, hand.size());
		assertEquals(12, hand.getValue());
		hand.addCard(new Card(Rank.EIGHT, Suit.HEARTS));
		assertEquals("[AC, AD, 10H, 8H]", hand.toString());
		assertEquals(4, hand.size());
		assertEquals(20, hand.getValue());
		hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
		assertEquals("[AC, AD, 10H, 8H, AH]", hand.toString());
		assertEquals(5, hand.size());
		assertEquals(21, hand.getValue());
		hand.addCard(new Card(Rank.ACE, Suit.SPADES));
		assertEquals("[AC, AD, 10H, 8H, AH]", hand.toString());
		assertEquals(5, hand.size());
		assertEquals(21, hand.getValue());
	}
}
