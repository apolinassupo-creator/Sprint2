package io.github.Polina.screens;


import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.ScreenUtils;

import static io.github.Polina.Main.SCR_HEIGHT;
import static io.github.Polina.Main.SCR_WIDTH;

import io.github.Polina.Main;
import io.github.Polina.charactors.Bird;
import io.github.Polina.charactors.Tube;
import io.github.Polina.components.MoveFrame;
import io.github.Polina.components.PointCounter;
import io.github.Polina.components.RecordWrite;

public class ScreenGame implements Screen {
    MoveFrame moveFrame;
    Audio audio = Gdx.audio;
    RecordWrite recordWrite;
    Main myGdxGame;
    Bird bird;
    PointCounter pointCounter;
    ScreenMenu menu;
    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;


    private int tubeCount =3;
    private Tube[] tubes;
    private boolean isGameOver;
    int countPoints;
    private Music music;

    public ScreenGame(Main myGdxGame) {
        this.myGdxGame = myGdxGame;

        initTubes();
        recordWrite = new RecordWrite();
        moveFrame = new MoveFrame("Frame/game_bg.png");
        bird = new Bird(20, SCR_HEIGHT / 2, 10, 250, 200);
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
    }


    @Override
    public void show() {
        music = Gdx.audio.newMusic(Gdx.files.internal("Music/gameMusic.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        countPoints=0;
        isGameOver = false;
        bird.setY(SCR_HEIGHT / 2);
        initTubes();
    }

    @Override
    public void render(float delta) {
        if (isGameOver) {
            myGdxGame.screenRestart.countPoints = countPoints;
            myGdxGame.setScreen(myGdxGame.screenRestart);

        }

        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
        moveFrame.move();
        bird.fly();
        if (!bird.isInField()){
            isGameOver = true;
        }
        for (Tube tube : tubes) {
            tube.move();
            if(tube.isHit(bird)){
                isGameOver = true;
            }
            else if (tube.needAddPoint(bird)) {
                countPoints+=1;
                recordWrite.checkRecord(countPoints);
                tube.setPointReceived();
                System.out.println(countPoints);

            }
        }

        ScreenUtils.clear(0.6f, 0.8f, 1, 1);


        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

        myGdxGame.batch.begin();
        moveFrame.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) {
            tube.draw(myGdxGame.batch);
        }
        pointCounter.draw(myGdxGame.batch, countPoints);
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
        bird.dispose();
        pointCounter.dispose();
        moveFrame.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
    }

    private void initTubes() {
        tubes = new Tube[tubeCount];

        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }
}
