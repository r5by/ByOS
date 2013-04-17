package edu.uta.byos.Managers;

/**
 * **********[ ByOS ]**********
 * @Description A solitaire game
 * @Class ResourceManager:
 *  Maintain all game resources
 * @authors ruby_
 * @version 1.0
 * ****************************
 */

import org.andengine.engine.Engine;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.FloatMath;
import edu.uta.byos.GameActivity;

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
	public GameActivity activity;
	public VertexBufferObjectManager vbom;
	private String mPreviousAssetBasePath = "";

	// ======================== Game Resources ================= //
	public static ITextureRegion tableauTR;
	public static BitmapTextureAtlas cardGroundTA;
	public static ITextureRegion mCardBackTR;
	public static ITextureRegion cardVacantTR;

	/* For test use */
	public static ITextureRegion mFaceTR;

	// ======================== Menu Resources ================= //
	public static ITextureRegion menuBackgroundTR;
	public static ITiledTextureRegion newgameTiledTextureRegion;
	public static ITiledTextureRegion exitTiledTextureRegion;
	public static ITiledTextureRegion optionsTiledTextureRegion;
	public static ITiledTextureRegion hintTiledTextureRegion;
	public static ITiledTextureRegion dealTiledTextureRegion;

	// =================== Shared Game and Menu Resources ====== //
	// public static ITextureRegion splashTextureRegion;
	public static Font fontBazarDefault;

	// -------------------------------
	// Constructors
	// -------------------------------
	private ResourceManager() {
	}

	// -------------------------------
	// Getter & Setter
	// -------------------------------
	public static ResourceManager getInstance() {
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
	 *            Set up gameManager at the beginning of the game
	 */
	public static void setup(final Engine pEngine,
			final GameActivity pActivity, final float pCameraWidth,
			final float pCameraHeight, final VertexBufferObjectManager pVbom) {
		getInstance().engine = pEngine;
		getInstance().activity = pActivity;
		getInstance().cameraWidth = pCameraWidth;
		getInstance().cameraHeight = pCameraHeight;
		getInstance().vbom = pVbom;
	}

	// Loads splash resources
	// public static void loadSplashResources() {
	// getInstance().loadSharedTextures();
	// }

	// Loads all game resources.
	public static void loadGameResources() {
		getInstance().loadGameTextures();
		getInstance().loadSharedResources();
	}

	// Loads all menu resources
	public static void loadMenuResources() {
		getInstance().loadMenuTextures();
		// getInstance().loadSharedResources();
	}

	// Unloads all splash resources
	// public static void unloadSplashResources() {
	// getInstance().unloadSharedTextures();
	// }

	// Unloads all game resources.
	public static void unloadGameResources() {
		getInstance().unloadGameTextures();
	}

	// Unloads all menu resources
	public static void unloadMenuResources() {
		getInstance().unloadMenuTextures();
	}

	// Unloads all shared resources
	// public static void unloadSharedResources() {
	// getInstance().unloadSharedTextures();
	// getInstance().unloadSounds();
	// getInstance().unloadFonts();
	// }

	// -------------------------------
	// Private Methods
	// -------------------------------
	// Loads resources used by both the game scenes and menu scenes
	 private void loadSharedResources(){
//	 loadSharedTextures();
//	 loadSounds();
	 loadFonts();
	 }

	// ============================ LOAD TEXTURES (MENU) ================= //
	private void loadMenuTextures() {

		mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory
				.getAssetBasePath();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");

		/* background texture */
		BuildableBitmapTextureAtlas texture = new BuildableBitmapTextureAtlas(
				engine.getTextureManager(), 359, 283, TextureOptions.BILINEAR);
		menuBackgroundTR = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(texture, activity, "menu_background.png");
		try {
			texture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
					0, 1, 4));
			texture.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}

		/* MENU -> NEW GAME */
		BuildableBitmapTextureAtlas newgameT = new BuildableBitmapTextureAtlas(
				engine.getTextureManager(), 260, 36, TextureOptions.BILINEAR);
		newgameTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(newgameT, activity, "menu_newgame.png",
						2, 1);
		try {
			newgameT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
					0, 1, 4));
			newgameT.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}

		/* MENU -> EXIT */

		BuildableBitmapTextureAtlas exitT = new BuildableBitmapTextureAtlas(
				engine.getTextureManager(), 260, 36, TextureOptions.BILINEAR);
		exitTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(exitT, activity, "menu_exit.png", 2, 1);
		try {
			exitT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
					0, 1, 4));
			exitT.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}

		/* MENU -> OPTIONS */

		BuildableBitmapTextureAtlas optionsT = new BuildableBitmapTextureAtlas(
				engine.getTextureManager(), 260, 36, TextureOptions.BILINEAR);
		optionsTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(optionsT, activity, "menu_options.png",
						2, 1);
		try {
			optionsT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
					0, 1, 4));
			optionsT.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}

		/* MENU -> HINT */

		BuildableBitmapTextureAtlas hintT = new BuildableBitmapTextureAtlas(
				engine.getTextureManager(), 260, 35, TextureOptions.BILINEAR);
		hintTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(hintT, activity, "menu_hint.png", 2, 1);
		try {
			hintT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
					0, 1, 4));
			hintT.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}

		/* MENU -> DEAL */
		BuildableBitmapTextureAtlas dealT = new BuildableBitmapTextureAtlas(
				engine.getTextureManager(), 260, 35, TextureOptions.BILINEAR);
		dealTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(dealT, activity, "menu_deal.png", 2, 1);
		try {
			dealT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
					0, 1, 4));
			dealT.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}

		// Revert the Asset Path.
		BitmapTextureAtlasTextureRegionFactory
				.setAssetBasePath(mPreviousAssetBasePath);
	}

	// ============================ UNLOAD TEXTURES (MENU) =============== //
	private void unloadMenuTextures() {
		// background texture:
		if (menuBackgroundTR != null) {
			if (menuBackgroundTR.getTexture().isLoadedToHardware()) {
				menuBackgroundTR.getTexture().unload();
				menuBackgroundTR = null;
			}
		}

		if (optionsTiledTextureRegion != null) {
			if (optionsTiledTextureRegion.getTexture().isLoadedToHardware()) {
				optionsTiledTextureRegion.getTexture().unload();
				optionsTiledTextureRegion = null;
			}
		}

		if (hintTiledTextureRegion != null) {
			if (hintTiledTextureRegion.getTexture().isLoadedToHardware()) {
				hintTiledTextureRegion.getTexture().unload();
				hintTiledTextureRegion = null;
			}
		}

		if (dealTiledTextureRegion != null) {
			if (dealTiledTextureRegion.getTexture().isLoadedToHardware()) {
				dealTiledTextureRegion.getTexture().unload();
				dealTiledTextureRegion = null;
			}
		}

		if (newgameTiledTextureRegion != null) {
			if (newgameTiledTextureRegion.getTexture().isLoadedToHardware()) {
				newgameTiledTextureRegion.getTexture().unload();
				newgameTiledTextureRegion = null;
			}
		}

		if (exitTiledTextureRegion != null) {
			if (exitTiledTextureRegion.getTexture().isLoadedToHardware()) {
				exitTiledTextureRegion.getTexture().unload();
				exitTiledTextureRegion = null;
			}
		}

	}

	// ============================ LOAD TEXTURES (GAME) ================= //
	private void loadGameTextures() {

		mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory
				.getAssetBasePath();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");

		/* background texture */
		BuildableBitmapTextureAtlas tableauT = new BuildableBitmapTextureAtlas(
				engine.getTextureManager(), 490, 330);
		tableauTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				tableauT, activity, "background.png");
		try {
			tableauT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
					0, 1, 4));
			tableauT.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}

		/* Fly face texture, used for TEST-ONLY Purpose */
		BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(
				engine.getTextureManager(), 64, 64, TextureOptions.BILINEAR);
		mFaceTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mBitmapTextureAtlas, activity, "face_box_menu.png", 0, 0);
		mBitmapTextureAtlas.load();

		/* Load vacant card texture */
		BitmapTextureAtlas mCardVacantTA = new BitmapTextureAtlas(
				engine.getTextureManager(), 60, 82, TextureOptions.BILINEAR);
		cardVacantTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mCardVacantTA, activity,
				"card_vacant.png", 0, 0);
		mCardVacantTA.load();

		/* Load cards ground textures */
		cardGroundTA = new BitmapTextureAtlas(engine.getTextureManager(), 1024,
				512, TextureOptions.BILINEAR);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(cardGroundTA,
				activity, "card854.png", 0, 0);
		cardGroundTA.load();

		/* Load card back texture */
		BitmapTextureAtlas cardBackTA = new BitmapTextureAtlas(
				engine.getTextureManager(), 60, 82, TextureOptions.BILINEAR);
		mCardBackTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				cardBackTA, activity, "card_background.png", 0, 0);
		cardBackTA.load();

		// Revert the Asset Path.
		BitmapTextureAtlasTextureRegionFactory
				.setAssetBasePath(mPreviousAssetBasePath);
	}

	// ============================ UNLOAD TEXTURES (GAME) =============== //
	private void unloadGameTextures() {
		// background texture - only unload it if it is loaded:
		if (tableauTR != null) {
			if (tableauTR.getTexture().isLoadedToHardware()) {
				tableauTR.getTexture().unload();
				tableauTR = null;
			}
		}
	}

	// TODO: unload cardgroundTR and cardBackTR

	// ============================ LOAD TEXTURES (SHARED) ================= //
	// private void loadSharedTextures(){
	// Store the current asset base path to apply it after we've loaded our
	// textures
	// mPreviousAssetBasePath =
	// BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
	// Set our shared assets folder to "assets/gfx/"
	// BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

	/* Splash texture */
	// if(splashTextureRegion==null) {
	// BuildableBitmapTextureAtlas splashTextureAtlas = new
	// BuildableBitmapTextureAtlas(engine.getTextureManager(), 74, 74,
	// TextureOptions.BILINEAR);
	// splashTextureRegion =
	// BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas,
	// activity, "splash.png");
	// try {
	// splashTextureAtlas.build(new
	// BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
	// BitmapTextureAtlas>(0, 1, 4));
	// splashTextureAtlas.load();
	// } catch (TextureAtlasBuilderException e) {
	// Debug.e(e);
	// }
	// }

	// Revert the Asset Path.
	// BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
	// }
	// ============================ UNLOAD TEXTURES (SHARED) ============= //
	// private void unloadSharedTextures(){
	// /* splash texture */
	// if(splashTextureRegion!=null) {
	// if(splashTextureRegion.getTexture().isLoadedToHardware()) {
	// splashTextureRegion.getTexture().unload();
	// splashTextureRegion = null;
	// }
	// }
	//
	// }

	// ============================ LOAD TEXTURES (SPLASH) ================= //

	// ============================ UNLOAD TEXTURES (SPLASH) =================
	// //

	// =========================== LOAD SOUNDS ======================== //

	// =========================== UNLOAD SOUNDS ====================== //

	// ============================ LOAD FONTS ========================== //
	 private void loadFonts(){
			fontBazarDefault = getFont(Typeface.createFromAsset(activity.getAssets(), "fonts/Bazar.ttf"), 32f, true);
			fontBazarDefault.load();
		}
	 
	 	// The following methods load and return a Font using a minimal amount of texture memory
		private static String DEFAULT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890~`!@#$%^&*()-_=+[] {};:'\",<.>?/\\";
		public Font getFont(Typeface pTypeface, float pSize, boolean pAntiAlias)
		{
			return getFont(pTypeface, pSize, pAntiAlias, DEFAULT_CHARS);
		}
		
		private static float FONT_TEXTURE_PADDING_RATIO = 1.04f;
		private float FontTextureWidth = 0f;
		private float FontTextureHeight = 0f;
		private int FontTextureRows;
		public Font getFont(Typeface pTypeface, float pSize, boolean pAntiAlias, String pCharsToUse)
		{
			updateTextBounds(pTypeface,pSize,pAntiAlias,pCharsToUse);
			FontTextureWidth = (float) (mTextBounds.width()-mTextBounds.left);
			FontTextureHeight = (float) (mTextBounds.height()-mTextBounds.top);
			FontTextureRows = (int) Math.round(FloatMath.sqrt(FontTextureWidth/FontTextureHeight));
			FontTextureWidth = ((FontTextureWidth / FontTextureRows) * FONT_TEXTURE_PADDING_RATIO);
			FontTextureHeight = ((FontTextureHeight * FontTextureRows) * FONT_TEXTURE_PADDING_RATIO);
			return new Font(engine.getFontManager(), new BitmapTextureAtlas(engine.getTextureManager(), (int) FontTextureWidth, (int) FontTextureHeight, BitmapTextureFormat.RGBA_8888, TextureOptions.DEFAULT), pTypeface, pSize, pAntiAlias, Color.WHITE_ARGB_PACKED_INT);
		}
		
		private Paint mPaint;
		private Rect mTextBounds = new Rect();
		private void updateTextBounds(final Typeface pTypeface, final float pSize, final boolean pAntiAlias, final String pCharacterAsString) {
			mPaint = new Paint();
			mPaint.setTypeface(pTypeface);
			mPaint.setTextSize(pSize);
			mPaint.setAntiAlias(pAntiAlias);
			mPaint.getTextBounds(pCharacterAsString, 0, pCharacterAsString.length(), this.mTextBounds);
		}

	// ============================ UNLOAD FONTS ======================== //

}
