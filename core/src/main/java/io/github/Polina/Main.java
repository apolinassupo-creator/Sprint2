package io.github.Polina;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.Polina.components.RecordWrite;
import io.github.Polina.screens.ScreenGame;
import io.github.Polina.screens.ScreenMenu;
import io.github.Polina.screens.ScreenRestart;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;
    public ScreenRestart screenRestart;
    public ScreenMenu screenMenu;


    public ScreenGame screenGame;
    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        screenMenu = new ScreenMenu(this);
        screenGame = new ScreenGame(this);
        screenRestart = new ScreenRestart(this);
        setScreen(screenMenu);
    }


    @Override
    public void dispose() {
        screenGame.dispose();
        screenMenu.dispose();
        batch.dispose();
    }
}

