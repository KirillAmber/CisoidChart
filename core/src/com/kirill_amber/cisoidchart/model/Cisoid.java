/*
 * Copyright (c) 2020.
 * @author Kirill_Amber
 */

package com.kirill_amber.cisoidchart.model;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

import java.util.ArrayList;

//y^2 = x^3/(2a-x), где a - радиус окружности
//length_graph - длинна окружности

public class Cisoid {
    private ShapeRenderer shapeRenderer;
    private float a;
    private float x;
    private float yy;
    private float length_graph;

    public Cisoid(float a, float length_graph){
        this.a = a;
        this.length_graph = length_graph;
        shapeRenderer = new ShapeRenderer();
    }

    public float getA() {
        return a;
    }

    public float getLength_graph() {
        return length_graph;
    }

    public float getX(){
        return x;
    }
    public void drawGraph(float centerX, float centerY, float xAxis_boundary, float yAxis_boundary, Matrix4 camera){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Point);
        for(int i = 0; i < length_graph; i++){
            x = i;
            if(x == 2*a){
                continue;
            }
            yy = (float)Math.pow(x,3)/(2*a-x);
            if(centerX + x > xAxis_boundary) {
                break;
            }
            if(centerY+(float)Math.sqrt(yy) > yAxis_boundary) {
                break;
            }
            shapeRenderer.point(centerX+x, centerY+(float)Math.sqrt(yy), 0);
            shapeRenderer.setProjectionMatrix(camera);
        }
        shapeRenderer.end();
    }
}
