package model ;
import javafx.scene.image.Image ;
import model.filter.Filter;

// cette classe rep le modéle principal pour la gestion des images
//elle stocke limage actuelle et une copie de limage origianle


public  class ImageModel {
  private Image image ; // image affichée actuellement(aprés filtres)
  private Image originalImage; // image originale chargée (non modifiée)
  public ImageModel() {} // constructeur vide
  public ImageModel(Image image){
    this.image=image;
  } // constructur avec image initiale
  public Image getImage(){ // getter
    return this.image;
  } // getter : retourne limage actuelle
  public void setImage(Image image){ // setter : maj l'imae et l'image originale
    this.image=image;//setter
    this.originalImage=image;
  }
  // methode pour charger une image depuis un chemin fichier
  public void load(String path){
      this.image = new Image("file:" + path);
      this.originalImage=this.image;
  }
  // applique un filtre sur limage
  public void applyFilter(Filter filter){
    if(originalImage!= null){ // verifie que limage original existe
      image=filter.apply(originalImage); // le filtre esr appliqué sur limage originale
    } // cela eite l'accumulation des filtres
  }
 }
