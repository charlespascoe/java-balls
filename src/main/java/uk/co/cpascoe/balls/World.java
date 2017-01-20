package uk.co.cpascoe.balls;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class World {
    private List<Ball> balls = new ArrayList<>();
    private BallFactory ballFactory;
    private Vector size;
    private Vector gravity;
    private float age = 0;
    private float lastBallCreated = 0;

    public World(Vector size, Vector gravity) {
        this.size = size;
        this.gravity = gravity;
        this.ballFactory = new BallFactory(this);
    }

    public Vector getSize() { return this.size; }

    public Vector getGravity() { return this.gravity; }

    public void update(float timePassed) {
        this.age += timePassed;

        this.gravity = this.gravity.rotate((timePassed * 2 * (float)Math.PI) / 10);

        Vector gravityForTick = this.gravity.multiply(timePassed);

        for (int i = this.balls.size() - 1; i >= 0; i--) {
            Ball ball = this.balls.get(i);
            ball.update(timePassed, gravityForTick);
            if (ball.getAge() > 10) this.balls.remove(i);
        }
    }

    public void createBall(Vector position) {
        if (this.age - this.lastBallCreated > 0.05) {
            this.balls.add(this.ballFactory.create(position));
            this.lastBallCreated = this.age;
        }
    }

    public void draw(Graphics2D graphics) {
        for (Ball ball : this.balls) {
            ball.draw(graphics);
        }
    }
}
