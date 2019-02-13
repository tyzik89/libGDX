package com.work.vladimirs.entities;

import com.badlogic.gdx.graphics.Texture;
import com.work.vladimirs.actors.BallonActor;

public class DangerBallon extends BallonActor {

    public DangerBallon() {
        setTexture(new Texture("balloon_danger.png"));
        setColor(0.5f, 0.5f, 0.5f,1);
        setSize(76, 100);
    }
}
