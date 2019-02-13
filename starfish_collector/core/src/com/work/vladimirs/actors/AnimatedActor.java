package com.work.vladimirs.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.compression.lzma.Base;

import java.util.HashMap;

public class AnimatedActor extends BaseActor{

    private float elapsedTime;
    private Animation activeAnim;
    private String activeName;
    private HashMap<String, Animation> animationStorage;

    public AnimatedActor() {
        super();
        elapsedTime = 0;
        activeAnim = null;
        activeName = null;
        animationStorage = new HashMap<String, Animation>();
    }

    public void storeAnimation(String name, Animation anim) {
        animationStorage.put(name, anim);
        if (activeName == null)
            setActiveAnimation(name);
    }

    public void storeAnimation(String name, Texture texture) {
        TextureRegion region = new TextureRegion(texture);
        TextureRegion[] frames = {region};
        Animation animation = new Animation(1.0f, frames);
        storeAnimation(name, animation);
    }

    public void setActiveAnimation(String name) {
        if (!animationStorage.containsKey(name)) {
            System.out.println("No animation: " + name);
            return;
        }

        if (activeName != null && activeName.equals(name))
            return;

        activeName = name;
        activeAnim = animationStorage.get(name);
        elapsedTime = 0;

        TextureRegion texture = (TextureRegion) activeAnim.getKeyFrame(0);
        setWidth(texture.getTexture().getWidth());
        setHeight(texture.getTexture().getHeight());
    }

    public String getAnimationName() {
        return activeName;
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        elapsedTime += dt;
    }

    public void draw(Batch batch, float parentAlpha) {
        this.setTextureRegion(((TextureRegion) activeAnim.getKeyFrame(0)).getTexture());
        super.draw(batch, parentAlpha);
    }
}
