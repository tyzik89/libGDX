package com.mylesson.lesson_1.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

abstract class GameObject {
    Rectangle bounds;
    //Таже самая картинка, но её можно поворачивать и взаимодействовать с ней
    Sprite object;

    public GameObject(Texture texture, float x, float y, float width, float height) {
        bounds = new Rectangle(x , y, width, height);
        object = new Sprite(texture);
    }

    public void draw(SpriteBatch batch) {
        object.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        object.draw(batch);
    }
}
