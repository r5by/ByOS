package edu.uta.byos;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;
import edu.uta.byos.Managers.ResourceManager;
import edu.uta.byos.Managers.SceneManager.SceneType;

/**
* @author ruby_ - CSE_UTA
*/

public abstract class ManagedScene extends Scene {

    // -------------------------------
    // Fields
    // -------------------------------
    protected Engine engine;
    protected Activity activity;
    protected float cameraWidth;
    protected float cameraHeight;
    protected VertexBufferObjectManager vbom;

    // -------------------------------
    // Constructors
    // -------------------------------
    public ManagedScene() {
        this.engine = ResourceManager.getInstance().engine;
        this.activity = ResourceManager.getInstance().activity;
        this.vbom = ResourceManager.getInstance().vbom;
        this.cameraWidth = ResourceManager.getInstance().cameraWidth;
        this.cameraHeight = ResourceManager.getInstance().cameraHeight;
    }

    // -------------------------------
    // Public Methods
    // -------------------------------
    public abstract void onShowScene();
    public abstract void onBackKeyPressed();
    public abstract SceneType getSceneType();
    public abstract void onDisposeScene();

}
