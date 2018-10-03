package com.mylesson.lesson_1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mylesson.lesson_1.view.GameScreen;

public class Main extends Game{
    private Screen gameScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
