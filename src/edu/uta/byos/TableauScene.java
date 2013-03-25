package edu.uta.byos;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import edu.uta.byos.Managers.ResourceManager;
import edu.uta.byos.Managers.SceneManager.SceneType;

public class TableauScene extends ManagedScene {

    // -------------------------------
    // Fields
    // -------------------------------
    private Sprite mBackgroundSprite;
    private Sprite mFaceSprite;

	@Override
	public void onCreateScene() {
        createBackground();
        createTableauChild();
	}

	@Override
	public void onBackKeyPressed() {
        System.exit(0);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void onDisposeScene() {
		mBackgroundSprite.detachSelf();
        mBackgroundSprite.dispose();
        
        
        this.detachSelf();
        this.dispose();
	}

    // -------------------------------
    // Private Methods
    // -------------------------------
    private void createBackground() {

        /* Create background */
        mBackgroundSprite = new Sprite( 0, 0, ResourceManager.getInstance().cameraWidth, ResourceManager.getInstance().cameraHeight,
                                        ResourceManager.tableauTR, ResourceManager.getInstance().vbom)
        {
        	@Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
//        mBackgroundSprite.setZIndex(-5000);
        attachChild(mBackgroundSprite);
    }

    private void createTableauChild() {
    	/* TODO: attach cards onto tableau */
        mFaceSprite = new Sprite(0, 0, ResourceManager.mFaceTR, ResourceManager.getInstance().vbom);
        mFaceSprite.registerEntityModifier(new MoveModifier(30, 0, ResourceManager.getInstance().cameraWidth - mFaceSprite.getWidth(), 
        		0, ResourceManager.getInstance().cameraHeight - mFaceSprite.getHeight()));
		attachChild(mFaceSprite);
    }

}
