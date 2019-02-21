package com.work.vladimirs.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import java.util.Random;

public class BalloonActor extends BaseActor {
    private float speed;
    private float amplitude;
    private float oscillation;
    private float initialY;
    private float time;
    private int offsetX;

    public BalloonActor() {
        this.speed = 160 * MathUtils.random(0.5f, 2.0f);
        this.amplitude = 80 * MathUtils.random(0.5f, 2.0f);
        this.oscillation = 0.01f * MathUtils.random(0.5f, 2.0f);
        this.initialY = 120 * MathUtils.random(0.5f, 2.0f);
        this.time = 0;
        this.offsetX = -100;
        //Начальная точка спавна за экраном
        setX(offsetX);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
        //Установка стартовой точки слевой стороны окна
        float xPos = speed * time + offsetX;
        float yPos = amplitude * MathUtils.sin(oscillation * xPos) + initialY;
        setPosition(xPos, yPos);
    }
}
