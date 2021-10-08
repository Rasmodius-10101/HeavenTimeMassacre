package io.github.imperialpinetrees.heaventimemassacre.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class MapManager {

    public static final float unitScale = 1/16f;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    public static Vector2 mapDimensions;

    public MapManager() {
        mapDimensions = new Vector2(280 / 2, 250 / 2);
    }

    public void loadMap(String mapName) {
        tiledMap = new TmxMapLoader().load(mapName);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void renderMap(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        camera.zoom = .5f;
    }
}
