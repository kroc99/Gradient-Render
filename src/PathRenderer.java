// PathRenderer.java
import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class PathRenderer {
    public abstract void renderPath(BufferedImage image, int x, int y, Color color);
}
