import java.awt.Color;
import java.awt.image.BufferedImage;

public class BlackPathRenderer extends PathRenderer {
    @Override
    public void renderPath(BufferedImage image, int x, int y, Color color) {
        // Set the pixel at (x, y) to the specified color (black)
        image.setRGB(x, y, color.getRGB());
    }
}