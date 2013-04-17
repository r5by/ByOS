package edu.uta.byos;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import edu.uta.byos.Managers.ResourceManager;

/**
* **************************** [ ByOS ] *****************************
* @Description A solitaire Game
* @class    | GameMenu
*           | This class is the game menu which can be called during
*           | game play
* @authors ruby_
* @version 1.0
* *******************************************************************
*/

public class GameMenu extends MenuScene implements IOnMenuItemClickListener{

	// -------------------------------
    // Fields
    // -------------------------------
	private Sprite menuBackground;
    private static final int MENU_Y_GAP = 10;
    private static final int MENU_X_GAP = 30;

    private final int MENU_NEWGAME = 0;
    private final int MENU_HINT = 1;
    private final int MENU_DEAL = 2;
    private final int MENU_OPTIONS = 3;
    private final int MENU_EXIT = 4;
    
    // -------------------------------
    // Constructor
    // -------------------------------
    public GameMenu(Camera pCamera) {
    	super(pCamera);
    }
    
    // -------------------------------
    // Public Methods
    // -------------------------------
    public void onPopulateMenu() {
    	showBackground();
    	showMenuChildScene();
    }
    
    // -------------------------------
    // Private Methods
    // -------------------------------
    private void showBackground() {
        menuBackground = new Sprite(0, 0, ResourceManager.menuBackgroundTR, ResourceManager.getInstance().vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        menuBackground.setPosition(  (ResourceManager.getInstance().cameraWidth - menuBackground.getWidth()) * 0.5f,
        		(ResourceManager.getInstance().cameraHeight - menuBackground.getHeight()) * 0.5f);
        attachChild(menuBackground);
        }

    private void showMenuChildScene() {
        setPosition( 0, 0);

        final IMenuItem newgameMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_NEWGAME, ResourceManager.newgameTiledTextureRegion, ResourceManager.getInstance().vbom), 1.2f, 1);
        final IMenuItem hintMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_HINT, ResourceManager.hintTiledTextureRegion, ResourceManager.getInstance().vbom), 1.2f, 1);
        final IMenuItem dealMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_DEAL, ResourceManager.dealTiledTextureRegion, ResourceManager.getInstance().vbom), 1.2f, 1);
        final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, ResourceManager.optionsTiledTextureRegion, ResourceManager.getInstance().vbom), 1.2f, 1);
        final IMenuItem exitMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_EXIT, ResourceManager.exitTiledTextureRegion, ResourceManager.getInstance().vbom), 1.2f, 1);

        addMenuItem(newgameMenuItem);
        addMenuItem(hintMenuItem);
        addMenuItem(dealMenuItem);
        addMenuItem(optionsMenuItem);
        addMenuItem(exitMenuItem);

        buildAnimations();
        setBackgroundEnabled(false);

        newgameMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() - MENU_Y_GAP * 6f);
        hintMenuItem.setPosition(optionsMenuItem.getX() - MENU_X_GAP, optionsMenuItem.getY() - MENU_Y_GAP * 3f);
        dealMenuItem.setPosition(optionsMenuItem.getX() + MENU_X_GAP, optionsMenuItem.getY() - MENU_Y_GAP * 3f);
        optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() + MENU_Y_GAP );
        exitMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() + MENU_Y_GAP * 3f);

        setOnMenuItemClickListener(this);
   }
	
    /* TODO: Define Menu click events */
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
        case MENU_NEWGAME:
            return true;
        case MENU_HINT:
            return true;
        case MENU_DEAL:
            return true;
		case MENU_OPTIONS:
			return true;
        case MENU_EXIT:
            return true;
			default:
			return false;
		}
	}

}
