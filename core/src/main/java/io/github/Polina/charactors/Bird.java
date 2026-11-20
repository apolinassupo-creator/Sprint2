package io.github.Polina.charactors;

import static io.github.Polina.Main.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Bird {

    int x, y;
    int width, height;
    int speed;
    int jumpHeight;
    final int maxJump = 150;
    boolean jump;
    int frameCounter;
    Texture[] framesArray;

    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > SCR_HEIGHT) return false;
        else return true;
    }



    public Bird(int x, int y, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        frameCounter = 0;
        this.speed = speed;

        framesArray = new Texture[]{
            new Texture("birdTiles/bird0.png"),
            new Texture("birdTiles/bird1.png"),
            new Texture("birdTiles/bird2.png"),
            new Texture("birdTiles/bird3.png"),
            new Texture("birdTiles/bird4.png"),
            new Texture("birdTiles/bird3.png"),
            new Texture("birdTiles/bird2.png"),
            new Texture("birdTiles/bird1.png")
        };
    }
    public void setY(int y) {
        this.y = y;
    }
    public void onClick() {
        jump = true;
        jumpHeight = maxJump + y;
    }

    public void fly() {
        if (y >= jumpHeight) {
            jump = false;
        }
        if (jump) {
            y += speed;
        } else {
            y -= speed;
        }
    }

    public void draw(Batch batch) {
        int frameMultiplier = 9;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }
}
