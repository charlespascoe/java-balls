package uk.co.cpascoe.balls;

import java.util.Random;
import java.awt.Color;

public class BallFactory {
    private World world;
    private float maxVelMag;
    private RandomRange size;
    private RandomRange bounce;
    private RandomRange maxAge;
    private Random random;

    public BallFactory(World world) {
        this(world, new RandomRange(10, 100), new RandomRange(0.6f, 1.01f), new RandomRange(10, 15), 750);
    }

    public BallFactory(World world, RandomRange size, RandomRange bounce, RandomRange maxAge, float maxVelMag) {
        this.world = world;
        this.size = size;
        this.bounce = bounce;
        this.maxAge = maxAge;
        this.maxVelMag = maxVelMag;
        this.random = new Random(System.currentTimeMillis());
    }

    private Vector randomSize() {
        float size = this.size.nextValue();
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
            this.bounce.nextValue(),
            this.randomSize(),
            position,
            this.randomVelocity(),
            this.maxAge.nextValue()
        );
    }
}
