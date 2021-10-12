package io.github.imperialpinetrees.heaventimemassacre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.imperialpinetrees.heaventimemassacre.util.MapManager;

public class Player {

    private Rectangle playerRectangle;

    private float x = 0;
    private float y = 0;

    private Vector2 velocity;

    private static Vector2 playerCoords;

    private int WIDTH = 16, HEIGHT = 32;

    private final Rectangle collisionRectangle = new Rectangle(0, 0,
            WIDTH, HEIGHT);

    public enum MovementDirection {
        LEFT, RIGHT, DOWN, UP
    }

    MovementDirection movementDirection;

    public Player() {
        playerCoords = new Vector2(0, 0);
        MapManager.setPlayerSpawnLocation();
        playerCoords.set(MapManager.playerStartPos.x, MapManager.playerStartPos.y);
        playerRectangle = new Rectangle(playerCoords.x, playerCoords.y, WIDTH, HEIGHT);
        velocity = new Vector2(2, 2);

    }

    public void playerUpdate(){
        getPlayerMovement();
        updateCollisionRectangle();
    }

    public void renderPlayer(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(playerRectangle.x, playerRectangle.y, playerRectangle.width, playerRectangle.height);
        getPlayerMovement();
    }

    public void getPlayerMovement() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D)) {
             playerCoords.x += velocity.x;
             playerRectangle.x = playerCoords.x;
             movementDirection = MovementDirection.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerCoords.x -= velocity.x;
            playerRectangle.x = playerCoords.x;
            movementDirection = MovementDirection.LEFT;
        }
    }

    //collision
    private void updateCollisionRectangle(){
        collisionRectangle.setPosition(playerCoords.x, playerCoords.y);
    }

    public void setPosition(float x, float y){
        this.playerCoords.x = x;
        this.playerCoords.y = y;

        updateCollisionRectangle();
    }

    //getters and setters
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

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    //dispose
    public void dispose() {

    }
}
