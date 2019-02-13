package com.work.vladimirs.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.work.vladimirs.actors.AnimatedActor;
import com.work.vladimirs.actors.BaseActor;

public class CheeseLevel extends BaseScreen {
    private AnimatedActor mousey;
    private BaseActor cheese;
    private BaseActor floor;
    private BaseActor winText;
    private boolean win;

    private float timeElapsed;
    private Label timeLabel;

    //game world dimesnsions
    final int mapWidth = 800;
    final int mapHeight = 800;


    public CheeseLevel(Game game) {
        super(game);
    }

    @Override
    public void create() {
        timeElapsed = 0;

        floor = new BaseActor();
        floor.setTexture(new Texture("tiles.jpg"));
        floor.setPosition(0, 0);
        mainStage.addActor(floor);

        cheese = new BaseActor();
        cheese.setTexture(new Texture("cheese.png"));
        cheese.setOrigin(cheese.getWidth()/2, cheese.getHeight()/2);
        cheese.setPosition(400, 300);
        mainStage.addActor(cheese);

        mousey = new AnimatedActor();
        TextureRegion[] frames = new TextureRegion[4];
        for (int n = 0; n < 4; n++) {
            String filename = "animations/mouse_animation/mouse" + n + ".png";
            Texture texture = new Texture(filename);
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            frames[n] = new TextureRegion(texture);
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);
        Animation animMouse = new Animation(0.1f, framesArray, Animation.PlayMode.LOOP_PINGPONG);
        mousey.setAnimation(animMouse);
        mousey.setOrigin(mousey.getWidth()/2, mousey.getHeight()/2);
        mousey.setPosition(20, 20);
        mainStage.addActor(mousey);

        winText = new BaseActor();
        winText.setTexture(new Texture("win.png"));
        winText.setPosition(170, 60);
        winText.setVisible(false);
        uiStage.addActor(winText);

        BitmapFont font = new BitmapFont();
        String text = "Time: 0";
        Label.LabelStyle style =  new Label.LabelStyle(font, Color.NAVY);
        timeLabel = new Label(text, style);
        timeLabel.setFontScale(2);
        timeLabel.setPosition(500, 440);
        uiStage.addActor(timeLabel);

        win = false;
    }

    @Override
    public void update(float dt) {
        mousey.velocityX = 0;
        mousey.velocityY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            mousey.velocityX -= 150;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            mousey.velocityX += 150;
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            mousey.velocityY += 150;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            mousey.velocityY -= 150;


        //Границы игрового мира для мыши
        mousey.setX(MathUtils.clamp(mousey.getX(), 0, mapWidth - mousey.getWidth()));
        mousey.setY(MathUtils.clamp(mousey.getY(), 0, mapHeight - mousey.getHeight()));

        //Проверка условия победы - мышь должна накрыть сыр
        Rectangle cheeseRectangle = cheese.getBoundingRectangle();
        Rectangle mouseyRectangle = mousey.getBoundingRectangle();

        if (!win && mouseyRectangle.contains(cheeseRectangle)) {
            win = true;
            //Вращение сыра
            Action spinShrinkFadeOut = Actions.parallel(
                    Actions.alpha(1),                                   //set transparency value
                    Actions.rotateBy(360, 1),       //rotation amount, duration
                    Actions.scaleBy(0, 0, 1),   //x,y amount, duration
                    Actions.fadeOut(1)                            //duration of fade out
            );
            cheese.addAction(spinShrinkFadeOut);
            //Текст победа с анимацией
            Action fadeInColorCycleForever = Actions.sequence(
                    Actions.alpha(0),
                    Actions.show(),
                    Actions.fadeIn(2),
                    Actions.forever(
                            Actions.sequence(
                                    //color shade to approach, duration
                                    Actions.color(new Color(1, 0, 0, 1), 1),
                                    Actions.color(new Color(0, 0, 1, 1), 1)
                            )
                    )
            );
            winText.addAction(fadeInColorCycleForever);
        }

        if (!win) {
            timeElapsed += dt;
            timeLabel.setText("Time: " + (int)timeElapsed);
        }

        //Вычисление позиции камеры
        Camera cam = mainStage.getCamera();
        //центрирование камеры на игроке
        cam.position.set(mousey.getX() + mousey.getOriginX(), mousey.getY() + mousey.getOriginY(), 0);
        //Ограничение движения камеры в границах
        cam.position.x = MathUtils.clamp(cam.position.x, viewWidth/2, mapWidth - viewWidth/2);
        cam.position.y = MathUtils.clamp(cam.position.y, viewHeight/2, mapHeight - viewHeight/2);
        cam.update();
    }

    //InputProcessor методы для обработчика дискретного ввода
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.M)
            game.setScreen(new CheeseMenu(game));

        if (keycode == Input.Keys.P)
            togglePaused();

        return false;
    }
}
