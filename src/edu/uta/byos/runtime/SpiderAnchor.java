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
* @version 1.0
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

	@Override
	protected Card getCanMoveCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getCanMoveCardIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected ArrayList<Card> getCanMoveCards(Card pCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isCanAppendCard(Card pCard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCanMove(Card pCard) {
		// TODO Auto-generated method stub
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
