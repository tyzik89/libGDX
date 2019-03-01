package com.work.vladimirs.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.work.vladimirs.actors.BalloonActor;
import com.work.vladimirs.actors.BaseActor;
import com.work.vladimirs.entities.DangerBalloon;
import com.work.vladimirs.entities.StandartBalloon;
import com.work.vladimirs.game.BaseGame;

import java.util.Random;

public class LevelFirst extends BaseScreen {
    private BaseActor background;

    private float spawnTimer;
    private float spawnInterval;

    private int popped;
    private int escaped;
    private int clickCount;

    private Label poppedLabel;
    private Label escapedLabel;
    private Label hitRatioLabel;

    //Музыка и звуки
    private Music levelMusic;
    private Sound boom;
    private Sound badBoom;

    final int mapWidth = 640;
    final int mapHeight = 480;

    public LevelFirst(BaseGame baseGame) {
        super(baseGame);
    }

    @Override
    public void create() {
        background = new BaseActor();
        background.setTexture(new Texture("sky.png"));
        background.setPosition(0, 0);
        mainStage.addActor(background);

        spawnTimer = 0;
        spawnInterval = 0.7f;

        //Интерфейс
        BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle(font, Color.NAVY);

        popped = 0;
        poppedLabel = new Label("Popped: 0", baseGame.getSkin(), "uiLabelStyle");

        escaped = 0;
        escapedLabel = new Label("Escaped: 0", baseGame.getSkin(), "uiLabelStyle");

        clickCount = 0;
        hitRatioLabel = new Label("Hit Ration: ---", baseGame.getSkin(), "uiLabelStyle");



        //Инициализация музыки
        levelMusic = Gdx.audio.newMusic(new FileHandle("music/banjo_race.mp3"));
        boom = Gdx.audio.newSound(new FileHandle("sounds/boom/09040.mp3"));
        badBoom = Gdx.audio.newSound(new FileHandle("sounds/boom/09038.mp3"));

        levelMusic.setLooping(false);
        levelMusic.setVolume(0.1f);
        levelMusic.play();

        //Пауза игры
        Texture pauseTexture = new Texture(new FileHandle("buttons/pause.png"));
        baseGame.getSkin().add("pauseImage", pauseTexture);
        Button.ButtonStyle pauseStyle = new Button.ButtonStyle();
        pauseStyle.up = baseGame.getSkin().getDrawable("pauseImage");
        Button pauseButton = new Button(pauseStyle);
        pauseButton.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        LevelFirst.super.togglePaused();
                        return true;
                    }
                });


        super.uiTable.pad(5);
        super.uiTable.add(poppedLabel);
        super.uiTable.add().expandX();
        super.uiTable.add(escapedLabel);
        super.uiTable.add().expandX();
        super.uiTable.add(hitRatioLabel);
        super.uiTable.add().expandX();
        super.uiTable.row();
        super.uiTable.add().colspan(5).expandY();
        super.uiTable.add(pauseButton).colspan(5).bottom();
    }

    @Override
    public void update(float dt) {
        spawnTimer += dt;

        //Проверка времени для следующего респавна баллона
        if (spawnTimer > spawnInterval) {
            spawnTimer -= spawnInterval;
            final BalloonActor ballon;
            if (new Random().nextInt(11) < 8) {
                ballon = new StandartBalloon();
            } else {
                ballon = new DangerBalloon();
            }
            ballon.addListener(
                    new InputListener() {
                        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                            popped++;
                            if (ballon instanceof StandartBalloon)
                                boom.play(1.0f);
                            else
                                badBoom.play(1.0f);
                            ballon.remove();
                            return true;
                        }
                    }
            );
            mainStage.addActor(ballon);
        }

        //Удаление шара, который вышел за пределы экрана
        for (Actor actor : mainStage.getActors()) {
            if (actor.getX() > mapWidth || actor.getY() > mapHeight) {
                escaped++;
                actor.remove();
            }
        }

        //Обновление пользовательского интерфейса
        poppedLabel.setText("Popped: " + popped);
        escapedLabel.setText("Escaped: " + escaped);
        if (clickCount > 0) {
            int percent = (int) (100.0 * popped / clickCount);
            hitRatioLabel.setText("Hit Ration: " + percent + "%");
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        clickCount++;
        return false;
        //return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void dispose() {
        levelMusic.dispose();
        boom.dispose();
        badBoom.dispose();
    }
}
