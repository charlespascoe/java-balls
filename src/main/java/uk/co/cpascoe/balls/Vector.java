package uk.co.cpascoe.balls;

public class Vector {
    private float x;
    private float y;

    public Vector() { this(0, 0); }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return this.x; }

    public void setX(float x) { this.x = x; }

    public float getY() { return this.y; }

    public void setY(float y) { this.y = y; }

    public Vector add(Vector other) {
        return new Vector(other.x + this.x, other.y + this.y);
    }

    public Vector multiply(float scale) {
        return new Vector(this.x * scale, this.y * scale);
    }

    public Vector rotate(float angle) {
        float sinAng = (float)Math.sin(angle);
        float cosAng = (float)Math.cos(angle);
        return new Vector(
            this.x * cosAng - this.y * sinAng,
            this.x * sinAng + this.y * cosAng
        );
    }

    public Vector mutateAdd(Vector other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector mutateMultiply(float scale) {
        this.x *= scale;
        this.y *= scale;
        return this;
    }
}
