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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.kirill_amber.cisoidchart.model.Axis;
import com.kirill_amber.cisoidchart.model.Cisoid;
import com.kirill_amber.cisoidchart.model.Square;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChartScreen implements Screen {
    private final int SIZE_GRID = 20;
    private boolean isClickedAnimation;
    private boolean isClickedPlot;

    private SpriteBatch sb;
    private OrthographicCamera camera;
    private Square square;
    private Axis xAxis;
    private Axis yAxis;
    private Axis[] arrayVerticalLines;
    private Axis[] arrayHorizontalLines;

    private float difference_between_axles;
    private float line_spacing;

    //widget
    private Skin skin;
    private ScalingViewport scalingViewport;
    private Stage stage;
    private Label label_of_a;
    private Label label_of_length;
    private Label label_of_warning;
    private TextField length_textField;
    private TextField a_textField;
    private TextButton plotButton;
    private TextButton animationButton;
    //widget


    private Cisoid cisoid;

    //инициализация
    @Override
    public void show() {
        isClickedAnimation = false;
        isClickedPlot = false;
        sb = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2, camera.viewportHeight/2);

        skin = new Skin(Gdx.files.internal("uisking.json"));
        skin.addRegions(new TextureAtlas("uisking.atlas"));

        scalingViewport = new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(scalingViewport, sb);
        Gdx.input.setInputProcessor(stage);

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


        //widget
        plotButton = new TextButton("Plot", skin);
        plotButton.setSize(200, 40);
        plotButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                Pattern pattern = Pattern.compile("[\\D&&[^\\.]]");
                Matcher matcher_a_text_field = pattern.matcher(a_textField.getText());
                Matcher matcher_length_text_field = pattern.matcher(length_textField.getText());

                if(!matcher_a_text_field.find() && !matcher_length_text_field.find()) {
                    isClickedPlot = true;
                    label_of_warning.setVisible(false);
                    cisoid.setA(Float.parseFloat(a_textField.getText()));
                    cisoid.setLength_graph(Float.parseFloat(length_textField.getText()));
                    square.setStep(0);
                }
                else label_of_warning.setVisible(true);
            }
        });

        animationButton = new TextButton("Animation", skin);
        animationButton.setSize(200, 40);
        animationButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                isClickedAnimation = !isClickedAnimation;
                square.setStep(0);
            }
        });

        length_textField = new TextField("2000", skin);
        length_textField.setSize(200, 40);

        label_of_length = new Label("Entry length:", skin);
        label_of_length.setSize(200,40);
        label_of_length.setColor(Color.BLACK);

        a_textField = new TextField("100", skin);
        a_textField.setSize(200,40);

        label_of_a = new Label("Entry radius(a):", skin);
        label_of_a.setSize(200, 40);
        label_of_a.setColor(Color.BLACK);

        label_of_warning = new Label("INVALID INPUT DATA", skin);
        label_of_warning.setSize(200, 40);
        label_of_warning.setColor(Color.RED);
        label_of_warning.setVisible(false);

        stage.addActor(plotButton);
        stage.addActor(animationButton);
        stage.addActor(length_textField);
        stage.addActor(label_of_length);
        stage.addActor(a_textField);
        stage.addActor(label_of_a);
        stage.addActor(label_of_warning);
        //widget


    }
    //масштабирование
    public void update(float dt){
        camera.update();
        scalingViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        xAxis.setX(Gdx.graphics.getWidth()-Gdx.graphics.getWidth());
        xAxis.setY(Gdx.graphics.getHeight()/2.0f);
        xAxis.setX2(Gdx.graphics.getWidth());
        xAxis.setY2(Gdx.graphics.getHeight()/2.0f);

        yAxis.setX(Gdx.graphics.getWidth()/2.0f);
        yAxis.setY(Gdx.graphics.getHeight()-Gdx.graphics.getHeight());
        yAxis.setX2(Gdx.graphics.getWidth()/2.0f);
        yAxis.setY2(Gdx.graphics.getHeight());



       if(Gdx.graphics.getWidth() <= Gdx.graphics.getHeight()) {
           square.setSize(Gdx.graphics.getWidth()/10.0f);

           yAxis.setY2(Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()-Gdx.graphics.getWidth())/2.0f);
           yAxis.setY((Gdx.graphics.getHeight()-Gdx.graphics.getWidth())/2.0f);

       }
       else if(Gdx.graphics.getHeight() < Gdx.graphics.getWidth()){
           square.setSize(Gdx.graphics.getHeight()/10.0f);

           xAxis.setX2(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()-Gdx.graphics.getHeight())/2.0f);
           xAxis.setX((Gdx.graphics.getWidth()-Gdx.graphics.getHeight())/2.0f);

       }

        System.out.println("x length " + (xAxis.getX2()-xAxis.getX()) + "\ny length " + (yAxis.getY2()-yAxis.getY()));
        System.out.println("");

       difference_between_axles = xAxis.getX2()-yAxis.getX();
       line_spacing = difference_between_axles/(SIZE_GRID/2.0f);

       //
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


       //widget
        plotButton.setPosition(camera.viewportWidth- plotButton.getWidth(), 0);
        animationButton.setPosition(plotButton.getX(), plotButton.getHeight());
        length_textField.setPosition(animationButton.getX(), animationButton.getY()+animationButton.getHeight());
        label_of_length.setPosition(length_textField.getX(), length_textField.getY()+length_textField.getHeight());
        a_textField.setPosition(label_of_length.getX(), label_of_length.getY()+label_of_length.getHeight());
        label_of_a.setPosition(a_textField.getX(), a_textField.getY()+a_textField.getHeight());
        label_of_warning.setPosition(label_of_a.getX(), label_of_a.getY()+label_of_a.getHeight());
        //widget

    }

    //отображение
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);

        sb.begin();
        sb.setProjectionMatrix(camera.combined);

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


        float centerX = (xAxis.getX() + xAxis.getX2())/2.0f;
        float centerY = (yAxis.getX() + xAxis.getX2())/2.0f;

        //cisoid.drawGraph(centerX, centerY, xAxis.getX2(), yAxis.getY2(), camera.combined);
        if(isClickedPlot) {
            cisoid.drawGraph(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f,
                    xAxis.getX2(), yAxis.getY2(), camera.combined);
        }

        if(isClickedAnimation) {
            square.getShapeRender().begin(ShapeRenderer.ShapeType.Filled);
            square.draw(cisoid.getA(), cisoid.getX(), Gdx.graphics.getWidth() / 2.0f,
                    Gdx.graphics.getHeight() / 2.0f, camera.combined);
            square.getShapeRender().end();
        }

        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
