package edu.uta.byos.runtime;


/**
 * **************************** [ ByOS ] *****************************
 * 
 * @Description A solitaire game
 * @Class | CardList | An enum of CardList represents a card with respect
 *  					to its suit-value
 * @has suit {CLUB, DIAMOND, HEART, SPADE)
 * @has value {ACE = 0, TWO = 2, ... KING = 12}
 * @authors ruby_
 * @version 1.0 *******************************************************
 */


public enum CardList {
	// ===========================================================
	// Elements
	// ===========================================================

	CLUB_ACE(Suit.CLUB, Value.ACE), CLUB_TWO(Suit.CLUB, Value.TWO), CLUB_THREE(
			Suit.CLUB, Value.THREE), CLUB_FOUR(Suit.CLUB, Value.FOUR), CLUB_FIVE(
			Suit.CLUB, Value.FIVE), CLUB_SIX(Suit.CLUB, Value.SIX), CLUB_SEVEN(
			Suit.CLUB, Value.SEVEN), CLUB_EIGHT(Suit.CLUB, Value.EIGHT), CLUB_NINE(
			Suit.CLUB, Value.NINE), CLUB_TEN(Suit.CLUB, Value.TEN), CLUB_JACK(
			Suit.CLUB, Value.JACK), CLUB_QUEEN(Suit.CLUB, Value.QUEEN), CLUB_KING(
			Suit.CLUB, Value.KING),

	DIAMOND_ACE(Suit.DIAMOND, Value.ACE), DIAMOND_TWO(Suit.DIAMOND, Value.TWO), DIAMOND_THREE(
			Suit.DIAMOND, Value.THREE), DIAMOND_FOUR(Suit.DIAMOND, Value.FOUR), DIAMOND_FIVE(
			Suit.DIAMOND, Value.FIVE), DIAMOND_SIX(Suit.DIAMOND, Value.SIX), DIAMOND_SEVEN(
			Suit.DIAMOND, Value.SEVEN), DIAMOND_EIGHT(Suit.DIAMOND, Value.EIGHT), DIAMOND_NINE(
			Suit.DIAMOND, Value.NINE), DIAMOND_TEN(Suit.DIAMOND, Value.TEN), DIAMOND_JACK(
			Suit.DIAMOND, Value.JACK), DIAMOND_QUEEN(Suit.DIAMOND, Value.QUEEN), DIAMOND_KING(
			Suit.DIAMOND, Value.KING),

	HEART_ACE(Suit.HEART, Value.ACE), HEART_TWO(Suit.HEART, Value.TWO), HEART_THREE(
			Suit.HEART, Value.THREE), HEART_FOUR(Suit.HEART, Value.FOUR), HEART_FIVE(
			Suit.HEART, Value.FIVE), HEART_SIX(Suit.HEART, Value.SIX), HEART_SEVEN(
			Suit.HEART, Value.SEVEN), HEART_EIGHT(Suit.HEART, Value.EIGHT), HEART_NINE(
			Suit.HEART, Value.NINE), HEART_TEN(Suit.HEART, Value.TEN), HEART_JACK(
			Suit.HEART, Value.JACK), HEART_QUEEN(Suit.HEART, Value.QUEEN), HEART_KING(
			Suit.HEART, Value.KING),

	SPADE_ACE(Suit.SPADE, Value.ACE), SPADE_TWO(Suit.SPADE, Value.TWO), SPADE_THREE(
			Suit.SPADE, Value.THREE), SPADE_FOUR(Suit.SPADE, Value.FOUR), SPADE_FIVE(
			Suit.SPADE, Value.FIVE), SPADE_SIX(Suit.SPADE, Value.SIX), SPADE_SEVEN(
			Suit.SPADE, Value.SEVEN), SPADE_EIGHT(Suit.SPADE, Value.EIGHT), SPADE_NINE(
			Suit.SPADE, Value.NINE), SPADE_TEN(Suit.SPADE, Value.TEN), SPADE_JACK(
			Suit.SPADE, Value.JACK), SPADE_QUEEN(Suit.SPADE, Value.QUEEN), SPADE_KING(
			Suit.SPADE, Value.KING);

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	public final Suit mSuit;
	public final Value mValue;

	// ===========================================================
	// Constructors
	// ===========================================================

	private CardList(final Suit pSuit, final Value pValue) {
		this.mSuit = pSuit;
		this.mValue = pValue;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Suit getSuit() {
		return this.mSuit;
	}

	public Value getValue() {
		return this.mValue;
	}

	public int getTexturePositionX() {
		return this.mValue.ordinal() * Card.CARD_WIDTH;
	}

	public int getTexturePositionY() {
		return this.mSuit.ordinal() * Card.CARD_HEIGHT;
	}

	// ===========================================================
	// Methods from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
