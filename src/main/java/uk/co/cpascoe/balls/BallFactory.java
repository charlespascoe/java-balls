package uk.co.cpascoe.balls;

import java.util.Random;
import java.awt.Color;

public class BallFactory {
    private World world;
    private float minSize;
    private float maxSize;
    private float maxVelMag;
    private Random random;

    public BallFactory(World world) {
        this(world, 10, 100, 500);
    }

    public BallFactory(World world, float minSize, float maxSize, float maxVelMag) {
        this.world = world;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.maxVelMag = maxVelMag;
        this.random = new Random();
    }

    private Vector randomSize() {
        float size = this.random.nextFloat() * (this.maxSize - this.minSize) + this.minSize;
        return new Vector(size, size);
    }

    private Vector randomVelocity() {
        float xVel = this.random.nextFloat() * this.maxVelMag;

        if (this.random.nextBoolean()) {
            xVel = -xVel;
        }

        float yVel = this.random.nextFloat() * (float)Math.sqrt(Math.pow(this.maxVelMag, 2) - Math.pow(xVel, 2));

        if (this.random.nextBoolean()) {
            yVel = -yVel;
        }

        return new Vector(xVel, yVel);
    }

    private Color randomColour() {
        return new Color(
            this.random.nextInt(256),
            this.random.nextInt(256),
            this.random.nextInt(256)
        );
    }

    public Ball create(Vector position) {
        return new Ball(
            this.world,
            this.randomColour(),
            this.random.nextFloat() * 0.5f + 0.5001f,
            this.randomSize(),
            position,
            this.randomVelocity()
        );
    }
}
