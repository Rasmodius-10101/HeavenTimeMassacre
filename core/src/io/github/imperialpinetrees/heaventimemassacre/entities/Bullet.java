package io.github.imperialpinetrees.heaventimemassacre.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bullet {

    public boolean remove = false;
    private final int speedOfBullet = 500;

    public float x, y;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float deltaTime) {
        float bulletTime = 1f;
        float timeSeconds = 0f;

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

        timeSeconds += deltaTime;

        if (bulletTime < timeSeconds) {
            remove = true;
        }

    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(x, y, 8, 8);
    }
}
