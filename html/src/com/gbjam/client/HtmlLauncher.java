package com.gbjam.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.gbjam.GBJam3;

public class HtmlLauncher extends GwtApplication {

	public static final int GAME_WIDTH = 160;
	public static final int GAME_HEIGHT = 144;
	
    @Override
    public GwtApplicationConfiguration getConfig () {
            return new GwtApplicationConfiguration(GAME_WIDTH, GAME_HEIGHT);
    }

    @Override
    public ApplicationListener getApplicationListener () {
            return new GBJam3();
    }
}