package io.github.imperialpinetrees.heaventimemassacre.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Gun { // POSSIBLE IDEA: Make this an abstract class and extend it in multiple gun classes, just an idea, may not work in the future though

    private Rectangle bullet; // The bullet object is just a rectangle (possibly circle in the future) that has logic for a bullet (possibly)
    private Rectangle[] bullets; // Array that stores all the bullets

    private Rectangle actualGun; // Couldn't think of an actual name for it but it's the square that is next to the player

    private float damage;
    private float weaponCooldown;
    private float speedOfBullet;

    private Vector2 posOfGun;

    private Texture gunTexture;

    public Gun(Texture gunTexture) {
        this.gunTexture = gunTexture;
    }

    public Gun() {
        posOfGun = new Vector2(Player.getXPos() + 15, Player.getYPos() + 10); // Sets the position of the gun close to the player so it looks like the player is holding the gun
        actualGun = new Rectangle(posOfGun.x, posOfGun.y, 16, 16);

    }

    public void fireGun() {

    }

    public void renderGun(ShapeRenderer shapeRenderer) {
         shapeRenderer.rect(actualGun.x, actualGun.y, actualGun.width, actualGun.height);
    }
}
