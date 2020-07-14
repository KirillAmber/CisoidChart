/*
 * Copyright (c) 2020.
 * @author Kirill_Amber
 */

package com.kirill_amber.cisoidchart;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.kirill_amber.cisoidchart.view.ChartScreen;


public class Main extends Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;//720
    public static final String TITLE = "Sunset Dog";
    private Screen chartScreen;
    @Override
    public void create() {
        chartScreen = new ChartScreen();
        setScreen(chartScreen);
    }
}
