package com.work.vladimirs.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.work.vladimirs.levels.BalloonLevel;

public class BalloonBusterGame extends BaseGame {

	public BalloonBusterGame() {
		super();
	}

	@Override
	public void create () {
		//Инициализация ресурсов, общих для всех экранов и хранящая базу Skin
        BitmapFont uiFont = new BitmapFont(new FileHandle("fonts/jazz_ball.fnt"));
        uiFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        super.skin.add("uiFont", uiFont);

        Label.LabelStyle uiLabelStyle = new Label.LabelStyle(uiFont, Color.ROYAL);
        super.skin.add("uiLabelStyle", uiLabelStyle);

		BalloonLevel balloonLevel = new BalloonLevel(this);
		setScreen(balloonLevel);
	}
}
