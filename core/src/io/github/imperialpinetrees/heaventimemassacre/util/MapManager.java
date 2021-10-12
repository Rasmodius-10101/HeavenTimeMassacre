package io.github.imperialpinetrees.heaventimemassacre.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import io.github.imperialpinetrees.heaventimemassacre.Player;

public class MapManager {

    public static final float unitScale = 1/16f;

    private static TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    private static MapLayer spawnLayer;

    public static Vector2 mapDimensions;
    public static Vector2 playerStartPos;

    public MapManager() {
        mapDimensions = new Vector2(280 / 2, 250 / 2);
        playerStartPos = new Vector2(0,0);
    }

    public void loadMap(String mapName) { //TODO: Fix the method to make it safer in terms of errors
        tiledMap = new TmxMapLoader().load(mapName);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void renderMap(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        camera.zoom = .5f;
    }

    public static void setPlayerSpawnLocation() {
        spawnLayer = tiledMap.getLayers().get("SPAWN");
        for (MapObject object : spawnLayer.getObjects()) {
            if (object.getName().equalsIgnoreCase("SPAWN")) {
                ((RectangleMapObject)object).getRectangle().getPosition(playerStartPos);
            }
        }
    }

    public static Vector2 getMapDimensions() {
        return mapDimensions;
    }

    public static void setMapDimensions(Vector2 mapDimensions) {
        MapManager.mapDimensions = mapDimensions;
    }
}
