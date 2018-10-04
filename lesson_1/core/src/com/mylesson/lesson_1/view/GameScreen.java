package com.mylesson.lesson_1.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mylesson.lesson_1.model.Car;

public class GameScreen implements Screen {
    private Texture carTexture;
    //Отрисовщик текстур
    private SpriteBatch batch;
    private Car car;
    private OrthographicCamera camera;

    //Время между кадров
    public static float deltaCff;

    @Override
    public void show() {
        batch = new SpriteBatch();
        //Путь к файлу относительный
        carTexture = new Texture(Gdx.files.internal("car.png"));
        //Установка фильтрации для текстуры
        carTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        car = new Car(carTexture, 0, 0, 1f, 1f  * 2f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        deltaCff = delta;
        //Применяем матрицу проекции к нашему отрисовщику
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        car.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //Соотношение сторон
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(20f, 20f * aspectRatio);
        //Установка ЗУМа камеры
        camera.zoom = 0.6f;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        carTexture.dispose();
        batch.dispose();
    }
}
