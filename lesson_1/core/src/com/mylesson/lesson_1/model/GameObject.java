package com.mylesson.lesson_1.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;

abstract class GameObject {
    Polygon bounds;
    //Таже самая картинка, но её можно поворачивать и взаимодействовать с ней
    Sprite object;

    public GameObject(Texture texture, float x, float y, float width, float height) {
        object = new Sprite(texture);
        //указываем размеры спрайта
        object.setSize(width, height);
        //указываем центр поворота для нашего полигона
        object.setOrigin(width / 2f, height / 2f);
        //задаём позицию спрайта
        object.setPosition(x , y);

        bounds = new Polygon(new float[]{0f, 0f, width, 0f, width, height, 0f, height});
        //указываем позицию полигона
        bounds.setPosition(x , y);
        //указываем центр поворота для нашего полигона
        bounds.setOrigin(width / 2f, height / 2f);
    }

    public void draw(SpriteBatch batch) {
        object.setPosition(bounds.getX(), bounds.getY());
        object.setRotation(bounds.getRotation());
        object.draw(batch);
    }

    public Polygon getBounds() {
        return bounds;
    }
}
