package com.work.vladimirs.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.work.vladimirs.actors.BaseActor;
import com.work.vladimirs.actors.PhysicsActor;

import java.util.ArrayList;

public class TurtleLevel extends BaseScreen {

    private BaseActor ocean;
    private ArrayList<BaseActor> rockList;
    private ArrayList<BaseActor> starfishList;
    private PhysicsActor turtle;
    private int mapWidth;
    private int mapHeight;

    //Музыка
    private float audioVolume;
    private Sound waterDrop;
    private Music instrumental;
    private Music oceanSurf;

    public TurtleLevel(Game game) {
        super(game);
        mapWidth = 800;
        mapHeight = 600;
    }

    @Override
    public void create() {
        ocean = new BaseActor();
        ocean.setTextureRegion(new Texture("ocean.jpg"));
        ocean.setPosition(0 ,0);
        mainStage.addActor(ocean);

        BaseActor overlay = ocean.clone();
        overlay.setPosition(-50,-50);
        overlay.setColor(1, 1, 1, 0.25f);
        uiStage.addActor(overlay);

        BaseActor rock = new BaseActor();
        rock.setTextureRegion(new Texture("rock.png"));
        rock.setWidth(100);
        rock.setHeight(100);
        rock.setEllipseBoundary();
        rockList = new ArrayList<BaseActor>();
        int[] rockCoords = {200, 0, 200, 100, 250, 200, 360, 200, 470, 200};
        for (int i = 0; i < 5; i++) {
            BaseActor rocks = rock.clone();
            rocks.setPosition(rockCoords[2 * i], rockCoords[2 * i + 1]);
            mainStage.addActor(rocks);
            rockList.add(rocks);
        }

        BaseActor starfish = new BaseActor();
        starfish.setTextureRegion(new Texture("starfish.png"));
        starfish.setWidth(100);
        starfish.setHeight(100);
        starfish.setEllipseBoundary();

        starfishList = new ArrayList<BaseActor>();
        int[] starfishCoords = {400, 100, 100, 400, 650, 400};
        for (int i = 0; i < 3; i++) {
            BaseActor starfishes = starfish.clone();
            starfishes.setPosition(starfishCoords[2 * i], starfishCoords[2 * i + 1]);
            mainStage.addActor(starfishes);
            starfishList.add(starfishes);
        }

        turtle = new PhysicsActor();
        TextureRegion[] frames = new TextureRegion[6];
        for (int n = 1; n <= 6; n++) {
            String filename = "animations/turtle" + n + ".png";
            Texture texture = new Texture(filename);
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear );
            frames[n - 1] = new TextureRegion(texture);
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);

        Animation animationSwim = new Animation(0.1f, framesArray, Animation.PlayMode.LOOP);
        turtle.storeAnimation("swim", animationSwim);

        Texture restTexture = new Texture("turtle.png");
        turtle.storeAnimation("rest", restTexture);

        turtle.setOrigin(turtle.getWidth() / 2, turtle.getHeight() / 2);
        turtle.setPosition(20, 20);
        turtle.setRotation(90);
        turtle.setEllipseBoundary();
        turtle.setMaxSpeed(100);
        turtle.setDeceleration(200);
        mainStage.addActor(turtle);

        //Инициализация музыки
//        waterDrop = Gdx.audio.newSound(new FileHandle(""));
   //     instrumental = Gdx.audio.newMusic(new FileHandle(""));
    //    oceanSurf = Gdx.audio.newMusic(new FileHandle(""));

//        audioVolume = 0.8f;
///        instrumental.setLooping(true);
//        instrumental.setVolume(audioVolume);
//        instrumental.play();
//        oceanSurf.setLooping(true);
//        oceanSurf.setVolume(audioVolume);
//        oceanSurf.play();
    }

    @Override
    public void update(float dt) {
        turtle.setAccelerationXY(0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            turtle.rotateBy(90 * dt);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            turtle.rotateBy(-90 * dt);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            turtle.accelerateForward(100);

        //Установка корректной анимации
        if (turtle.getSpeed() > 1 && turtle.getAnimationName().equals("rest"))
            turtle.setActiveAnimation("swim");
        if (turtle.getSpeed() < 1 && turtle.getAnimationName().equals("swim"))
            turtle.setActiveAnimation("rest");

        //Привязка черепахи к экрану
        turtle.setX(MathUtils.clamp(turtle.getX(), 0, mapWidth - turtle.getWidth()));
        turtle.setY(MathUtils.clamp(turtle.getY(), 0, mapHeight - turtle.getHeight()));

        for (BaseActor r : rockList) {
            turtle.overlaps(r, true);
        }

        ArrayList<BaseActor> removeList = new ArrayList<BaseActor>();
        for (BaseActor s : starfishList) {
            if (turtle.overlaps(s, false))
                removeList.add(s);
        }

        for (BaseActor b : removeList) {
            b.remove();                 //удаление из Stage
            starfishList.remove(b);     //удаление из списка
            //waterDrop.play(audioVolume); //Проигрывание музыки
        }
    }

    @Override
    public void dispose() {
//        waterDrop.dispose();
//        instrumental.dispose();
//        oceanSurf.dispose();
    }
}
