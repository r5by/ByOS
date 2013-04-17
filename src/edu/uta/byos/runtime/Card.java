package edu.uta.byos.runtime;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import edu.uta.byos.runtime.CardList;

/**
 * **************************** [ ByOS ] *****************************
 * 
 * @Description A solitaire game
 * @Class | Card | A <code>Card</code> Object represents a playing card
 * @has mCardIndex: [suit, value] - pair
 * @has texture: original & cardBack
 * @authors ruby_
 * @version 1.0 *******************************************************
 */

public class Card extends Sprite {

    // -------------------------------
    // Fields
    // -------------------------------
    private boolean isFace;
    private CardList mCardIndex;
    private ITextureRegion mCardOriginalTR;
    private ITextureRegion mCardBackTR;

    public static final int CARD_WIDTH = 78;
    public static final int CARD_HEIGHT = 106;


    // -------------------------------
    // Constructor
    // -------------------------------
    public Card(float pX, float pY, final CardList pCardIndex,
			final ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, CARD_WIDTH, CARD_HEIGHT, pTextureRegion,
				pVertexBufferObjectManager);
		this.mCardBackTR = pTextureRegion;
		this.mCardIndex = pCardIndex;
	}

    // -------------------------------
    // setters & getters
    // -------------------------------

    public CardList getCard() {
		return this.mCardIndex;
	}

    public ITextureRegion getmCardOriginalTextureRegion() {
        return this.mCardOriginalTR;
    }

    public boolean isFace() {
        return this.isFace;
    }

    public void setFace(boolean pIsFace) {
        this.isFace = pIsFace;
    }

    public void setmCardOriginalTextureRegion(ITextureRegion pTextureRegion) {
        this.mCardOriginalTR = pTextureRegion;
    }

    // -------------------------------
    // Methods
    // -------------------------------
    public void clicked() {
        setColor(0.5f, 0.5f, 0.5f);
    }

    public void release() {
        setColor(1.0f, 1.0f, 1.0f);
    }

    public void onTurnOff() {
		this.isFace = false;
		this.setTextureRegion(mCardBackTR);
	}

    public void onTurnOn() {
		this.isFace = true;
		this.setTextureRegion(this.mCardOriginalTR);
	}
    
 // -------------------------------
 	// Overrides
 	// -------------------------------
 	@Override
 	public boolean onAreaTouched(
 			org.andengine.input.touch.TouchEvent pSceneTouchEvent,
 			float pTouchAreaLocalX, float pTouchAreaLocalY) {
 		if (pSceneTouchEvent.isActionDown()) {
 			this.setFace(!isFace);
 			if (isFace())
 				onTurnOn();
 			else
 				onTurnOff();
 			return true;
 		} else
 			return false;
 	};

}
