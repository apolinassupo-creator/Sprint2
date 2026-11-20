package io.github.Polina.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class RecordWrite {

    private Preferences downlend;
    private int record;

    // Загружаем сохранённый рекорд
    public RecordWrite() {
        downlend = Gdx.app.getPreferences("game_prefs");
        record = downlend.getInteger("high_score", 0);
    }

    // Проверяем, появился ли новый рекорд
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

    // Возвращаем рекорд
    public int getRecord() {
        return record;
    }
}
