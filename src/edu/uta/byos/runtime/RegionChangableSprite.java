package edu.uta.byos.runtime;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class RegionChangableSprite extends Rectangle {

	protected int layer;
	protected ITextureRegion mTextureRegion;

    // -------------------------------
    // Getter & Setter
    // -------------------------------
    public int getLayer() {
        return this.layer;
    }

    public ITextureRegion getTextureRegion() {
        return this.mTextureRegion;
    }

    public void setLayer(int pLayerIndex) {
    this.layer = pLayerIndex;
  }

  public void setTextureRegion(ITextureRegion pTextureRegion)
  {
    this.mTextureRegion = pTextureRegion;
  }

    // -------------------------------
    // Public Methods
    // -------------------------------
    public RegionChangableSprite(float pX, float pY, float pWidth,
			float pHeight, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pVertexBufferObjectManager);
		this.mTextureRegion = pTextureRegion;
		initBlendFunction();
	}

    public void reset() {
        super.reset();
        initBlendFunction();
    }
    
    /* TODO: NEED TO BE FIXED */
//    protected void onApplyTransformations(GL10 paramGL10)
//    {
//      super.onApplyTransformations(paramGL10);
//      this.mTextureRegion.onApply(paramGL10);
//    }
//
//    protected void onInitDraw(GL10 paramGL10)
//    {
//      super.onInitDraw(paramGL10);
//      GLHelper.enableTextures(paramGL10);
//      GLHelper.enableTexCoordArray(paramGL10);
//    }

    // -------------------------------
    // Private Methods
    // -------------------------------

	private void initBlendFunction() {
		if (this.mTextureRegion.getTexture().getTextureOptions().mPreMultiplyAlpha)
			setBlendFunction(1, 771);
	}
}
