package model.filter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
/**
 * filtre qui convertit une image en niveaux de gris
 * pour chaque pixel, on calcule la moy des 3 composantes
 * Rouge, vert et bleu et on affecte cette valeur aux 3 composantes
 * ca donne un pixel gris
 */

/**
 * * Applique le filtre niveaux de gris sur un pixel
 * * Exp un pixel rouge vif (1.0, 0.0, 0.0)
 * * donnera (0.33, 0.33, 0.33) soit un gris foncé
 * * c la couleur originale du pixel
 * * return  la couleur transformée en niveau de gris
 *
 */
// on fait la moy des 3 composantes
// dans javaFX les valeurs sont entre 0.0 et 1.0 pas 0 -255
// on retourne un nv pixel où r=g=b=moy
// on cinserve la transparence du pixel original
public class GrayFilter implements Filter {

    @Override
    public Image apply (Image image){
        int width= (int) image.getWidth();
        int height= (int) image.getHeight();
        WritableImage newImage= new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = newImage.getPixelWriter();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Color c = reader.getColor(x,y);
                double moyenne= (c.getRed()+c.getGreen()+c.getBlue())/3;
                Color gray= new Color(moyenne, moyenne, moyenne, c.getOpacity());
                writer.setColor(x,y,gray);
            }
        }
        return newImage;
    }
}
/**
 *  elle reçoit un pixel coloré et elle le ressort en gris.
 *  quand les trois composantes R, G et B ont la même valeur, l'œil perçoit du gris.
 *   Plus la valeur est proche de 0 plus le gris est foncé

 */
