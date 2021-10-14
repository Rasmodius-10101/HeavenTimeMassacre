package io.github.imperialpinetrees.heaventimemassacre.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Gun extends Weapon {

    private Rectangle bullet;
    private Rectangle[] bullets;

    private float speedOfBullet;

    private Texture gunTexture;

    public Gun(Texture gunTexture) {
        this.gunTexture = gunTexture;
    }

    public Gun() {

    }

    public void fireGun() {

    }
}
