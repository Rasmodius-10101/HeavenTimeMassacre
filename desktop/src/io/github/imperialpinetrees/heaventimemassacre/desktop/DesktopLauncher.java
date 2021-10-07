package io.github.imperialpinetrees.heaventimemassacre.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.imperialpinetrees.heaventimemassacre.HeavenTimeMassacreMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new HeavenTimeMassacreMain(), config);
	}
}
