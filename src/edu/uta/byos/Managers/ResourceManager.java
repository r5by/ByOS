package edu.uta.byos.Managers;

/**
* **********[ ByOS ]**********
* @Description A solitaire game
* @authors ruby_
* @version 1.0
* ****************************
*/

import org.andengine.engine.Engine;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.app.Activity;


public class ResourceManager {

    // -------------------------------
    // Constants
    // -------------------------------
    private static final ResourceManager INSTANCE = new ResourceManager();

    // -------------------------------
    // Fields
    // -------------------------------
    public float cameraWidth;
    public float cameraHeight;

    public Engine engine;
    public Activity activity;
    public VertexBufferObjectManager vbom;
    private String mPreviousAssetBasePath = "";

    // ======================== Game Resources ================= //
    public static ITextureRegion tableauTextureRegion;

    // ======================== Menu Resources ================= //
    public static ITextureRegion menuBackgroundTextureRegion;
    public static ITiledTextureRegion newgameTiledTextureRegion;
    public static ITiledTextureRegion exitTiledTextureRegion;
    public static ITiledTextureRegion optionsTiledTextureRegion;
    public static ITiledTextureRegion hintTiledTextureRegion;
    public static ITiledTextureRegion dealTiledTextureRegion;

    // =================== Shared Game and Menu Resources ====== //
    public static ITextureRegion splashTextureRegion;

    // -------------------------------
    // Constructors
    // -------------------------------
    private ResourceManager() {
    }

    // -------------------------------
    // Getter & Setter
    // -------------------------------
    public static ResourceManager getInstance(){
        return INSTANCE;
    }

    // -------------------------------
    // Public Methods
    // -------------------------------
    /**
     * @param pEngine
     * @param pActivity
     * @param pCameraWidth
     * @param pCameraHeight
     * @param pVbom
     * Set up gameManager at the beginning of the game
     */
    public static void setup(final Engine pEngine, final Activity pActivity, final float pCameraWidth, final float pCameraHeight, final VertexBufferObjectManager pVbom){
        getInstance().engine = pEngine;
        getInstance().activity = pActivity;
        getInstance().cameraWidth = pCameraWidth;
        getInstance().cameraHeight = pCameraHeight;
        getInstance().vbom = pVbom;
    }

    //Loads splash resources
    public static void loadSplashResources() {
        getInstance().loadSharedTextures();
    }

    // Loads all game resources.
    public static void loadGameResources() {
        getInstance().loadGameTextures();
        //getInstance().loadSharedResources();
    }


    // Loads all menu resources
    public static void loadMenuResources() {
        getInstance().loadMenuTextures();
        //getInstance().loadSharedResources();
    }

    // Unloads all splash resources
    public static void unloadSplashResources() {
        getInstance().unloadSharedTextures();
    }

    // Unloads all game resources.
    public static void unloadGameResources() {
        getInstance().unloadGameTextures();
    }

    // Unloads all menu resources
    public static void unloadMenuResources() {
        getInstance().unloadMenuTextures();
    }

    // Unloads all shared resources
    //public static void unloadSharedResources() {
        //getInstance().unloadSharedTextures();
        //getInstance().unloadSounds();
        //getInstance().unloadFonts();
    //}

    // -------------------------------
    // Private Methods
    // -------------------------------
    // Loads resources used by both the game scenes and menu scenes
    //private void loadSharedResources(){
        //loadSharedTextures();
        //loadSounds();
        //loadFonts();
    //}

    // ============================ LOAD TEXTURES (MENU) ================= //
    private void loadMenuTextures(){
        // Store the current asset base path to apply it after we've loaded our textures
        mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
        // Set our menu assets folder to "assets/gfx/menu/"
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");

        // background texture:
        if(menuBackgroundTextureRegion==null) {
            BuildableBitmapTextureAtlas texture = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 359, 283, TextureOptions.BILINEAR);
            menuBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(texture, activity, "menu_background.png");
            try {
                texture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 4));
                texture.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }

        /* MENU -> NEW GAME */
         if(newgameTiledTextureRegion==null) {
            BuildableBitmapTextureAtlas newgameT = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 260, 36,
                    TextureOptions.BILINEAR);
            newgameTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(newgameT, activity, "menu_newgame.png",2,1);
            try {
                newgameT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 4));
                newgameT.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }

         /* MENU -> EXIT */
         if(exitTiledTextureRegion==null) {
            BuildableBitmapTextureAtlas exitT = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 260, 36,
                    TextureOptions.BILINEAR);
            exitTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(exitT, activity, "menu_exit.png",2,1);
            try {
                exitT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 4));
                exitT.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }

        /* MENU -> OPTIONS */
        if(optionsTiledTextureRegion==null) {
            BuildableBitmapTextureAtlas optionsT = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 260, 36,
                    TextureOptions.BILINEAR);
            optionsTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(optionsT, activity, "menu_options.png",2,1);
            try {
                optionsT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 4));
                optionsT.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }

        /* MENU -> HINT */
        if(hintTiledTextureRegion==null) {
            BuildableBitmapTextureAtlas hintT = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 260, 35,
                    TextureOptions.BILINEAR);
            hintTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(hintT, activity, "menu_hint.png",2,1);
            try {
                hintT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 4));
                hintT.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }

        /* MENU -> DEAL */
        if(dealTiledTextureRegion==null) {
            BuildableBitmapTextureAtlas dealT = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 260, 35,
                    TextureOptions.BILINEAR);
            dealTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(dealT, activity, "menu_deal.png",2,1);
            try {
                dealT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 4));
                dealT.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }




        // Revert the Asset Path.
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
    }
    // ============================ UNLOAD TEXTURES (MENU) =============== //
    private void unloadMenuTextures(){
        // background texture:
        if(menuBackgroundTextureRegion!=null) {
            if(menuBackgroundTextureRegion.getTexture().isLoadedToHardware()) {
                menuBackgroundTextureRegion.getTexture().unload();
                menuBackgroundTextureRegion = null;
            }
        }

        if(optionsTiledTextureRegion!=null) {
            if(optionsTiledTextureRegion.getTexture().isLoadedToHardware()) {
                optionsTiledTextureRegion.getTexture().unload();
                optionsTiledTextureRegion = null;
            }
        }

         if(hintTiledTextureRegion!=null) {
            if(hintTiledTextureRegion.getTexture().isLoadedToHardware()) {
                hintTiledTextureRegion.getTexture().unload();
                hintTiledTextureRegion = null;
            }
        }

         if(dealTiledTextureRegion!=null) {
            if(dealTiledTextureRegion.getTexture().isLoadedToHardware()) {
                dealTiledTextureRegion.getTexture().unload();
                dealTiledTextureRegion = null;
            }
        }

        if(newgameTiledTextureRegion!=null) {
            if(newgameTiledTextureRegion.getTexture().isLoadedToHardware()) {
                newgameTiledTextureRegion.getTexture().unload();
                newgameTiledTextureRegion = null;
            }
        }

         if(exitTiledTextureRegion!=null) {
            if(exitTiledTextureRegion.getTexture().isLoadedToHardware()) {
                exitTiledTextureRegion.getTexture().unload();
                exitTiledTextureRegion = null;
            }
        }

    }

    // ============================ LOAD TEXTURES (GAME) ================= //
    private void loadGameTextures(){
        // Store the current asset base path to apply it after we've loaded our textures
        mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
        // Set our game assets folder to "assets/gfx/game/"
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");

        // background texture - only load it if we need to:
        if(tableauTextureRegion==null) {
            BuildableBitmapTextureAtlas tableauT = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 490, 330);
            tableauTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tableauT, activity, "background.png");
            try {
                tableauT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 4));
                tableauT.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }

        // Revert the Asset Path.
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
    }
    // ============================ UNLOAD TEXTURES (GAME) =============== //
    private void unloadGameTextures(){
        // background texture - only unload it if it is loaded:
        if(tableauTextureRegion!=null) {
            if(tableauTextureRegion.getTexture().isLoadedToHardware()) {
                tableauTextureRegion.getTexture().unload();
                tableauTextureRegion = null;
            }
        }
    }

    // ============================ LOAD TEXTURES (SHARED) ================= //
    private void loadSharedTextures(){
        // Store the current asset base path to apply it after we've loaded our textures
        mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
        // Set our shared assets folder to "assets/gfx/"
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        /* Splash texture */
        if(splashTextureRegion==null) {
            BuildableBitmapTextureAtlas splashTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 74, 74,
                    TextureOptions.BILINEAR);
            splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.png");
            try {
                splashTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 4));
                splashTextureAtlas.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }

        // Revert the Asset Path.
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
    }
    // ============================ UNLOAD TEXTURES (SHARED) ============= //
    private void unloadSharedTextures(){
        /* splash texture */
        if(splashTextureRegion!=null) {
            if(splashTextureRegion.getTexture().isLoadedToHardware()) {
                splashTextureRegion.getTexture().unload();
                splashTextureRegion = null;
            }
        }

    }

    // ============================ LOAD TEXTURES (SPLASH) ================= //

    // ============================ UNLOAD TEXTURES (SPLASH) ================= //

    // =========================== LOAD SOUNDS ======================== //

    // =========================== UNLOAD SOUNDS ====================== //


    // ============================ LOAD FONTS ========================== //

    // ============================ UNLOAD FONTS ======================== //

}
