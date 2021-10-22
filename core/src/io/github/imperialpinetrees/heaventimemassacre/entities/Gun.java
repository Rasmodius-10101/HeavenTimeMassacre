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
        posOfGun = new Vector2(Player.getXPos() + 5, Player.getYPos() - 5); // Sets the position of the gun close to the player so it looks like the player is holding the gun
        actualGun = new Rectangle(posOfGun.x, posOfGun.y, 11, 8);

    }

    public void fireGun() {

    }

    public void renderGun(ShapeRenderer shapeRenderer) {
        // This chunk of code may not be the best but it works
        // It checks for the x and y pos of the player and updates the actualGun object as well
        posOfGun.set(Player.getXPos() + 10, Player.getYPos() + 5);
        actualGun.x = posOfGun.x;
        actualGun.y = posOfGun.y;

        shapeRenderer.rect(actualGun.x, actualGun.y, actualGun.width, actualGun.height);
    }
}
