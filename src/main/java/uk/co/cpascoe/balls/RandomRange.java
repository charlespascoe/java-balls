package uk.co.cpascoe.balls;

import java.util.Random;

public class RandomRange {
    private float min;
    private float max;
    private Random random;

    public RandomRange(float min, float max) {
        if (min < max) {
            this.min = min;
            this.max = max;
        } else {
            this.min = max;
            this.max = min;
        }

        this.random = new Random(System.currentTimeMillis());
    }

    public float nextValue() {
        return this.random.nextFloat() * (this.max - this.min) + this.min;
    }
}
