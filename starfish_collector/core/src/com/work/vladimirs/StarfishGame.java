package com.work.vladimirs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.work.vladimirs.screens.TurtleLevel;

public class StarfishGame extends Game {

	@Override
	public void create () {
		TurtleLevel level = new TurtleLevel(this);
		setScreen(level);
	}
}
