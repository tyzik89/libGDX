package com.work.vladimirs.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.work.vladimirs.StarfishGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//Изменение настроек
		config.width = 1000;
		config.height = 800;
		config.title = "Starfish Collector";

		new LwjglApplication(new StarfishGame(), config);
	}
}
