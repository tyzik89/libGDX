package com.mylesson.lesson_1.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.mylesson.lesson_1.view.GameScreen;

public class CarController {
    private Polygon carBounds;

    public CarController(Polygon carBounds) {
        this.carBounds = carBounds;
    }

    float speed, velocity = 0.2f;
    public void handle() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            speed += velocity;
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
                speed -= velocity;


        carBounds.setPosition(
                        carBounds.getX() + MathUtils.cosDeg(carBounds.getRotation() + 90) * speed * GameScreen.deltaCff,
                        carBounds.getY() + MathUtils.sinDeg(carBounds.getRotation() + 90) * speed * GameScreen.deltaCff);
    }
}
