package com.work.vladimirs.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.work.vladimirs.game.BalloonBusterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//Изменение настроек
        //config.width = 800;
        //config.height = 600;
        config.title = "Ballon Buster";

		new LwjglApplication(new BalloonBusterGame(), config);
	}
}
