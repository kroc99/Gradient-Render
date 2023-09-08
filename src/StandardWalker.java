import java.util.Random;

public class StandardWalker extends Walker {
    public StandardWalker(int startX, int startY) {
        super(startX, startY);
    }

    @Override
    public void step() {
        // Implement random movement logic for the standard walker
        Random rand = new Random();
        int deltaX, deltaY;

        do {
            deltaX = rand.nextInt(3) - 1; // Random change in x (-1, 0, 1)
            deltaY = rand.nextInt(3) - 1; // Random change in y (-1, 0, 1)
        } while (deltaX == 0 && deltaY == 0); // Ensure the walker moves

        // Update the walker's position
        x += deltaX;
        y += deltaY;
    }
}
