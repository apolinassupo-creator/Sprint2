package io.github.Polina.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class ButtonStart {
    BitmapFont font;

    String text;
    Texture texture;

    int x, y;
    int textX, textY;
    int buttonWidth, buttonHeight;
    int textWidth, textHeight;

    public ButtonStart(int x, int y, String text) {
        this.text = text;
        this.x = x;
        this.y = y;

        font = new BitmapFont();
        font.getData().scale(5f);
        font.setColor(Color.WHITE);

        GlyphLayout gl = new GlyphLayout(font, text);
        textWidth = (int) gl.width;
        textHeight = (int) gl.height;

        texture = new Texture("button/buttonStart.png");
        buttonWidth = texture.getWidth();
        buttonHeight = texture.getHeight();
        System.out.println(buttonWidth+" "+buttonHeight);

        textX = x + (buttonWidth - textWidth) / 2;
        textY = y + ((buttonHeight/2+10) + textHeight) / 2;
    }
    public boolean isStartHit(int tx, int ty) {
        return tx >= x && tx <= x + buttonWidth
            && ty >= y && ty <= y + buttonHeight;
    }
    public void draw(Batch batch) {
        batch.draw(texture, x, y, buttonWidth, buttonHeight);
        font.draw(batch, text, textX, textY);
    }

    public void dispose() {
        texture.dispose();
        font.dispose();
    }
}
