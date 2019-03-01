package com.work.vladimirs.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.work.vladimirs.levels.LevelFirst;
import com.work.vladimirs.levels.MenuScreen;

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

		TextButton.TextButtonStyle uiTextButtonStyle = new TextButton.TextButtonStyle();
		uiTextButtonStyle.font = uiFont;
		uiTextButtonStyle.fontColor = Color.NAVY;

		Texture buttonOffTexture = new Texture(new FileHandle("buttons/buttonOff.png"));
		super.skin.add("buttonOff", new NinePatch(buttonOffTexture, 26, 26,16, 20));
		uiTextButtonStyle.up = super.skin.getDrawable("buttonOff");

		Texture buttonOnTexture = new Texture(new FileHandle("buttons/buttonOn.png"));
		super.skin.add("buttonOn", new NinePatch(buttonOnTexture, 26, 26,16, 20));
		uiTextButtonStyle.over = super.skin.getDrawable("buttonOn");
		uiTextButtonStyle.overFontColor = Color.BLUE;

		super.skin.add("uiTextButtonStyle", uiTextButtonStyle);

		//LevelFirst balloonLevel = new LevelFirst(this);
		setScreen(new MenuScreen(this));
	}
}
