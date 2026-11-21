package io.github.Polina.screens;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


import io.github.Polina.Main;
import io.github.Polina.buttons.ButtonExit;
import io.github.Polina.buttons.ButtonStart;
import io.github.Polina.components.MoveFrame;
import io.github.Polina.components.PointCounter;
import io.github.Polina.components.RecordWrite;

public class ScreenMenu implements Screen {
    Main myGdxGame;
    Audio audio = Gdx.audio;
    RecordWrite recordWrite;
    MoveFrame menuFrame;
    PointCounter pointCounter;
    ButtonExit buttonExit;
    ButtonStart buttonStart;

    public ScreenMenu(Main myGdxGame){
        this.myGdxGame=myGdxGame;
        pointCounter = new PointCounter(100,100);
        recordWrite = myGdxGame.recordWrite;
        menuFrame = new MoveFrame("Frame/menu_bg.png");
        buttonStart = new ButtonStart(350,350,"START");
        buttonExit = new ButtonExit(410,150,"Exit");
    }
    private Music music;

    @Override
    public void show() {
        music = Gdx.audio.newMusic(Gdx.files.internal("Music/menuMusic.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.6f, 0.8f, 1, 1);
        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonStart.isStartHit((int) touch.x, (int) touch.y)) {
                //recordWrite.resetRecord();
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
            if (buttonExit.isExitHit((int) touch.x, (int) touch.y)) {
                Gdx.app.exit();
            }
        }
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();


        menuFrame.draw(myGdxGame.batch);
        buttonStart.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch,recordWrite.getRecord());
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
        pointCounter.dispose();
        buttonExit.dispose();
        buttonStart.dispose();
        menuFrame.dispose();
       music.dispose();
    }
}
