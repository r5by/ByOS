package edu.uta.byos.Managers;

import java.util.ArrayList;
import java.util.HashMap;

import edu.uta.byos.runtime.Anchor;
import edu.uta.byos.runtime.Card;

/**
* ********** [ ByOS ] ***********
* @Description A solitaire game
* @Class    | GameManager
*           | A class designed to keep track of
*           | certain data involved in game
*           | Pattern: Singleton
* @authors ruby_
* @version 1.0
* ***************************************
*/

public class GameManager {

    // -------------------------------
    // Fields
    // -------------------------------

	private static GameManager INSTANCE;
    /* 54 cards set, 44 were set turned-off at beginning */
    private static final int INITTURNOFFCOUNT = 44;
    /* 10 piles of cards on board */
	private static int ANCHORCOUNT = 10;
    /* 5 sets of cards available to be used */
	private static int DECKCOUNT = 5;
    /* Color suit: 1, 2 or 4; 1 by default */
    private int mCardColorCount = 1;

    /* One/Two/Four set of cards */
	private ArrayList<Card> oneArray = new ArrayList(); //one color suit
	private ArrayList<Card> twoArray = new ArrayList(); //two color suits
	private ArrayList<Card> threeArray = new ArrayList(); //four color suits
  	private ArrayList<Card> mAllCardList = new ArrayList(); //Used to init suits

	public static final int MAXZINDEX = 99;
	public static float mAnchorY = 1.0f;

    public static float mAnchorSpacing;
    public static HashMap<Integer, Anchor> mAnchorList;
    public static HashMap<Integer, ArrayList<Card>> mDeckList;
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
    /* Add cards into deck */
    public static void addToDeck(int pIndex, Card pCard) {
        ((ArrayList)mDeckList.get(Integer.valueOf(pIndex))).add(pCard);
    }

    public void allCardTurnOff() {
		//TODO: Turn all cards off (resart game)
	}

	public void arrangementAllAnchor() {
		//TODO: Arrange all piles of cards on tableau
	}

	public void initAllCard(int pCardColorCount) {
        this.mAllCardList.clear();
        if(pCardColorCount == 1) {

        } else if (pCardColorCount == 2) {
            //TODO: options ->2 color suits
        } else {
            //TODO: options ->4 color suits
        }

	}

	public void initReadyCard(boolean pBoolean) {
		//TODO: Init cards that are ready to be moved
	}

	public void initTurnOffCard(boolean pBoolean) {
		//TODO: Init cards that are turned off at beginning
	}

	public static void moveCard(boolean pBool1, boolean pBool2) {
		//TODO: Define card move
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
