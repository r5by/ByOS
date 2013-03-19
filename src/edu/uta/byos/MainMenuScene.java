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
import edu.uta.byos.Managers.SceneManager.SceneType;

public class MainMenuScene extends ManagedScene implements IOnMenuItemClickListener {

    // -------------------------------
    // Fields
    // -------------------------------
	private Sprite menuBackground;
    private final float MID_POSITION_X =  ResourceManager.getInstance().cameraWidth * 0.5f;
    private final float MID_POSITION_Y =  ResourceManager.getInstance().cameraHeight * 0.5f;
    private final int MENU_Y_GAP = 10;
    private final int MENU_X_GAP = 30;

    private MenuScene menuChildScene;
    private final int MENU_NEWGAME = 0;
    private final int MENU_HINT = 1;
    private final int MENU_DEAL = 2;
    private final int MENU_OPTIONS = 3;
    private final int MENU_EXIT = 4;

    // -------------------------------
    // Public Methods
    // -------------------------------

	@Override
	public void onShowScene() {
		showBackground();
        showMenuChildScene();
	}

	@Override
	public void onBackKeyPressed() {
        System.exit(0);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}

	@Override
	public void onDisposeScene() {
		// TODO Auto-generated method stub

	}

    // -------------------------------
    // Private Methods
    // -------------------------------
    private void showBackground() {
        menuBackground = new Sprite(0, 0, ResourceManager.menuBackgroundTextureRegion, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        menuBackground.setPosition(  MID_POSITION_X - menuBackground.getWidth() * 0.5f,
        							 MID_POSITION_Y - menuBackground.getHeight() * 0.5f);
        attachChild(menuBackground);
        }

    private void showMenuChildScene() {
        menuChildScene = new MenuScene(ResourceManager.getInstance().engine.getCamera());
        menuChildScene.setPosition( 0, 0);

        final IMenuItem newgameMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_NEWGAME, ResourceManager.newgameTiledTextureRegion, vbom), 1.2f, 1);
        final IMenuItem hintMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_HINT, ResourceManager.hintTiledTextureRegion, vbom), 1.2f, 1);
        final IMenuItem dealMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_DEAL, ResourceManager.dealTiledTextureRegion, vbom), 1.2f, 1);
        final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, ResourceManager.optionsTiledTextureRegion, vbom), 1.2f, 1);
        final IMenuItem exitMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_EXIT, ResourceManager.exitTiledTextureRegion, vbom), 1.2f, 1);

        menuChildScene.addMenuItem(newgameMenuItem);
        menuChildScene.addMenuItem(hintMenuItem);
        menuChildScene.addMenuItem(dealMenuItem);
        menuChildScene.addMenuItem(optionsMenuItem);
        menuChildScene.addMenuItem(exitMenuItem);

        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);

        newgameMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() - MENU_Y_GAP * 6f);
        hintMenuItem.setPosition(optionsMenuItem.getX() - MENU_X_GAP, optionsMenuItem.getY() - MENU_Y_GAP * 3f);
        dealMenuItem.setPosition(optionsMenuItem.getX() + MENU_X_GAP, optionsMenuItem.getY() - MENU_Y_GAP * 3f);
        optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() + MENU_Y_GAP );
        exitMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() + MENU_Y_GAP * 3f);

        menuChildScene.setOnMenuItemClickListener(this);

       setChildScene(menuChildScene);
   }

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
