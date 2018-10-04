package com.mylesson.lesson_1.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mylesson.lesson_1.control.CarController;

public class Car extends GameObject {
    private CarController carController;

    public Car(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
        carController = new CarController(bounds);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        carController.handle();
    }
}
