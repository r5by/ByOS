package edu.uta.byos;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import edu.uta.byos.Managers.ResourceManager;
import edu.uta.byos.Managers.SceneManager.SceneType;

public class SplashScene extends ManagedScene {

    // -------------------------------
    // Fields
    // -------------------------------
    private Sprite splash;

	@Override
	public void onCreateScene() {
//	    splash = new Sprite(0, 0, ResourceManager.splashTextureRegion, ResourceManager.getInstance().vbom) {
//            @Override
//            protected void preDraw(GLState pGLState, Camera pCamera) {
//                super.preDraw(pGLState, pCamera);
//                /* Better quality */
//                pGLState.enableDither();
//            }
        };

        /* Set it in the middle of the screen */
//        splash.setScale(2.0f);
//        splash.setPosition( (ResourceManager.getInstance().cameraWidth - splash.getWidth()) * 0.5f , 
//        					(ResourceManager.getInstance().cameraHeight - splash.getHeight()) * 0.5f);
//        attachChild(splash);
//	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub

	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SPLASH;
	}

	@Override
	public void onDisposeScene() {
        splash.detachSelf();
        splash.dispose();
        this.detachSelf();
        this.dispose();
	}

}
