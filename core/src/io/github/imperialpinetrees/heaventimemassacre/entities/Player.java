package io.github.imperialpinetrees.heaventimemassacre.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.imperialpinetrees.heaventimemassacre.util.MapManager;

public class Player {

    private Rectangle playerRectangle;


    private final MapManager map = new MapManager();
    private static final TiledMapTileLayer layer = new TiledMapTileLayer(16,16, 16, 16);


    private Vector2 speed;
    private Vector2 velocity;
    private static Vector2 playerCoords;
    private double airTime = 0.00;// i need this to change as time passes while player is in the air
    public static double gravity =.2;
    public double yMotion;
    private static int WIDTH = 16, HEIGHT = 16, tileWidth , tileHieght;
    private static boolean jumpBlock = false;
    private static float jumpDistance = 0;
    private final float MAX_JUMP = 10*HEIGHT;

    private static final Rectangle collisionRectangle = new Rectangle(MapManager.playerStartPos.x, MapManager.playerStartPos.y,
            WIDTH, HEIGHT);

    public enum MovementDirection {
        LEFT, RIGHT, DOWN, UP
    }
    public enum AimDirection {
        AIM_LEFT, AIM_RIGHT, AIM_DOWN, AIM_UP
    }

    MovementDirection movementDirection;
    AimDirection aimDirection;


    public Player() {
        playerCoords = new Vector2(0, 0);
        MapManager.setPlayerSpawnLocation();
        playerCoords.set(MapManager.playerStartPos.x, MapManager.playerStartPos.y);
        playerRectangle = new Rectangle(playerCoords.x, playerCoords.y, WIDTH, HEIGHT);
        velocity = new Vector2(50, 100);
    }

    public void playerUpdate(float deltatime){
        getPlayerMovement(deltatime);
        updateCollisionRectangle();
    }

    public void renderPlayer(ShapeRenderer shapeRenderer, float deltatime) {
        shapeRenderer.rect(playerRectangle.x, playerRectangle.y, playerRectangle.width, playerRectangle.height);
        playerUpdate(deltatime);
    }

    public static void landed(){
        jumpBlock = false;
        jumpDistance = 16;

    }

    public void getPlayerMovement(float deltaTime) {
//------------------------------------------------------------------------------------------------------book mark
        double yMotion=  (velocity.y * deltaTime)-(gravity*airTime);
        applyGravity(deltaTime);

        if (map.getLeftBounds().x + map.getLeftBounds().width >= playerCoords.x){
            setPosition(MapManager.getLeftBounds().x + MapManager.getLeftBounds().width, playerCoords.y);
            collisionRectangle.x = playerCoords.x;
            playerRectangle.x = playerCoords.x;
        }

        if (map.getRightBounds().x  <= (playerRectangle.x + playerRectangle.width)){
            setPosition(MapManager.getRightBounds().x- playerRectangle.width, playerCoords.y);
            collisionRectangle.x = playerCoords.x;
            playerRectangle.x = playerCoords.x;
        }

        if (map.getFloorBounds().overlaps(collisionRectangle)) {
            setPosition(playerCoords.x, MapManager.getFloorBounds().y + playerRectangle.height);
            collisionRectangle.y = playerCoords.y;
            playerRectangle.y = playerCoords.y;
            airTime = 0;
            jumpBlock=false;
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D))) {
            playerCoords.x += velocity.x * deltaTime;
            playerRectangle.x = playerCoords.x;
            movementDirection = MovementDirection.RIGHT;
            aimDirection = AimDirection.AIM_RIGHT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerCoords.x -= velocity.x * deltaTime;
            playerRectangle.x = playerCoords.x;
            movementDirection = MovementDirection.LEFT;
            aimDirection = AimDirection.AIM_LEFT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            //while (jumpBlock) [prob not]
                if (!jumpBlock)
                    velocity.y=500;
                jumpBlock = true;
                playerCoords.y += yMotion;
                airTime+=1;
                playerRectangle.y = playerCoords.y;
                movementDirection = MovementDirection.UP;
                jumpDistance += playerCoords.y;

                aimDirection = AimDirection.AIM_UP;

        }else {
            airTime+=1;
            playerRectangle.y = playerCoords.y;
            movementDirection = MovementDirection.DOWN;
        }

    }
    // gravity but its called all the time, so it stops acting weird
    // goal: For the movement from slowing upward motion to falling to be smooth as possible, rn releasing the jump causes instant falling
    // i would ike to apply the remnants of the velocity to still exist, so it eases into a fall.
    public void applyGravity(float deltaTime) {
        if (playerCoords.x >= MapManager.getFloorBounds().x) {
            playerCoords.y += (velocity.y* deltaTime/2)-(gravity*airTime);
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
// might be unneeded
    //public static float getGravity() {
   //     return gravity;
    //}

    //dispose
    public void dispose() {

    }

}
// old stuff---
//so we need the code to check if the character is in the air before it applies gravity. - i dont like how gravity changes when jump is released
//once we have a variable for that we can incorperate
// the gravity increasing based on how long the character has been in the air
// but also making sure it doesn't increase indefinately.
//this will stop it from pulling th character down even while its on the ground.
//-------also is delta time just the time since last render?--------
// 0_0 It's the time since last frame, so basically :)
// okay so what i want to make the jump more fluid and satifying is we need the jump to set the velocity
// positive and have the gravity aceelerate down making the velocity progressivly negative.
// we really need the air time variable in order to do that.