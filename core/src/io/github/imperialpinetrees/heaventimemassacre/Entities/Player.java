package io.github.imperialpinetrees.heaventimemassacre.Entities;
//                                                     ^typo?
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
    public static float gravity = 50;

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
        velocity = new Vector2(50, 50);
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
        //good elevator mechanics
        /*for (Rectangle e : MapManager.getCollisionArray()) {
            if (collisionRectangle.overlaps(e)) {
                if (collisionRectangle.x >= e.x) {
                    playerCoords.y += 1;
                } else if (collisionRectangle.y >= e.y) {
                    playerCoords.x += 1;
                }
            }
        }

        for (Rectangle e : MapManager.getCollisionArray()) {
            oldPlayerCoords = playerCoords;

            if (collisionRectangle.overlaps(e)) {
                if (collisionRectangle.x >= e.x) {
                    playerCoords.x += 1;
                }
        else if (collisionRectangle.x >= e.x+e.width){
                    playerCoords.x += 1;
                }

                if (collisionRectangle.y >= e.y) {
                    playerCoords.y += 1;
                }
            }
        }*/

        if (map.getLeftBounds().x + map.getLeftBounds().width >= playerCoords.x){
            setPosition(MapManager.getLeftBounds().x + MapManager.getLeftBounds().width, playerCoords.y);
            /*playerCoords.x += 1;*/
            collisionRectangle.x = playerCoords.x;
            playerRectangle.x = playerCoords.x;
        }

        if (map.getRightBounds().x  <= (playerRectangle.x + playerRectangle.width)){
            setPosition(MapManager.getRightBounds().x- playerRectangle.width, playerCoords.y);
            /*playerCoords.x -= 1;*/
            collisionRectangle.x = playerCoords.x;
            playerRectangle.x = playerCoords.x;
        }

        if (map.getFloorBounds().y  >= playerRectangle.y - playerRectangle.height ){
            setPosition(playerCoords.x, MapManager.getFloorBounds().y + playerRectangle.height);
            /*playerCoords.y += 1;*/
            collisionRectangle.y = playerCoords.y;
            playerRectangle.y = playerCoords.y;
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
                playerCoords.y += velocity.y * deltaTime;
                playerRectangle.y = playerCoords.y;
                movementDirection = MovementDirection.UP;
                jumpDistance += playerCoords.y;
                jumpBlock = jumpDistance >= MAX_JUMP;
                aimDirection = AimDirection.AIM_UP;


        }else {
            playerCoords.y -= getGravity() * deltaTime;
            //so we need the code to check if the character is in the air before it applies gravity.
            //once we have a variable for that we can incorperate
            // the gravity increasing based on how long the character has been in the air
            // but also making sure it doesn't increase indefinately.
            //this will stop it from pulling th character down even while its on the ground.
            //-------also is delta time just the time since last render?--------
            playerRectangle.y = playerCoords.y;
            movementDirection = MovementDirection.DOWN;
            jumpBlock = jumpDistance >= 0;
        }

    }


    //collision
    private static void updateCollisionRectangle(){
        collisionRectangle.setPosition(playerCoords.x, playerCoords.y);
    }

    /*
    private static boolean isPlayerOverlappingCollisionY() {
        for (Rectangle collision : MapManager.getCollisionArray()) {
            if (collisionRectangle.overlaps(collision)) {
                if (collisionRectangle.y >= collision.y || collisionRectangle.y <= collision.y) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isPlayerOverlappingCollisionX() {
        for (Rectangle collision : MapManager.getCollisionArray()) {
            if (collisionRectangle.overlaps(collision)) {
                if (collisionRectangle.x >= collision.x || collisionRectangle.x <= collision.x) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkForCollision() {
        for (Rectangle collison : MapManager.getCollisionArray()) {
            // if (collisionRectangle.x <= collison.x ||)
        }
    }
    */

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
