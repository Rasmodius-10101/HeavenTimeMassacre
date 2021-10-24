package io.github.imperialpinetrees.heaventimemassacre.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bullet {

    public boolean remove = false;
    private final int speedOfBullet = 200;

    public float x, y;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float deltaTime) {
        remove = false;

        float bulletTime = 1.0178f;
        float timeSeconds = 0;

        switch (Player.getAimDirection())  {
            case AIM_UP:
                y += speedOfBullet * deltaTime;
                break;
            case AIM_DOWN:
                y -= speedOfBullet * deltaTime;
                break;
            case AIM_LEFT:
                x -= speedOfBullet * deltaTime;
                break;
            case AIM_RIGHT:
                x += speedOfBullet * deltaTime;
                break;
        }

        timeSeconds += deltaTime + 1;

        Gdx.app.log("Gun", String.valueOf(timeSeconds));
        if (timeSeconds > bulletTime) {
            remove = true;
            timeSeconds = 0;
        }

    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(x, y, 8, 8);
    }
}
