package edu.uta.byos;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;
import edu.uta.byos.Managers.ResourceManager;
import edu.uta.byos.Managers.SceneManager.SceneType;

/**
* **************************** [ ByOS ] *****************************
* @Description A solitaire Game
* @class    | ManagedScene
*           | An abstract class for all types of scene to extent
* @authors ruby_
* @version 1.0
* *******************************************************************
*/

public abstract class ManagedScene extends Scene {

    // -------------------------------
    // Fields
    // -------------------------------

    // -------------------------------
    // Constructors
    // -------------------------------
    public ManagedScene() {
    	onCreateScene();
    }

    // -------------------------------
    // Public Methods
    // -------------------------------
    public abstract void onCreateScene();
    public abstract void onBackKeyPressed();
    public abstract SceneType getSceneType();
    public abstract void onDisposeScene();

}
