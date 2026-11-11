package controller;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import model.filter.*;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import util.JsonUtil;
import model.transform.ImageTransform;
import security.CryptoUtil;
import javafx.scene.control.TextInputDialog;




public class MainController {
    @FXML
    //affichage de l'interface
    private ImageView imageView;
    private ImageModel model = new ImageModel();
    private TaggableImage taggableImage;
    @FXML
    private TextField tagField;

    @FXML
    public void openImage() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(imageView.getScene().getWindow());
        if (file != null) {
//chargement de limage
            Image image = new Image(file.toURI().toString());
            model.setImage(image);

            imageView.setImage(model.getImage());
            if (taggableImage == null) {
                taggableImage = new TaggableImage(file.getAbsolutePath());
            }
        }
    }

    @FXML
    public void applyGray() {
        model.applyFilter(new GrayFilter());
        imageView.setImage(model.getImage());

    }

    @FXML
    public void applySepia() {
        model.applyFilter(new SepiaFilter());
        imageView.setImage(model.getImage());
    }

    @FXML
    public void applyPrewitt() {
        model.applyFilter(new PrewittFilter());
        imageView.setImage(model.getImage());
    }

    @FXML
    public void applySwap() {
        model.applyFilter(new SwapFilter());
        imageView.setImage(model.getImage());
    }

    @FXML
    public void addTag() {//ajout de tag

        if (taggableImage != null) {
            String tag = tagField.getText();
            if (tag != null && !tag.isEmpty()) {
                taggableImage.addTag(tag);
                System.out.println(taggableImage.getTags());
                tagField.clear();
            }


        }
    }

    @FXML//sauvegarde en json
    public void saveData() {
        JsonUtil.save(taggableImage, "src/main/resources/data/metadata.json");
    }

    @FXML
    public void rotateLeft() {
        if (model.getImage() != null) {
            model.setImage(ImageTransform.apply(model.getImage(), ImageTransform.Direction.LEFT));
            imageView.setImage(model.getImage());
        }

    }

    @FXML
    public void rotateRight() {
        if (model.getImage() != null) {
            model.setImage(ImageTransform.apply(model.getImage(), ImageTransform.Direction.RIGHT));
            imageView.setImage(model.getImage());
        }

    }

    @FXML
    public void flipHorizontal() {
        if (model.getImage() != null) {
            model.setImage(ImageTransform.apply(model.getImage(), ImageTransform.Direction.HORIZONTAL));
            imageView.setImage(model.getImage());
        }

    }

    @FXML
    public void flipVertical() {
        if (model.getImage() != null) {
            model.setImage(ImageTransform.apply(model.getImage(), ImageTransform.Direction.VERTICAL));
            imageView.setImage(model.getImage());
        }

    }
@FXML
//chiffre limage avec mdp
    public void encryptImage(){
        if (model.getImage() != null){
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Mot de Pass");
            dialog.setHeaderText("Entrer un mot de pass");
            dialog.showAndWait().ifPresent(password-> {
                Image encrypted = CryptoUtil.encrypt(model.getImage(), password );
                model.setImage(encrypted);
                imageView.setImage(encrypted);
                if(taggableImage != null){
                    taggableImage.addAppliedFilter("Encrypted");
                }
            });
        }
}
    //dechiffre limage
    @FXML
    public void decryptImage(){

        if (model.getImage() != null){

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Mot de passe");
            dialog.setHeaderText("Entrer le mot de passe");

            dialog.showAndWait().ifPresent(password -> {

                Image decrypted = CryptoUtil.decrypt(model.getImage(), password);
                model.setImage(decrypted);
                imageView.setImage(decrypted);
//ajout dune trace
                if(taggableImage != null){
                    taggableImage.addAppliedFilter("Decrypted");
                }
            });
        }
    }
}
