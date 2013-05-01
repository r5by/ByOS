package edu.uta.byos.runtime;

import java.util.ArrayList;

import edu.uta.byos.Managers.ResourceManager;
import edu.uta.byos.runtime.Card;

import edu.uta.byos.Managers.GameManager;

/**
* ******************************* [ ByOS ] ********************************
* @Description A solitaire game
* @Class    |SpiderAnchor
*           | A class implements Anchor that apply spider solitaire rule
* @authors ruby_
* @version 3.8
* @Since v-3.0
* **************************************************************************
*/


public class SpiderAnchor extends Anchor {

	private static final long serialVersionUID = 1L;

    /* AppendCard to anchor at proper position & space, according to whether the card isFace or not */
    @Override
    public void appendCard(Card pCard) {
    	if (this.size() > 0) {
			if (this.getLastCard().isFace())
				pCard.setPosition(this.getmAnchorX(), this.getLastCard().getY()
						+ GameManager.mAnchorSpacing);
			else
				pCard.setPosition(this.getmAnchorX(), this.getLastCard().getY()
						+ GameManager.mAnchorSpacing / 4f);
		} else if (this.size() == 0) {
			pCard.setPosition(getmAnchorX(), GameManager.mAnchorY);
		}
		super.appendCard(pCard);
		arrangeAnchor();
    }
    
    /* Remove card & cards */
    @Override
    public void removeCard(Card pCard) {
    	super.removeCard(pCard);
    	
//    	this.getLastCard().onTurnOn();
    	arrangeAnchor();
    };
    
    @Override
    public void removeCards(java.util.ArrayList<Card> pArrayList) {
    	super.removeCards(pArrayList);
    	arrangeAnchor();
    };

    /*
     * Arrange anchor so that no appended cards exceeds the tableau's boarder
     */
    public void arrangeAnchor() {

    	if (this.size() > 0 && this.getFirstFaceCardIndex() != -1) {
			/* Adjust the face up cards' spacing */
			int firstFaceUpCardIndex = getFirstFaceCardIndex();
			Card firstFaceUpCard = this.get(firstFaceUpCardIndex);
			float anchorSpaceHolder = ( ResourceManager.getInstance().cameraHeight- Card.CARD_HEIGHT * 2.0f - firstFaceUpCard
						.getY()) / (this.size() - 1 - firstFaceUpCardIndex);
			if (firstFaceUpCardIndex >= 0
					&& firstFaceUpCardIndex != this.size() - 1) {
				for (int faceUpCardIndex = firstFaceUpCardIndex + 1; faceUpCardIndex < this
						.size(); faceUpCardIndex++) {
					Card localCard = this.get(faceUpCardIndex);
					if (anchorSpaceHolder < GameManager.mAnchorSpacing) {
						localCard
								.setPosition(
										firstFaceUpCard.getX(),
										firstFaceUpCard.getY()
												+ anchorSpaceHolder
												* (faceUpCardIndex - firstFaceUpCardIndex));
					} else {
						localCard
								.setPosition(
										firstFaceUpCard.getX(),
										firstFaceUpCard.getY()
												+ GameManager.mAnchorSpacing
												* (faceUpCardIndex - firstFaceUpCardIndex));
					}
				}
			}
		}
    }

    /*
     * Get the first card in the spiderAnchor that can be moved
     */
	@Override
	public Card getCanMoveCard() {
		if(size() == 1)
			return get(0);
		if(size() > 1) {
			int i = size() -1;
			while(i > getFirstFaceCardIndex()) {
				Card localCard = get(i);
				if( get(i-1).getCard().getValue().compareTo(localCard.getCard().getValue()) == 1 )
					i--;
				else
					break;
			}
			return get(i);
		}
		else
			return null;
	}

	/*
     * Get the first card (index) in the spiderAnchor that can be moved 
     */
	@Override
	protected int getCanMoveCardIndex() {
		if(size() == 1)
			return 0;
		if(size() > 1) {
			int i = size() -1;
			while(i > getFirstFaceCardIndex()) {
				Card localCard = get(i);
				if( get(i-1).getCard().getValue().compareTo(localCard.getCard().getValue()) == 1)
					i--;
				else
					break;
			}
			return i;
		}
		else
			return 0;
	}

	/*
	 * Get arraylist<card> that represents cards under passed parameter (if exists)
	 * Used along with getCanMoveCard as:
	 * 		getCanMoveCards(getCanMoveCard()) -> to return list of cards that can be moved
	 */
	@Override
	protected ArrayList<Card> getCanMoveCards(Card pCard) {
		if(getCardIndex(pCard) >= 0) {
			ArrayList<Card> localCards = new ArrayList<Card>();
			for(int i = getCardIndex(pCard); i < size(); i++) {
				localCards.add(get(i));
			}
			return localCards;
		} else
			return null;
	}
	
	public ArrayList<Card> getCanMoveCards() {
		return this.getCanMoveCards(this.getCanMoveCard());
	}

	/*
	 * Decide whether passed card can be appended to current anchor
	 */
	@Override
	public boolean isCanAppendCard(Card pCard) {
       if(size() > 0 && getLastCard().getCard().getValue().ordinal() == (pCard.getCard().getValue().ordinal() + 1) ) 
    	   return true;
       return false;
	}

	/*
	 * Decide whether passed-in card can be moved in the anchor
	 */
	@Override
	public boolean isCanMove(Card pCard) {
		ArrayList<Card> localCards = new ArrayList<Card>();
		localCards = getCanMoveCards(getCanMoveCard());
		for(int i = 0; i < localCards.size(); i++) {
			if(pCard == localCards.get(i))
				return true;
		}
        return false;
	}

	@Override
	public void moveCardFailure() {
		// TODO Auto-generated method stub

	}

    // -------------------------------
    // Private
    // -------------------------------
	private int getFirstFaceCardIndex() {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).isFace())
				return i;
		}
		/* TODO: Exception Handling */
		return -1;
	}
}
