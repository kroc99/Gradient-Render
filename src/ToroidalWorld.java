public class ToroidalWorld extends WorldGeometry {
    @Override
    public void handleBoundary(int[] position, int width, int height) {
        int x = position[0];
        int y = position[1];

        // Handle boundary conditions for Toroidal World (teleportation)
        if (x < 0) {
            x = width - 1; // Teleport to the opposite edge
        } else if (x >= width) {
            x = 0; // Teleport to the opposite edge
        }

        if (y < 0) {
            y = height - 1; // Teleport to the opposite edge
        } else if (y >= height) {
            y = 0; // Teleport to the opposite edge
        }

        // Update the position array with the adjusted coordinates
        position[0] = x;
        position[1] = y;
    }
}