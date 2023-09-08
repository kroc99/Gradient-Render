import java.util.Random;

public abstract class Walker {
    protected int x; // Current x-coordinate
    protected int y; // Current y-coordinate

    public Walker(int startX, int startY) {
        x = startX;
        y = startY;
    }

    public abstract void step();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}