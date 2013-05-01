package edu.uta.byos.Managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;

import android.util.SparseArray;
import edu.uta.byos.TableauScene;
import edu.uta.byos.runtime.Anchor;
import edu.uta.byos.runtime.Card;
import edu.uta.byos.runtime.CardList;
import edu.uta.byos.runtime.SpiderAnchor;

/**
* ************** [ ByOS ] ***************
* @Description A solitaire game
* @Class    | GameManager
*           | A class designed to keep track of
*           | certain data involved in game
*           | Pattern: Singleton
* @authors ruby_
* @version 3.8
* @since v- 2.0
* ***************************************
*/

public class GameManager {

    // -------------------------------
    // Fields
    // -------------------------------

	private static GameManager INSTANCE;
    /* 52 cards set, 45 were set turned off at beginning */
    private static final int INITTURNOFFCNT = 45;
    /* 10 piles of cards on board */
	private static int ANCHORCNT = 7;
	/* Each pile consists 4 cards initialy */
	private static int ANCHORINITCARDSCNT = 4;
    /* Color suit: 1, 2 or 4; 1 by default */
	private int mCardSuitCount = 1;
	
	 /* 5 sets of cards available to be used */
	public static int deckCnt = 4;
	public static final int MAXZINDEX = 99;
	public static float mAnchorY = 1.0f;
	public static float mAnchorSpacing = Card.CARD_HEIGHT * 0.5f;
	
    /* One/Two/Four set of cards */
	private ArrayList<Card> oneSuitCards = new ArrayList(); //one color suit
	private ArrayList<Card> twoSuitCards = new ArrayList(); //two color suits
	private ArrayList<Card> fourSuitCards = new ArrayList(); //four color suits
	private ArrayList<Card> mAllCards = new ArrayList<Card>(); //Used to init suits

	public static Card SELECTEDCARD = null;
	public static ArrayList<Card> mMovingCards;
	public static EnumMap<CardList, Card> mCardMap;
	public static SparseArray<Anchor> mAnchorList;
	public static SparseArray<ArrayList<Card>> mDeckList;
	public static HashMap<Integer, AnchorRange> touchedAreaList;

    // -------------------------------
    // Constructors
    // -------------------------------
    GameManager() {
    }
    public static GameManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GameManager();
        }
        return INSTANCE;
    }

    // -------------------------------
    // Public Methods
    // -------------------------------

    /**
     * Description: Turn all cards off ( start/restart game)
     */
    public void allCardTurnOff() {
    	/* A map that store the card by its index -> (suit, value) */
		mCardMap = new EnumMap<CardList, Card>(CardList.class);
		for (CardList index : CardList.values()) {
			Card card = new Card(0, 0, index, ResourceManager.mCardBackTR,
					ResourceManager.getInstance().vbom);
			final ITextureRegion cardTextureRegion = TextureRegionFactory
					.extractFromTexture(ResourceManager.cardGroundTA,
							index.getTexturePositionX(),
							index.getTexturePositionY(), Card.CARD_WIDTH,
							Card.CARD_HEIGHT);
			card.setmCardOriginalTextureRegion(cardTextureRegion);
			mCardMap.put(index, card);
		}
	}

    /* 
     * Arrange all anchors on tableau to prevent exceeding boarder 
     */
	public void arrangeAllAnchors() {
		for(int i = 0; i < ANCHORCNT; i++)
			((SpiderAnchor)(mAnchorList.get(i))).arrangeAnchor();
	}

	/**
	 * Init mAllCards that represents all cards that are used at current suit configuration
	 * @param pCardSuitCount current suit used
	 */
	public void initAllCards(int pCardSuitCount) {
		if (mAllCards != null)
			this.mAllCards.clear();
		
		/* Random Seed */
		long seed = System.nanoTime();
		
		if (pCardSuitCount == 1) {
			// TODO: options ->1 color suit
		} else if (pCardSuitCount == 2) {
			// TODO: options ->2 color suits
		} else {
			/* options ->4 color suits */
			for (CardList index : CardList.values())
				fourSuitCards.add(mCardMap.get(index));  // 52 standard cards	
			mAllCards = (ArrayList<Card>)fourSuitCards;
			
			/* Shuffle cards */
			Collections.shuffle( mAllCards, new Random(seed));
		}

	}

	public void initReadyCard() {
		float mAnchorXSpacing = this.getAnchorXspacing();
		
		if (mAllCards != null) {
			mAnchorList = new SparseArray<Anchor>();

			for (int i = 0; i < ANCHORCNT; i++) {
				float posX = mAnchorXSpacing + i
						* (Card.CARD_WIDTH + mAnchorXSpacing);

				/* Arrangement of anchors */
				mAnchorList.put(i, new SpiderAnchor());
				mAnchorList.get(i).setmAnchorX(posX);
				for (int j = 0; j < ANCHORINITCARDSCNT; j++) {
					((SpiderAnchor) mAnchorList.get(i)).appendCard(mAllCards
							.get(j + i * ANCHORINITCARDSCNT));
				}
			}
		}
		
		/* Init cards that are ready to be moved */
		if(mAnchorList != null){
            for(int i = 0; i < ANCHORCNT; i++) {
            	mAnchorList.get(i).getLastCard().onTurnOn();
            }
        }
				
		/* Init deck cards */
		List<Card> deckCards = mAllCards.subList(ANCHORCNT * ANCHORINITCARDSCNT, mAllCards.size());
        
		mDeckList = new SparseArray<ArrayList<Card>>();
		for(int i = 0; i < deckCnt; i++) {
			mDeckList.put(i, new ArrayList<Card>());
			for(int j = 0; j < ANCHORCNT; j ++) {
				if( j + i * ANCHORCNT < deckCards.size()) {
					addToDeck(i, deckCards.get(j + i * ANCHORCNT));
				}
			}
		}
	}
	
	public void dealCards() {
		if( deckCnt > 0 ) {
			int cardsToDeal = mDeckList.get(4 - deckCnt).size();
			for (int i = 0; i < cardsToDeal; i++) {
				Card localCard = mDeckList.get(4 - deckCnt).get(i);
				localCard.onTurnOn();
				((SpiderAnchor) mAnchorList.get(i)).appendCard(localCard);
//				mDeckList.remove(4 - deckCnt);
			}
			deckCnt --;
		} 
	}

	public static void moveCard(boolean pBool1, boolean pBool2) {
		//TODO: Define card move
	}
	
	
	// -------------------------------
    // Private Methods
    // -------------------------------
	
	 /**
     * Description: Add cards into deck 
     */
	private static void addToDeck(int pIndex, Card pCard) {
		mDeckList.get(pIndex).add(pCard);
	}
	
	private float getAnchorXspacing() {
		return ((ResourceManager.getInstance().cameraWidth - Card.CARD_WIDTH
				* ANCHORCNT) / (ANCHORCNT + 1));
	}

    // -------------------------------
    // Inner and Anonymous classes
    // -------------------------------
    /* Define Touchable area for a pile of cards */
    public static class AnchorRange {
        private float startX;
        private float startY;
        private float endX;
        private float endY;

        AnchorRange(float pstartX, float pendX, float pstartY, float pendY) {
            this.startX = pstartX;
            this.endX = pendX;
            this.startY = pstartY;
            this.endY = pendY;
        }

        public float getEndX() {
            return this.endX;
        }

        public float getEndY() {
            return this.endY;
        }

        public float getStartX() {
            return this.startX;
        }

        public float getStartY() {
            return this.startY;
        }

        public void setEndX(float pendX) {
            this.endX = pendX;
        }

        public void setEndY(float pendY) {
            this.endY = pendY;
        }

        public void setStartX(float pstartX) {
            this.startX = pstartX;
        }

        public void setStartY(float pstartY) {
            this.startY = pstartY;
        }
    }

}
