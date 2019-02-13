package com.work.vladimirs.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseActor extends Actor {

    private TextureRegion textureRegion;
    private Polygon boundingPolygon;

    public BaseActor() {
        super();
        textureRegion = new TextureRegion();
        boundingPolygon = null;
    }

    public void setTextureRegion(Texture texture) {
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        textureRegion.setRegion(texture);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a);
        if (isVisible())
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void setRectangleBoundary() {
        float w = getWidth();
        float h = getWidth();
        float[] vertices = {0, 0, w, 0, w, h, 0, h};
        boundingPolygon = new Polygon(vertices);
        boundingPolygon.setOrigin(getOriginX(), getOriginY());
    }

    public void setEllipseBoundary() {
        int n = 8;
        float w = getWidth();
        float h = getHeight();
        float[] vertices = new float[2 * n];
        for (int i = 0; i < n; i++) {
            float t = i * 6.28f / n;
            //x-coordinate
            vertices[2*i] = w/2 * MathUtils.cos(t) + w/2;
            //y-coordinate
            vertices[2*i+1] = h/2 * MathUtils.sin(t) + h/2;
            boundingPolygon = new Polygon(vertices);
            boundingPolygon.setOrigin(getOriginX(), getOriginY());
        }
    }

    public Polygon getBoundingPolygon() {
        boundingPolygon.setPosition(getX(), getY());
        boundingPolygon.setRotation(getRotation());
        return boundingPolygon;
    }

    /**
     * Определеет пересечение полигонов двух BaseActor объектов
     * Если resolve == true тогда при пересечении, то перемещать BaseActor
     * как минимум до их НЕпересечения
     * @param other
     * @param resolve
     * @return
     */
    public boolean overlaps(BaseActor other, boolean resolve) {
        Polygon poly1 = this.getBoundingPolygon();
        Polygon poly2 = other.getBoundingPolygon();

        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return false;

        Intersector.MinimumTranslationVector vector = new Intersector.MinimumTranslationVector();
        boolean polyOverlap = Intersector.overlapConvexPolygons(poly1, poly2, vector);
        if (polyOverlap && resolve) {
            this.moveBy(vector.normal.x * vector.depth, vector.normal.y * vector.depth);
        }

        float significant = 0.5f;
        return (polyOverlap && (vector.depth > significant));
    }

    private void copy(BaseActor original) {
        this.textureRegion = new TextureRegion(original.textureRegion);
        if (original.boundingPolygon != null) {
            this.boundingPolygon = new Polygon(original.boundingPolygon.getVertices());
            this.boundingPolygon.setOrigin(original.getOriginX(), original.getOriginY());
        }
        this.setPosition(original.getX(), original.getY());
        this.setOriginX(original.getOriginX());
        this.setOriginY(original.getOriginY());
        this.setWidth(original.getWidth());
        this.setHeight(original.getHeight());
        this.setColor(original.getColor());
        this.setVisible(original.isVisible());
    }

    public BaseActor clone() {
        BaseActor newbie = new BaseActor();
        newbie.copy(this);
        return newbie;
    }
}
