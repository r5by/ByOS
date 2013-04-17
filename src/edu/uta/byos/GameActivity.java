package edu.uta.byos;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import android.view.KeyEvent;
import edu.uta.byos.Managers.ResourceManager;
import edu.uta.byos.Managers.SceneManager;
import edu.uta.byos.Managers.SceneManager.SceneType;
//import org.andengine.engine.handler.timer.ITimerCallback;
//import org.andengine.engine.handler.timer.TimerHandler;
//import android.view.KeyEvent;
//import android.webkit.WebIconDatabase;

public class GameActivity extends BaseGameActivity {

    // -------------------------------
    // Constants
    // -------------------------------

    /* TARGET design device: Samsung Galaxy */
    static float DESIGN_WINDOW_WIDTH_PX = 800f;
    static float DESIGN_WINDOW_HEIGHT_PX = 480f;

	// -------------------------------
	// Fields
	// -------------------------------
    private Camera mCamera;


    // -------------------------------
    // HANDLE THE BACK BUTTON
    // -------------------------------
    //@Override
    //public void onBackPressed() {
        //TODO
    //}

    // -------------------------------
    // CREATE ENGINE OPTIONS
    // -------------------------------
    /*
     * Override the onMeasure method of the ResolutionPolicy to set the
     * camera's size.
     */
		@Override
		public EngineOptions onCreateEngineOptions() {

			mCamera = new Camera(0, 0, DESIGN_WINDOW_WIDTH_PX, DESIGN_WINDOW_HEIGHT_PX);
            /* Create engine options */
            EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(DESIGN_WINDOW_WIDTH_PX,DESIGN_WINDOW_HEIGHT_PX), mCamera);

//			engineOptions.getAudioOptions().setNeedsMusic(true)
//										   .setNeedsSound(true);
			engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

			return engineOptions;
		}

		@Override
		public void onCreateResources(
				OnCreateResourcesCallback pOnCreateResourcesCallback) {

        /* Set up ResourceManager */
        ResourceManager.setup(this.getEngine(), this, DESIGN_WINDOW_WIDTH_PX, DESIGN_WINDOW_HEIGHT_PX, this.getVertexBufferObjectManager());

		pOnCreateResourcesCallback.onCreateResourcesFinished();
		}

		@Override
		public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {

            /* Create game scene at the beginning of the game */
            SceneManager.getInstance().onShowGameScene(pOnCreateSceneCallback);
       
		}

		@Override
		public void onPopulateScene(Scene pScene,
				OnPopulateSceneCallback pOnPopulateSceneCallback) {

//			 mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback()
//			    {
//			            public void onTimePassed(final TimerHandler pTimerHandler)
//			            {
//			                mEngine.unregisterUpdateHandler(pTimerHandler);
//			                // load menu resources, create menu scene
//			                // set menu scene using scene manager
//			                // disposeSplashScene();
//			                // READ NEXT ARTICLE FOR THIS PART.
//			            }
//			    }));

			pOnPopulateSceneCallback.onPopulateSceneFinished();
		}

//		@Override
//		protected void onDestroy()
//		{
//		    super.onDestroy();
//		    System.exit(0);
//		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if(keyCode == KeyEvent.KEYCODE_MENU
					&& event.getAction() == KeyEvent.ACTION_DOWN) {
				if(SceneManager.getInstance().getCurrentScene().hasChildScene()) {
					/* Remove the menu and reset it. */
					SceneManager.getInstance().onShowGameScene();
				} else {
					/* Attach the menu. */
					SceneManager.getInstance().onShowMenuScene();
				}
				return true;
			} else {
				return super.onKeyDown(keyCode, event);
			}
		}
}
