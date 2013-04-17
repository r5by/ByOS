package edu.uta.byos;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.HorizontalAlign;

import edu.uta.byos.Managers.GameManager;
import edu.uta.byos.Managers.ResourceManager;
import edu.uta.byos.Managers.SceneManager;
import edu.uta.byos.Managers.SceneManager.SceneType;
import edu.uta.byos.runtime.Card;

public class TableauScene extends ManagedScene {

    // -------------------------------
    // Fields
    // -------------------------------
    private Sprite mBackgroundSprite;
    private static Sprite mDeckSprite;
    private Sprite mFaceSprite;
    private HUD gameHUD;
    private Text deckCntText;

//    private Card mCard01;

	@Override
	public void onCreateScene() {
        createBackground();
        createTableauChild();
        createHUD();
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
	
	public static void removeDeck() {
		if(mDeckSprite != null) {
			mDeckSprite.detachSelf();
			mDeckSprite.dispose();
		}
	}

    // -------------------------------
    // Private Methods
    // -------------------------------

	private void createHUD() {
		gameHUD = new HUD();

        /* Create the deck count */
        deckCntText = new Text(GameManager.mAnchorList.valueAt(0).getmAnchorX(),
				ResourceManager.getInstance().cameraHeight - Card.CARD_HEIGHT - GameManager.mAnchorY,
				ResourceManager.fontBazarDefault, "01234", new TextOptions(HorizontalAlign.CENTER), 
				ResourceManager.getInstance().vbom);
        deckCntText.setText(Integer.toString(GameManager.deckCnt));
        gameHUD.attachChild(deckCntText);

        ResourceManager.getInstance().engine.getCamera().setHUD(gameHUD);
	}

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

        /* Vacant cards on tableau */
        /* Arrangement of vacant cards (under each anchor) */
        for(int i = 0; i < GameManager.mAnchorList.size(); i++) {
        	attachChild(
    				new Sprite(GameManager.mAnchorList.valueAt(i).getmAnchorX(), GameManager.mAnchorY, Card.CARD_WIDTH,
    						Card.CARD_HEIGHT, ResourceManager.cardVacantTR,
    						ResourceManager.getInstance().vbom));
        	GameManager.mAnchorList.valueAt(i);
        }

        /* Piles of cards on tableau */
        for(int i = 0; i < GameManager.mAnchorList.size(); i++) {
        	for(Card card : GameManager.mAnchorList.valueAt(i))
        		attachChild(card);
        }

        /* Place holder for remaining deck */
		attachChild(
				new Sprite(GameManager.mAnchorList.valueAt(0).getmAnchorX(),
						ResourceManager.getInstance().cameraHeight - Card.CARD_HEIGHT - GameManager.mAnchorY,
						Card.CARD_WIDTH, Card.CARD_HEIGHT,
						ResourceManager.cardVacantTR, ResourceManager
								.getInstance().vbom));
		
		mDeckSprite =	new Sprite(GameManager.mAnchorList.valueAt(0).getmAnchorX(),
						ResourceManager.getInstance().cameraHeight - Card.CARD_HEIGHT - GameManager.mAnchorY,
						Card.CARD_WIDTH, Card.CARD_HEIGHT,
						ResourceManager.mCardBackTR, ResourceManager.getInstance().vbom) {
			
			@Override
			protected void onManagedUpdate(float pSecondsElapsed) {
				super.onManagedUpdate(pSecondsElapsed);
				
				if(GameManager.deckCnt < 1) {
					this.setVisible(false);
					this.setIgnoreUpdate(true);
				}
			}
			
			@Override
			public boolean onAreaTouched(
					TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				
				if (pSceneTouchEvent.isActionDown()) {
					GameManager.getInstance().dealCards();
					for(int i = 0; i < GameManager.mDeckList.get(3 - GameManager.deckCnt).size(); i++)
						SceneManager.getInstance().getCurrentScene().attachChild(GameManager.mAnchorList.get(i).getLastCard());
					
					deckCntText.setText(Integer.toString(GameManager.deckCnt));
				}
				
				return super
						.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
			}
		};
		
		
		attachChild(mDeckSprite);
		attachChild(mFaceSprite);

		registerTouchArea(mDeckSprite);
		setTouchAreaBindingOnActionDownEnabled(true);
    }


}
