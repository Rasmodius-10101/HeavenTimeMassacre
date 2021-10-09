package io.github.imperialpinetrees.heaventimemassacre;

import com.badlogic.gdx.Game;

import io.github.imperialpinetrees.heaventimemassacre.screens.GameScreen;

public class HeavenTimeMassacreMain extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}


}
