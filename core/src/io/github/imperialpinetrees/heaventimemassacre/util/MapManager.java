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
    public static Rectangle leftBounds;
    public static Rectangle rightBounds;
    public static Rectangle floorBounds;

    //Arrays
    private static Array<Rectangle> collisionArray;


    //Other Classes


    public MapManager() {
        mapDimensions = new Vector2(280 / 2, 250 / 2);
        playerStartPos = new Vector2(0,0);

    }

    public void loadMap(String mapName) { //TODO: Fix the method to make it safer in terms of errors
        tiledMap = new TmxMapLoader().load(mapName);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        getCollisionObjects();
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

    private void getCollisionObjects() {
        for (MapObject e : tiledMap.getLayers().get("COLLISION").getObjects()) {
            if (e.getName().equalsIgnoreCase("Leftcollision")) {
                leftBounds = new Rectangle(((RectangleMapObject)e).getRectangle().x , ((RectangleMapObject)e).getRectangle().y, ((RectangleMapObject)e).getRectangle().width, ((RectangleMapObject)e).getRectangle().height);
            }
            if (e.getName().equalsIgnoreCase("rightcollision")) {
                rightBounds = new Rectangle(((RectangleMapObject)e).getRectangle().x , ((RectangleMapObject)e).getRectangle().y, ((RectangleMapObject)e).getRectangle().width, ((RectangleMapObject)e).getRectangle().height);
            }
            if (e.getName().equalsIgnoreCase("floorcollision")) {
                floorBounds = new Rectangle(((RectangleMapObject)e).getRectangle().x , ((RectangleMapObject)e).getRectangle().y, ((RectangleMapObject)e).getRectangle().width, ((RectangleMapObject)e).getRectangle().height);
            }
        }
    }

    public static Array<Rectangle> getCollisionArray() {
        return collisionArray;
    }

    public static Vector2 getMapDimensions() {
        return mapDimensions;
    }

    public static Rectangle getLeftBounds() {
        return leftBounds;
    }

    public static Rectangle getRightBounds() {
        return rightBounds;
    }

    public static Rectangle getFloorBounds() {
        return floorBounds;
    }

}
