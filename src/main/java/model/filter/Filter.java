package model.filter;
import javafx.scene.image.Image;
public interface Filter {
    Image apply(Image image);
}
// on cree une regle commune pour filter car on a plusieurs filtres : swap sepia ..
// on a creer cette interface pour que tous les filtres utilsient ca
