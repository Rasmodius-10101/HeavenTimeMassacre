package io.github.imperialpinetrees.heaventimemassacre.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.imperialpinetrees.heaventimemassacre.Player;
import io.github.imperialpinetrees.heaventimemassacre.util.MapManager;

public class GameScreen implements Screen {

    private static final String TAG = GameScreen.class.getCanonicalName();

    private final Game game;

    private Player player;

    // Using ShapeRenderer for now until we get art
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;

    private MapManager map;

    private static final float width = Gdx.graphics.getWidth();
    private static final float height = Gdx.graphics.getHeight();


    public GameScreen(Game game) {
        this.game = game;
        map = new MapManager();
    }

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
        viewport = new ExtendViewport(width, height);
        viewport.apply(true);
        camera = new OrthographicCamera();
        player = new Player(20, 20);

        //camera.zoom = .5f;
        camera.setToOrtho(false, width, height);
        map.loadMap("untitled.tmx");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        camera.position.set(MapManager.mapDimensions.x, MapManager.mapDimensions.y, 0f);
        camera.update();

        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        player.renderPlayer(shapeRenderer);

        map.renderMap(camera);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
