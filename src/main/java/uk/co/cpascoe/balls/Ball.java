package uk.co.cpascoe.balls;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Ball {
    private float timeToLive;
    private Color color;
    private Vector size;
    private Vector position;
    private Vector velocity;
    private World world;
    private float bounce;

    public Ball(World world, Color color, float bounce, Vector size, Vector centerPosition, Vector velocity, float timeToLive) {
        this.world = world;
        this.color = color;
        this.bounce = bounce;
        this.size = size;
        this.position = new Vector(centerPosition.getX() - size.getX() * 0.5f, centerPosition.getY() - size.getY() * 0.5f);
        this.velocity = velocity;
        this.timeToLive = timeToLive;
    }

    public float getTimeToLive() { return this.timeToLive; }

    public void update(float timePassed, Vector gravity) {
        this.timeToLive -= timePassed;

        if (this.timeToLive < 1 && this.timeToLive > 0) {
            this.color = new Color(
                this.color.getRed(),
                this.color.getGreen(),
                this.color.getBlue(),
                (int)(255 * this.timeToLive)
            );
        }

        this.velocity.mutateAdd(gravity);
        this.position.mutateAdd(this.velocity.multiply(timePassed));

        if (this.position.getX() < 0) {
            this.position.setX(0);
            this.velocity.setX(Math.abs(this.velocity.getX()) * this.bounce);
        } else if (this.position.getX() + this.size.getX() > this.world.getSize().getX()) {
            this.position.setX(this.world.getSize().getX() - this.size.getX());
            this.velocity.setX(-Math.abs(this.velocity.getX()) * this.bounce);
        }

        if (this.position.getY() < 0) {
            this.position.setY(0);
            this.velocity.setY(Math.abs(this.velocity.getY()) * this.bounce);
        } else if (this.position.getY() + this.size.getY() > this.world.getSize().getY()) {
            this.position.setY(this.world.getSize().getY() - this.size.getY());
            this.velocity.setY(-Math.abs(this.velocity.getY()) * this.bounce);
        }
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(this.color);
        graphics.fill(new Ellipse2D.Float(this.position.getX(), this.position.getY(), this.size.getX(), this.size.getY()));
    }
}
