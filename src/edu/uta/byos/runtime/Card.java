package edu.uta.byos.runtime;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Card extends RegionChangableSprite {

    // -------------------------------
    // Fields
    // -------------------------------
    protected boolean isFace;
    protected int mCardColor;
    protected int mCardValue;
    protected ITextureRegion mCardOriginalTR;
    protected ITextureRegion mCardBackTR;
    
    public static final int CARD_WIDTH = 78;
    public static final int CARD_HEIGHT = 106;

    // -------------------------------
    // Constructor
    // -------------------------------
	public Card(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, CARD_WIDTH, CARD_HEIGHT, pTextureRegion, pVertexBufferObjectManager);
        this.mCardBackTR = pTextureRegion;
	}

    // -------------------------------
    // setters & getters
    // -------------------------------

    public int getmCardColor() {
        return this.mCardColor;
    }

    public ITextureRegion getmCardOriginalTextureRegion() {
        return this.mCardOriginalTR;
    }

    public int getmCardValue() {
        return this.mCardValue;
    }

    public boolean isFace() {
        return this.isFace;
    }

    public void setFace(boolean pIsFace) {
        this.isFace = pIsFace;
    }

    public void setmCardColor(int pmCardColor) {
        this.mCardColor = pmCardColor;
    }

    public void setmCardOriginalTextureRegion(ITextureRegion pTextureRegion) {
        this.mCardOriginalTR = pTextureRegion;
    }

    public void setmCardValue(int pCardValue) {
        this.mCardValue = pCardValue;
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

    protected void onTurnOff() {
        this.isFace = false;
        setTextureRegion(this.mCardBackTR);
    }

    protected void onTurnOn() {
        this.isFace = true;
        setTextureRegion(this.mCardOriginalTR);
    }


}
