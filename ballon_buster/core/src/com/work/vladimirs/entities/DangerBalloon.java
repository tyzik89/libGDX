package com.work.vladimirs.entities;

import com.badlogic.gdx.graphics.Texture;
import com.work.vladimirs.actors.BalloonActor;

public class DangerBalloon extends BalloonActor {

    public DangerBalloon() {
        setTexture(new Texture("balloon_danger.png"));
        setColor(0.5f, 0.5f, 0.5f,1);
        setSize(76, 100);
    }
}
