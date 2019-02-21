package com.work.vladimirs.levels;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.work.vladimirs.game.BaseGame;

public abstract class BaseScreen implements Screen, InputProcessor{
    protected BaseGame baseGame;

    protected Stage mainStage;
    protected Stage uiStage;
    protected Table uiTable;

    public final int viewWidth = 640;
    public final int viewHeight = 480;

    private boolean paused;

    public BaseScreen(BaseGame baseGame) {
        this.baseGame = baseGame;
        mainStage = new Stage(new FitViewport(viewWidth, viewHeight));
        uiStage = new Stage(new FitViewport(viewWidth, viewHeight));
        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);
        paused = false;
        InputMultiplexer inputMultiplexer = new InputMultiplexer(this, uiStage, mainStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        create();
    }

    public abstract void create();

    public abstract void update(float dt);

    //Код игрового цикла - update, затем render
    @Override
    public void render(float dt) {
        uiStage.act(dt);
        if (!isPaused()) {
            mainStage.act(dt);
            update(dt);
        }

        //Рендер
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.draw();
    }

    //Пауза игрового мира
    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean p) {
        paused = p;
    }

    public void togglePaused() {
        paused = !paused;
    }

    //метод регульрующий размер объектов при изменении размера окна

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

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

    }

    //методы для InputProcessor interface*****************************************
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
}
