package io.github.imperialpinetrees.heaventimemassacre;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.imperialpinetrees.heaventimemassacre.screens.GameScreen;

public class HeavenTimeMassacreMain extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}
}
