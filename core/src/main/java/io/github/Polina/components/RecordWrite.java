package io.github.Polina.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class RecordWrite {

    private Preferences downlend;
    private int record;


    public RecordWrite() {
        downlend = Gdx.app.getPreferences("game_prefs");
        record = downlend.getInteger("high_score", 0);
    }


    public void checkRecord(int point) {
        if (point > record) {
            record = point;

            downlend.putInteger("high_score", record);
            downlend.flush();
        }
    }
    public void resetRecord() {
        record = 0;

        downlend.putInteger("high_score", 0);
        downlend.flush();
    }


    public int getRecord() {
        return record;
    }
}
