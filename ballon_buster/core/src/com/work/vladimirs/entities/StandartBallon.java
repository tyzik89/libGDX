package com.work.vladimirs.entities;

import com.badlogic.gdx.graphics.Texture;
import com.work.vladimirs.actors.BallonActor;

import java.util.Random;

public class StandartBallon extends BallonActor {

    public StandartBallon() {
        setTexture(new Texture("balloon.png"));
        setColor(generateClorComponent(), generateClorComponent(), generateClorComponent(),1);
        setSize(76, 100);
    }

    /**
     * Генерирует float значение цвета и нормирует его в светлые тона
     * @return Рандомную составляющую цвета
     */
    private float generateClorComponent() {
        return new Random().nextFloat() * (1.0f - 0.1f) + 0.2f;
    }
}
