/*
 * Copyright (c) 2020.
 * @author Kirill_Amber
 */

package com.kirill_amber.cisoidchart.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public class Axis extends AbstractFigure {
    private float x2;
    private float y2;
    public Axis(){
        super();
        x2 = 10;
        y2 = 10;
    }

    @Override
    public void setColor(Color color) {
        shapeRenderer.setColor(color);
    }

    public Axis(float x, float y, float x2, float y2, Color color){
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }
    public void setSize(float x, float y, float x2, float y2){
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }

    @Override
    public void draw(Matrix4 camera) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(x, y, x2, y2);
        shapeRenderer.setProjectionMatrix(camera);
        shapeRenderer.end();
    }

}
