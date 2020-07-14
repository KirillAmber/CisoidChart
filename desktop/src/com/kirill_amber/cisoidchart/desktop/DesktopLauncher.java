/*
 * Copyright (c) 2020.
 * @author Kirill_Amber
 */

package com.kirill_amber.cisoidchart.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kirill_amber.cisoidchart.Main;

import static com.kirill_amber.cisoidchart.Main.HEIGHT;
import static com.kirill_amber.cisoidchart.Main.TITLE;
import static com.kirill_amber.cisoidchart.Main.WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		config.title = TITLE;
		new LwjglApplication(new Main(), config);
	}
}
