package com.work.vladimirs.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class BaseGame extends Game{
    //Используется для записи ресурсов (тексты, метки и т.д.) общих, для разных экранов
    protected Skin skin;

    public BaseGame() {
        skin = new Skin();
    }

    @Override
    public abstract void create();

    @Override
    public void dispose() {
        skin.dispose();
    }

    public Skin getSkin() {
        return skin;
    }
}
