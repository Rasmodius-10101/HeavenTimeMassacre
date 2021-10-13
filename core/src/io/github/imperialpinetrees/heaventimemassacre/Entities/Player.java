package io.github.imperialpinetrees.heaventimemassacre.Entities;

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

    private MapManager map = new MapManager();

    private Vector2 velocity;
    private static Vector2 playerCoords;
    public static float gravity = 1;

    private static int WIDTH = 16, HEIGHT = 32;
    private static boolean jumpBlock = false;
    private static float jumpDistance = 0;
    private final float MAX_JUMP = 3*HEIGHT;

    private static final Rectangle collisionRectangle = new Rectangle(MapManager.playerStartPos.x, MapManager.playerStartPos.y,
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
        velocity = new Vector2(2, 20);

    }

    public void playerUpdate(){
        getPlayerMovement();
        updateCollisionRectangle();
    }

    public void renderPlayer(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(playerRectangle.x, playerRectangle.y, playerRectangle.width, playerRectangle.height);
        playerUpdate();
    }

    public static void landed(){
        jumpBlock = false;
        jumpDistance = 16;

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
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && !jumpBlock){
            playerCoords.y += velocity.y;
            playerRectangle.y = playerCoords.y;
            movementDirection = MovementDirection.UP;
            jumpDistance += playerCoords.y;
            jumpBlock = jumpDistance >= MAX_JUMP;
        }else {
            for (Rectangle e : map.getCollisionArray()){

            }
            playerCoords.y -= getGravity();
            playerRectangle.y = playerCoords.y;
            movementDirection = MovementDirection.DOWN;
            jumpBlock = jumpDistance >= 0;
        }
    }

    //collision
    private static void updateCollisionRectangle(){
        collisionRectangle.setPosition(playerCoords.x, playerCoords.y);
    }

    public static void setPosition(float x, float y){
        playerCoords.x = x;
        playerCoords.y = y;

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

    public static Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    public static float getGravity() {
        return gravity;
    }

    //dispose
    public void dispose() {

    }
}
