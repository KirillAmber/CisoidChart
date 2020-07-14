/*
 * Copyright (c) 2020.
 * @author Kirill_Amber
 */

package com.kirill_amber.cisoidchart.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public abstract class AbstractFigure {
    protected ShapeRenderer shapeRenderer;
    protected float x;
    protected float y;

    public AbstractFigure(){
        x = 0;
        y = 0;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.GREEN);
    }
    public AbstractFigure(float x, float y, Color color){
        this.x = x;
        this.y = y;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(color);
    }
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public float getX() { return x; }

    public float getY() {
        return y;
    }
    public abstract void setColor(Color color);
    public abstract void draw(Matrix4 camera);

}
