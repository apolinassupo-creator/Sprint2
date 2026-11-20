package io.github.Polina.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import io.github.Polina.Main;

public class MoveFrame {
    Texture texture;
    int texture1X;
    int texture2X;
    int speed = 2;
    public MoveFrame(String pathToTexture){
        texture1X = 0;
        texture2X = Main.SCR_WIDTH;
        texture=new Texture(pathToTexture);
    }
    public void move() {
        texture1X -= speed;
        texture2X -= speed;

        if (texture1X <= -Main.SCR_WIDTH) {
            texture1X = Main.SCR_WIDTH;
        }
        if(texture2X<=-Main.SCR_WIDTH){
            texture2X=Main.SCR_WIDTH;
        }
    }
    public void draw(Batch batch) {
        batch.draw(texture, texture1X, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        batch.draw(texture, texture2X, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        batch.draw(texture, texture1X, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
    }
    public void dispose(){

        texture.dispose();
    }

}
