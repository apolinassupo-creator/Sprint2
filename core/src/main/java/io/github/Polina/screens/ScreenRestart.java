package io.github.Polina.screens;


import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.Polina.Main;

import io.github.Polina.buttons.ButtonMenu;
import io.github.Polina.components.MoveFrame;
import io.github.Polina.components.PointCounter;
import io.github.Polina.buttons.TextButton;
import io.github.Polina.components.RecordWrite;

public class ScreenRestart implements Screen {
    Audio audio = Gdx.audio;
    Main myGdxGame;
    TextButton buttonRestart;
    MoveFrame moveFrame;
    RecordWrite recordWrite;
    PointCounter pointCounter;
    ButtonMenu buttonMenu;
    int countPoints;
    private Music music;

    public ScreenRestart(Main myGdxGame) {
        this.myGdxGame = myGdxGame;
        recordWrite = new RecordWrite();
        pointCounter = new PointCounter(23, 90);
        buttonRestart = new TextButton(900, 580, "Restart");
        moveFrame = new MoveFrame("Frame/restart_bg.png");
        buttonMenu = new ButtonMenu(450,350,"MENU");

    }

    @Override
    public void show() {
    music= Gdx.audio.newMusic(Gdx.files.internal("Music/resertMusic.mp3"));
    music.setLooping(true);
    music.setVolume(0.5f);
    music.play();

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonRestart.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
            if (buttonMenu.isMenuHit((int) touch.x, (int) touch.y)){
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }

        ScreenUtils.clear(0.6f, 0.8f, 1, 1);

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();


        moveFrame.draw(myGdxGame.batch);
        buttonRestart.draw(myGdxGame.batch);
        buttonMenu.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, recordWrite.getRecord());

        myGdxGame.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        music.stop();
    }

    @Override
    public void dispose() {
        music.dispose();
        moveFrame.dispose();
        buttonRestart.dispose();
        buttonMenu.dispose();
    }
}


