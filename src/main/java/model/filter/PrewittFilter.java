package model.filter;
//cette classe permet de detecter les bord (contours) dune image
// Le filtre Prewitt calcule les variations de luminosité entre pixels voisins
// pour détecter les contours.
import javafx.scene.image.*;
import javafx.scene.paint.Color;
public class PrewittFilter implements Filter {
    @Override
    public Image apply(Image image) {
        // recuperation des dimensions de l'i'mage
        int width=(int) image.getWidth();
        int height=(int) image.getHeight();
        WritableImage newImage=new WritableImage(width, height); // creation dune nvlle image qui contiendra le res
        PixelReader reader = image.getPixelReader(); // permet de lire les px de limage originale
        PixelWriter writer= newImage.getPixelWriter();// permet decrire les px dans la nvlle image
        //on parcourt l'image en evitant les bord car on utilise les voisins
        for(int y=1 ; y<height - 1 ; y++){
            for(int x=1 ; x<width  - 1; x++){
                double gx = // gradient horizental (var guache-droite)
                        - reader.getColor(x - 1 , y - 1 ).getBrightness() // getBrightness sert a donneer niveau de lumiere
                        + reader.getColor(x + 1 , y -1).getBrightness()
                        - reader.getColor(x - 1 , y ).getBrightness()
                        + reader.getColor(x + 1 , y).getBrightness()
                        - reader.getColor(x - 1 , y + 1 ).getBrightness()
                        + reader.getColor(x + 1 , y + 1).getBrightness();
                double gy = // gradient vertical  haut/bas
                        - reader.getColor(x - 1 , y - 1 ).getBrightness()
                        - reader.getColor(x  , y - 1 ).getBrightness()
                        - reader.getColor(x + 1 , y - 1 ).getBrightness()
                        + reader.getColor(x - 1 , y + 1 ).getBrightness()
                        + reader.getColor(x  , y + 1 ).getBrightness()
                        + reader.getColor(x + 1 , y + 1 ).getBrightness();
                double g = Math.sqrt(gx * gx + gy * gy); // intensité du contour(norme du gradient)
                g=Math.min(1.0, g); // on limite la val a &.à (valeur max en javafx)
                Color color = new Color(g,g,g,1.0); // creation dune couleur en niveau de  gris
                writer.setColor(x,y,color); // ecriture du px dans la nvlle image
            }
        }
        return newImage;
    }
}
