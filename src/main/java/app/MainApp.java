//MainApp sert à lancer l'application.
//- il charge l’interface (FXML)
//- il applique le style (CSS)
//- il affiche la fenêtre

package app;
import javafx.application.Application; // pour creer une app graphic (classe de base de javaFX)
import javafx.fxml.FXMLLoader; // charger une fichier FXML
import javafx.scene.Scene; // contenu de la fenetre
import javafx.stage.Stage; // reprenste la fenetre
public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception { // methode appelle auto au demarrage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
      Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(
                getClass().getResource("/styles/style.css").toExternalForm()
        );
      stage.setTitle("Image Manager"); // le titre de la fenetre
      stage.setScene(scene); // on associe le contenu a la fenetre
      stage.show(); // on affcihe la fenetre
    }

  public static void main(String[] args){
    launch(); // on lance l'app javaFX
  }
  }
