// cest un filtre qui modifie les couleurs :  R-B / G->R / B->G


package model.filter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
public class SwapFilter implements Filter {
    @Override
    public Image apply(Image image) {
        // recuperation des dimensions de limage
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        // creation dune nvlle image pour stocker le res
        WritableImage newImage = new WritableImage(width, height);
         // permet de lire les px de limage originale
        PixelReader reader = image.getPixelReader();
        // permet decrire les px dans la nvlle image
        PixelWriter writer = newImage.getPixelWriter();
        // parcours de tous les px de limage
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // recuperation de la couleur du px courant
                Color color = reader.getColor(x, y);
                Color newColor = new Color(
                        color.getGreen(), // composante rouge
                        color.getBlue(),// composante verte
                        color.getRed(),// composante bleue
                        color.getOpacity() // on conserve la transparence

                );
                writer.setColor(x, y, newColor); // ecriture du nv px dans limage resultat
            }
        }
        return newImage; // retourne l'image modifie
    }

}
