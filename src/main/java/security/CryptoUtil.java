package security;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import java.security.MessageDigest;
import java.security.SecureRandom;


public class CryptoUtil { // classe utilitaire permettant de chiffrer et déchiffrer une image
    public static Image encrypt(Image image, String password) { // methode de chiffrement
        int width = (int) image.getWidth(); // recuperation des dim de limage
        int height = (int) image.getHeight();
        WritableImage newImage = new WritableImage(width, height); // creation dune nvlle image pour stocker le res
        PixelReader reader = image.getPixelReader(); // permet de lire les px de limage originale
        PixelWriter writer = newImage.getPixelWriter(); // permet decrire dans la nvlle image
        try {
            // transformer password en hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            // generateur pseudo-aleatoire base sur le hash
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(hash);
            // melanger les pixels
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // generation des cordonnes aleatoires
                    int rx = random.nextInt(width);
                    int ry = random.nextInt(height);
                    // recuperation dun px aleatoire
                    Color color = reader.getColor(rx, ry);
                    // ecriture dans la nvlle pos
                    writer.setColor(x, y, color);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newImage;
    }
// methode de dechiffrement
    public static Image decrypt(Image image, String password) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage newImage = new WritableImage(width, height);
        PixelWriter writer = newImage.getPixelWriter();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(hash);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    int rx = random.nextInt(width);
                    int ry = random.nextInt(height);

                    Color color = image.getPixelReader().getColor(x, y);

                    writer.setColor(rx, ry, color);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newImage;
    }
}




