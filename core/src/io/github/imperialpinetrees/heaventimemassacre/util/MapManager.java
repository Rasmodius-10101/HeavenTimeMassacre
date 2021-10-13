package io.github.imperialpinetrees.heaventimemassacre.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import io.github.imperialpinetrees.heaventimemassacre.Entities.Player;

public class MapManager {

    //I don't know
    public static TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    //layers
    private static MapLayer spawnLayer;
    public static MapLayer collisionLayer;
    public static final float unitScale = 1/16f;

    //variables
    public static Vector2 mapDimensions;
    public static Vector2 playerStartPos;

    //Arrays
    public static Array<Rectangle> collisionArray;

    //Other Classes
    private static Player player;

    public MapManager() {
        mapDimensions = new Vector2(280 / 2, 250 / 2);
        playerStartPos = new Vector2(0,0);

    }

    public void loadMap(String mapName) { //TODO: Fix the method to make it safer in terms of errors
        tiledMap = new TmxMapLoader().load(mapName);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        setCollision();
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

    public static void setCollision(){
        collisionLayer = tiledMap.getLayers().get("COLLISION");
        collisionArray = new Array<Rectangle>();
        for (MapObject object : collisionLayer.getObjects()) {
            if (object.getName().equalsIgnoreCase("COLLISION")) {
                collisionArray.add(((RectangleMapObject)object).getRectangle());
            }
        }
    }

    public static void checkCollision(){

        for (Rectangle e : collisionArray){
            if (Player.getCollisionRectangle().overlaps(e)) {
                Player.setPosition(Player.getXPos(), e.height + Player.getGravity() - 1);
                Player.landed();
            }

            if (Player.getXPos() < 2) {
                Player.setPosition(2, Player.getYPos());
            }
        }
    }




    public static Vector2 getMapDimensions() {
        return mapDimensions;
    }

    public static void setMapDimensions(Vector2 mapDimensions) {
        MapManager.mapDimensions = mapDimensions;
    }

    public static Array<Rectangle> getCollisionArray() {
        return collisionArray;
    }

    public static void setCollisionArray(Array<Rectangle> collisionArray) {
        MapManager.collisionArray = collisionArray;
    }

    public static MapLayer getCollisionLayer() {
        return collisionLayer;
    }


}
