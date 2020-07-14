/*
 * Copyright (c) 2020.
 * @author Kirill_Amber
 */

package com.kirill_amber.cisoidchart.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public class Square extends AbstractFigure {
    private float size;

    public Square(){
        super();
        size = 10;
    }

    @Override
    public void setColor(Color color) {
        shapeRenderer.setColor(color);
    }

    public Square(float x, float y, float size, Color color) {
        super(x, y, color);
        this.size = size;
    }

    public float getSize(){
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public void draw(Matrix4 camera){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, size, size);
        shapeRenderer.setProjectionMatrix(camera);
        shapeRenderer.end();
    }
}
