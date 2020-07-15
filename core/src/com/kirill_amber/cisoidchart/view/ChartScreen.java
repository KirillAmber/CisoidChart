/*
 * Copyright (c) 2020.
 * @author Kirill_Amber
 */

package com.kirill_amber.cisoidchart.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kirill_amber.cisoidchart.Main;
import com.kirill_amber.cisoidchart.model.Axis;
import com.kirill_amber.cisoidchart.model.Cisoid;
import com.kirill_amber.cisoidchart.model.Square;


public class ChartScreen implements Screen {
    private final int SIZE_GRID = 20;

    private SpriteBatch sb;
    private OrthographicCamera camera;
    private Square square;
    private Axis xAxis;
    private Axis yAxis;
    private Axis[] arrayVerticalLines;
    private Axis[] arrayHorizontalLines;

    private float difference_between_axles;
    private float line_spacing;

    Cisoid cisoid;

    @Override
    public void show() {
        sb = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2, camera.viewportHeight/2);

        square = new Square(0, 0,
                100, Color.BLUE);
        xAxis = new Axis();
        xAxis.setColor(Color.RED);
        yAxis = new Axis();
        yAxis.setColor(Color.RED);


        arrayVerticalLines = new Axis[SIZE_GRID];
        for(int i = 0; i < arrayVerticalLines.length; i++){
            arrayVerticalLines[i] = new Axis();
        }
        arrayHorizontalLines = new Axis[SIZE_GRID];
        for(int i = 0; i < arrayHorizontalLines.length; i++){
            arrayHorizontalLines[i] = new Axis();
        }

        cisoid = new Cisoid(100, 2000);
    }
    //как масштабировать
    public void update(float dt){
        camera.update();

        xAxis.setX(Gdx.graphics.getWidth()-Gdx.graphics.getWidth());
        xAxis.setY(Gdx.graphics.getHeight()/2.0f);
        xAxis.setX2(Gdx.graphics.getWidth());
        xAxis.setY2(Gdx.graphics.getHeight()/2.0f);

        yAxis.setX(Gdx.graphics.getWidth()/2.0f);
        yAxis.setY(Gdx.graphics.getHeight()-Gdx.graphics.getHeight());
        yAxis.setX2(Gdx.graphics.getWidth()/2.0f);
        yAxis.setY2(Gdx.graphics.getHeight());



       if(Gdx.graphics.getWidth() <= Gdx.graphics.getHeight()) {
           square.setSize(Gdx.graphics.getWidth()/4.0f);

           yAxis.setY2(Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()-Gdx.graphics.getWidth())/2.0f);
           yAxis.setY((Gdx.graphics.getHeight()-Gdx.graphics.getWidth())/2.0f);

       }
       else if(Gdx.graphics.getHeight() < Gdx.graphics.getWidth()){
           square.setSize(Gdx.graphics.getHeight()/4.0f);

           xAxis.setX2(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()-Gdx.graphics.getHeight())/2.0f);
           xAxis.setX((Gdx.graphics.getWidth()-Gdx.graphics.getHeight())/2.0f);

       }

       difference_between_axles = xAxis.getX2()-yAxis.getX();
       line_spacing = difference_between_axles/(SIZE_GRID/2.0f);

        int count = 0;
        for(int i = 0; i < SIZE_GRID/2; i++){
            //правая половина
            arrayVerticalLines[i].setX(yAxis.getX()+count*line_spacing);
            arrayVerticalLines[i].setX2(yAxis.getX()+count*line_spacing);
            arrayVerticalLines[i].setY(yAxis.getY());
            arrayVerticalLines[i].setY2(yAxis.getY2());
            //верхняя половина
            arrayHorizontalLines[i].setX(xAxis.getX());
            arrayHorizontalLines[i].setX2(xAxis.getX2());
            arrayHorizontalLines[i].setY(xAxis.getY()+count*line_spacing);
            arrayHorizontalLines[i].setY2(xAxis.getY()+count*line_spacing);
            count++;
        }
        count = 0;
        for(int i = SIZE_GRID/2; i < SIZE_GRID; i++){
            //левая половина
            arrayVerticalLines[i].setX(yAxis.getX()-count*line_spacing);
            arrayVerticalLines[i].setX2(yAxis.getX()-count*line_spacing);
            arrayVerticalLines[i].setY(yAxis.getY());
            arrayVerticalLines[i].setY2(yAxis.getY2());
            //нижняя половина
            arrayHorizontalLines[i].setX(xAxis.getX());
            arrayHorizontalLines[i].setX2(xAxis.getX2());
            arrayHorizontalLines[i].setY(xAxis.getY()-count*line_spacing);
            arrayHorizontalLines[i].setY2(xAxis.getY()-count*line_spacing);
            count++;
        }

       square.setX(Gdx.graphics.getWidth()/2.0f-square.getSize()/2.0f);
       square.setY(Gdx.graphics.getHeight()/2.0f-square.getSize()/2.0f);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        square.draw(camera.combined);
        for(Axis element : arrayVerticalLines){
            if(element.getX()<=xAxis.getX2())
            element.draw(camera.combined);
            else break;
        }
        for(Axis element:arrayHorizontalLines){
            if(element.getY()<=yAxis.getY2())
            element.draw(camera.combined);
            else break;
        }
        xAxis.draw(camera.combined);
        yAxis.draw(camera.combined);

        //cisoid.drawGraph((xAxis.getX2()-xAxis.getX())/2.0f, (yAxis.getY2()-yAxis.getY())/2.0f, camera.combined);

        cisoid.drawGraph(Gdx.graphics.getWidth()/2.0f, Gdx.graphics.getHeight()/2.0f,
                xAxis.getX2(), yAxis.getY2(), camera.combined);
        sb.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        sb.dispose();
    }
}
