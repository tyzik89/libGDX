package com.work.vladimirs;

import com.badlogic.gdx.Game;
import com.work.vladimirs.levels.BalloonLevel;

public class BallonBusterGame extends Game {
	
	@Override
	public void create () {
		BalloonLevel balloonLevel = new BalloonLevel(this);
		setScreen(balloonLevel);
	}
}
