package com.work.vladimirs.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.work.vladimirs.game.BaseGame;

public class MenuScreen extends BaseScreen {

    public MenuScreen(BaseGame baseGame) {
        super(baseGame);
    }

    @Override
    public void create() {
        Texture menuBackgroundTexture = new Texture(new FileHandle("menus/main-background.jpg"));
        menuBackgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        super.baseGame.getSkin().add("menuBackgroundTexture", menuBackgroundTexture);
        super.uiTable.background(super.baseGame.getSkin().getDrawable("menuBackgroundTexture"));

        //Создание кнопок старт и выход
        TextButton startButton = new TextButton("Start", baseGame.getSkin(), "uiTextButtonStyle");
        startButton.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        baseGame.setScreen(new LevelFirst(baseGame));
                    }
                });
        TextButton quitButton = new TextButton("Quit", baseGame.getSkin(), "uiTextButtonStyle");
        quitButton.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        Gdx.app.exit();
                    }
                });

        float w = startButton.getWidth();
        uiTable.add(startButton);
        uiTable.add(quitButton).width(w);
        uiTable.row();

    }

    @Override
    public void update(float dt) {

    }
}
