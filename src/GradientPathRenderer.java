import java.awt.Color;
import java.awt.image.BufferedImage;

public class GradientPathRenderer extends PathRenderer {
    private final int startColorRGB;
    private final int endColorRGB;

    public GradientPathRenderer(int startColorRGB, int endColorRGB) {
        this.startColorRGB = startColorRGB;
        this.endColorRGB = endColorRGB;
    }

    @Override
    public void renderPath(BufferedImage image, int x, int y, Color color) {
        // Calculate the gradient between start and end colors
        float alpha = (float) color.getAlpha() / 255f;
        float red = ((1 - alpha) * ((startColorRGB >> 16) & 0xFF) + alpha * ((endColorRGB >> 16) & 0xFF)) / 255f;
        float green = ((1 - alpha) * ((startColorRGB >> 8) & 0xFF) + alpha * ((endColorRGB >> 8) & 0xFF)) / 255f;
        float blue = ((1 - alpha) * (startColorRGB & 0xFF) + alpha * (endColorRGB & 0xFF)) / 255f;

        // Set the pixel at (x, y) to the calculated color
        int finalColorRGB = (Math.round(red * 255) << 16) | (Math.round(green * 255) << 8) | Math.round(blue * 255);
        image.setRGB(x, y, finalColorRGB);
    }
}
