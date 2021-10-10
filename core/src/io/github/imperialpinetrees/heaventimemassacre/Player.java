package io.github.imperialpinetrees.heaventimemassacre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.imperialpinetrees.heaventimemassacre.util.MapManager;

public class Player {

    private Rectangle playerRectangle;

    private Vector2 velocity;

    private static Vector2 playerCoords;

    public Player(int x, int y) {
        playerCoords = new Vector2(x, y);
        playerRectangle = new Rectangle(playerCoords.x, playerCoords.y, 16, 32);
        velocity = new Vector2(2, 2);
        MapManager.setPlayerSpawnLocation();
        playerCoords.set(MapManager.playerStartPos.x, MapManager.playerStartPos.y);
    }

    public void renderPlayer(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(playerRectangle.x, playerRectangle.y, playerRectangle.width, playerRectangle.height);
        getPlayerMovement();
    }

    public void getPlayerMovement() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
             playerCoords.x += velocity.x;
             playerRectangle.x = playerCoords.x;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerCoords.x -= velocity.x;
            playerRectangle.x = playerCoords.x;
        }
    }

    public static float getXPos() {
        return playerCoords.x;
    }

    public static void setXPos(float x) {
        playerCoords.x = x;
    }

    public static float getYPos() {
        return playerCoords.y;
    }

    public static void setYPos(float y) {
        playerCoords.y = y;
    }

    public void dispose() {

    }
}
