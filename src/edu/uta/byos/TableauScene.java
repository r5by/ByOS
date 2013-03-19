package edu.uta.byos;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import edu.uta.byos.Managers.ResourceManager;
import edu.uta.byos.Managers.SceneManager.SceneType;

public class TableauScene extends ManagedScene {

    // -------------------------------
    // Fields
    // -------------------------------
    private Sprite mBackgroundSprite;
    private final float BACKGROUND_WIDTH = cameraWidth;
    private final float BACKGROUND_HEIGHT = cameraHeight;
     //private Sprite[] CardSprites;

	@Override
	public void onShowScene() {
        createBackground();
//        createTableauChildScene();
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
		// TODO Auto-generated method stub

	}

    // -------------------------------
    // Private Methods
    // -------------------------------
    private void createBackground() {
        /* Load game resources */
        ResourceManager.loadGameResources();

        /* Create background */
        mBackgroundSprite = new Sprite( 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT,
                                        ResourceManager.tableauTextureRegion, vbom) {
        	@Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        mBackgroundSprite.setZIndex(-5000);
        attachChild(mBackgroundSprite);
    }

//    private void createTableauChildScene() {
//        //TODO draw cards on tableau
//    }

}
