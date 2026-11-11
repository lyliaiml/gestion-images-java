package model.filter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

//Filtre qui donne à l'image l'aspect des vieilles photographies

public class SepiaFilter implements Filter {
    /**
     *
     * c la couleur originale du pixel
     *
     * @return la couleur avec la teinte sepia
     */
    @Override
    public Image apply(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage newImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = newImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color c = reader.getColor(x, y);
                // on recup les composantes du pixel originale
                double r = c.getRed();
                double g = c.getGreen();
                double b = c.getBlue();
// math.min(1,... ) sert a sassurer que la val ne depasse pas 1.0
                // lopacite est conservé
                double newR = Math.min(1, 0.393 * r + 0.769 * g + 0.189 * b);
                double newG = Math.min(1, 0.349 * r + 0.686 * g + 0.168 * b);
                double newB = Math.min(1, 0.272 * r + 0.534 * g + 0.131 * b);

                Color sepia = new Color(newR, newG, newB, c.getOpacity());
                writer.setColor(x, y, sepia);

            }
        }
        return newImage;
    }
}
