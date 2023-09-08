public class BoundedWorld extends WorldGeometry {
    @Override
    public void handleBoundary(int[] position, int width, int height) {
        int x = position[0];
        int y = position[1];

        // Check and handle boundary conditions for Bounded World
        if (x < 0) {
            x = 0; // Adjust x-coordinate to stay within bounds
        } else if (x >= width) {
            x = width - 1; // Adjust x-coordinate to stay within bounds
        }

        if (y < 0) {
            y = 0; // Adjust y-coordinate to stay within bounds
        } else if (y >= height) {
            y = height - 1; // Adjust y-coordinate to stay within bounds
        }

        // Update the position array with the adjusted coordinates
        position[0] = x;
        position[1] = y;
    }
}
