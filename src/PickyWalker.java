import java.util.Random;

public class PickyWalker extends Walker {
    private int stepsRemaining;
    private int direction;

    public PickyWalker() {
        super(0,0);
        stepsRemaining = 0;
        direction = 0;
    }

    @Override
    public void step() {
        if (stepsRemaining <= 0) {
            // Pick a random direction and a random number of steps to take
            Random rand = new Random();
            direction = rand.nextInt(8); // Random direction (0-7)
            stepsRemaining = rand.nextInt(10) + 1; // Random steps (1-10)
        }

        int deltaX, deltaY;

        // Convert direction to delta coordinates
        switch (direction) {
            case 0:
                deltaX = 0;
                deltaY = -1;
                break;
            case 1:
                deltaX = 1;
                deltaY = -1;
                break;
            case 2:
                deltaX = 1;
                deltaY = 0;
                break;
            case 3:
                deltaX = 1;
                deltaY = 1;
                break;
            case 4:
                deltaX = 0;
                deltaY = 1;
                break;
            case 5:
                deltaX = -1;
                deltaY = 1;
                break;
            case 6:
                deltaX = -1;
                deltaY = 0;
                break;
            case 7:
                deltaX = -1;
                deltaY = -1;
                break;
            default:
                deltaX = 0;
                deltaY = 0;
                break;
        }

        // Update the walker's position
        x += deltaX;
        y += deltaY;
        stepsRemaining--;
    }
}
