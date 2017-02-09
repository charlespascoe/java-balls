package uk.co.cpascoe.balls;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class World {
    private ArrayList<Ball> balls = new ArrayList<>();
    private List<Ball> drawingBalls;
    private BallFactory ballFactory;
    private Vector size;
    private Vector gravity;
    private float lastBallCreated = 0;
    private float rotateGravityRate = 0;
    private int maxBalls;
    private boolean isDrawing = false;

    public World(Vector size, Vector gravity, float rotateGravityRate, int maxBalls) {
        this.size = size;
        this.gravity = gravity;
        this.rotateGravityRate = rotateGravityRate;
        this.maxBalls = maxBalls;
        this.ballFactory = new BallFactory(this);
    }

    public Vector getSize() { return this.size; }

    public Vector getGravity() { return this.gravity; }

    public void update(float timePassed) {
        this.drawingBalls = (List)this.balls.clone();

        if (this.rotateGravityRate > 0) {
            this.gravity = this.gravity.rotate((timePassed * 2 * (float)Math.PI) / this.rotateGravityRate);
        }

        Vector gravityForTick = this.gravity.multiply(timePassed);

        for (int i = this.balls.size() - 1; i >= 0; i--) {
            Ball ball = this.balls.get(i);
            ball.update(timePassed, gravityForTick);
            if (ball.getTimeToLive() <= 0) this.balls.remove(i);
        }
    }

    public void createBall(Vector position) {
        if (this.balls.size() < this.maxBalls) {
            this.balls.add(this.ballFactory.create(position));
        }
    }

    public void draw(Graphics2D graphics) {
        for (Ball ball : this.drawingBalls) {
            ball.draw(graphics);
        }
    }
}
