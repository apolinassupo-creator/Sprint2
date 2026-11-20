package io.github.Polina.charactors;

import static io.github.Polina.Main.SCR_HEIGHT;
import static io.github.Polina.Main.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {
    private Random random;
    private int x;
    private int gapY;
    private int distanceTubes;
    private  boolean isPointReceived;

    private int speed = 5;
    private int width = 300;
    private int height = 450;
    private int gapHeight = 400;
    private int padding = 100;

    private Texture textureUpperTube;
    private Texture textureDownTube;
    public boolean isHit(Bird bird) {
        if (bird.y< gapY - gapHeight / 2 && bird.x + bird.width-50 > x && bird.x < x){
            return true;
        }
        if (bird.y + bird.height-80 > gapY + gapHeight / 2 && bird.x + bird.width-80 > x && bird.x < x)
            return true;
        else{
            return  false;
        }
    }
    public void setPointReceived() {

        isPointReceived = true;
    }
    public boolean needAddPoint(Bird bird) {
        return bird.x > x + width-10 && !isPointReceived;
    }
    public Tube(int tubeCount, int tubeIdx) {
        random = new Random();

        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2)+50);
        distanceTubes = (SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceTubes * tubeIdx + SCR_WIDTH;

        textureUpperTube = new Texture("Tube/tube_flipped.png");
        textureDownTube = new Texture("Tube/tube.png");
    }
    public void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }
    public void move() {
        x -= speed;

        if (x < -width) {
            isPointReceived = false;
            x = SCR_WIDTH + distanceTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }
    public void dispose() {
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }

}
