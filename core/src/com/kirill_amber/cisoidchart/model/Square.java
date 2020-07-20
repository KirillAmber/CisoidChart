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
    private float step;

    public Square(){
        super();
        size = 10;
        step = 0;
    }

    public Square(float x, float y, float size, Color color) {
        super(x, y, color);
        this.size = size;
        step = 0;
    }

    @Override
    public void setColor(Color color) {
        shapeRenderer.setColor(color);
    }

    public float getSize(){
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public ShapeRenderer getShapeRender(){
        return shapeRenderer;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public void draw(float a, float length, float centerX, float centerY, Matrix4 camera){
        x = step;
            y = (float) Math.pow(x, 3) / (2 * a - x);
            shapeRenderer.rect(centerX + x-size/2.0f, centerY + (float) Math.sqrt(y)-size/2.0f, size, size);
            shapeRenderer.setProjectionMatrix(camera);
        if(step <= length)
        step++;
        else step = 0;
        shapeRenderer.setProjectionMatrix(camera);
    }

}
