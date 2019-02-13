package com.work.vladimirs;

import com.badlogic.gdx.Game;
import com.work.vladimirs.screens.CheeseMenu;

public class CheesePleaseGame extends Game {

    @Override
    public void create() {
        CheeseMenu menu = new CheeseMenu(this);
        setScreen(menu);
    }
}
