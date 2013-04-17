package edu.uta.byos.runtime;

import java.util.ArrayList;

import edu.uta.byos.runtime.Card;

/**
* **************************** [ ByOS ] *****************************
* @Description A solitaire Game
* @class    | Anchor
*           | Designed for dealing with cards in piles on tableau
* @authors ruby_
* @version 1.0
* *******************************************************************
*/


public abstract class Anchor extends ArrayList<Card> {
    // -------------------------------
    // Fields
    // -------------------------------

	static final long serialVersionUID = 1L;
	protected float mAnchorX;
    protected float mAnchorY;

    // -------------------------------
    // Getter & Setter
    // -------------------------------
    public float getmAnchorX() {
        return this.mAnchorX;
    }

    public float getmAnchorY() {
        return this.mAnchorY;
    }

    public void setmAnchorX(float xPos) {
        this.mAnchorX = xPos;
    }

    public void setmAnchorY(float yPos) {
        this.mAnchorY = yPos;
    }

    // -------------------------------
    // Methods
    // -------------------------------

    /* Append card to current pile */
    protected void appendCard(Card pCard) {
        add(pCard);
        pCard.setZIndex(size() - 1);
    }

    /* Append pile of cards to current pile */
    protected void appendCards(ArrayList<Card> pArrayList) {
    	for (int i = 0; i < pArrayList.size(); i++) {
			appendCard(pArrayList.get(i));
		}
    }

    // -------------------------------
    // Methods
    // -------------------------------

    /* Get selected card index */
    protected int getCardIndex(Card pCard) {
        return indexOf(pCard);
    }

    /* Return last card (in the pile) */
    public Card getLastCard() {
    	if (size() > 0)
			return (Card) this.get(this.size() - 1);
		return null;
    }

    /* Remove card(s) from the pile */
    protected void removeCard(Card pCard) {
        remove(pCard);
    }

    protected void removeCards(ArrayList<Card> pArrayList) {
        removeAll(pArrayList);
    }


    // -------------------------------
    // Methods to be override
    // -------------------------------

    protected abstract Card getCanMoveCard();

    protected abstract int getCanMoveCardIndex();

    protected abstract ArrayList<Card> getCanMoveCards(Card pCard);

    protected abstract boolean isCanAppendCard(Card pCard);

    public abstract boolean isCanMove(Card pCard);

    public abstract void moveCardFailure();


}
