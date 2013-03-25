package edu.uta.byos.runtime;

import java.util.ArrayList;

import edu.uta.byos.Managers.GameManager;

/**
* ********** [ ByOS ] ***********
* @Description A solitaire game
* @Class    |SpiderAnchor
*           | A class implements Anchor that apply spider solitaire rule
* @authors ruby_
* @version 1.0
* ***************************************
*/


public class SpiderAnchor extends Anchor {

	private static final long serialVersionUID = 1L;

    /* AppendCard to anchor at proper position & space, according to whether the card isFace or not */
    @Override
    protected void appendCard(Card pCard) {
        if(size() > 0) {
            if(getLastCard().isFace())
                pCard.setPosition(getmAnchorX(), getLastCard().getY() + GameManager.mAnchorSpacing);
            else
                pCard.setPosition(getmAnchorX(), getLastCard().getY() + GameManager.mAnchorSpacing / 4f );
        } else if (size() == 0) {
                pCard.setPosition(getmAnchorX(), GameManager.mAnchorY);
        }
        super.appendCard(pCard);
        arrangementAnchor();
    }

    protected void arrangementAnchor() {
        if(size() > 0) {
            int i = getFirstFaceCardIndex();
            if( i >= 0) {
                float f;
                int k;
                Card card = (Card)get(i);
                card.setColor(1.0f, 1.0f, 1.0f);
                card.setZIndex(i);
            }
            /* TO BE CONTINUED */
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
        for(int i = 0; i < size() ; i++) {
            if(((Card)get(i)).isFace())
                return i;
        }
        /* TODO: Exception Handling */
        return -1;
    }
}
