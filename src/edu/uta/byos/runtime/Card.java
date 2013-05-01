package edu.uta.byos.runtime;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import edu.uta.byos.Managers.GameManager;
import edu.uta.byos.Managers.SceneManager;

/**
 * **************************** [ ByOS ] *****************************
 * 
 * @Description A solitaire game
 * @Class | Card | A <code>Card</code> Object represents a playing card
 * @has mCardIndex: [suit, value] - pair
 * @has texture: original & cardBack
 * @authors ruby_
 * @version 3.8
 * @since v-2.0
 * *******************************************************************/

public class Card extends Sprite {

    // -------------------------------
    // Fields
    // -------------------------------
    private boolean isFace;
    private boolean isGrabbed;
    private boolean isClicked;
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
    
    public boolean isClicked() {
    	return this.isClicked;
    }

    public ITextureRegion getmCardOriginalTextureRegion() {
        return this.mCardOriginalTR;
    }

    public boolean isFace() {
        return this.isFace;
    }
    
    public boolean isGrabbed() {
    	return this.isGrabbed;
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
    	if(!isClicked()) {
    		setColor(0.5f, 0.5f, 0.5f);
    		isClicked = true;
    	}
    }

    public void release() {
    	if(isClicked()) {
    		setColor(1.0f, 1.0f, 1.0f);
    		isClicked = false;
    	}
    }

    public void onTurnOff() {
    	if(isFace() == true) {
    		this.isFace = false;
    		this.setTextureRegion(mCardBackTR);
    	}
	}

    public void onTurnOn() {
    	if(isFace() == false) {
    		this.isFace = true;
    		this.setTextureRegion(this.mCardOriginalTR);
    	}
	}
    
    // -------------------------------
 	// Overrides
 	// -------------------------------
 	@Override
 	public boolean onAreaTouched(
 			org.andengine.input.touch.TouchEvent pSceneTouchEvent,
 			float pTouchAreaLocalX, float pTouchAreaLocalY) {
 		int originalZIndex = this.getZIndex();
 		
 		switch(pSceneTouchEvent.getAction()) {
 		 case TouchEvent.ACTION_MOVE:
         case TouchEvent.ACTION_DOWN:{
        	this.isGrabbed = true;
  			GameManager.SELECTEDCARD = this;
  			this.release();	
  			GameManager.SELECTEDCARD.setZIndex(originalZIndex + 1);
  			SceneManager.getInstance().getCurrentScene().sortChildren();
  			GameManager.SELECTEDCARD.setPosition(pSceneTouchEvent.getX() - GameManager.SELECTEDCARD.getWidth() * 0.5f, 
 				pSceneTouchEvent.getY() - GameManager.SELECTEDCARD.getHeight() * 0.5f);
             return true; 
         }
         case TouchEvent.ACTION_UP:{
        	GameManager.SELECTEDCARD.isGrabbed = false;
            return true; 
         }
         case TouchEvent.ACTION_CANCEL:{
             // If the event is somehow canceled - e.g. the finger leaves the display
             return true; 
         }
         default:{
             // none of the above
             return false;
         }
 		}
 		 		
 	};

}
