package model.transform;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class ImageTransform {
    public enum Direction{LEFT, RIGHT, HORIZONTAL, VERTICAL}
    public static Image apply(Image image, Direction direction){
        int w = (int) image.getWidth();
        int h = (int) image.getHeight();

        PixelReader reader = image.getPixelReader();
        WritableImage result=null;
        PixelWriter writer=null;

        switch (direction){
            case LEFT :
                result= new WritableImage(h,w);
                writer= result.getPixelWriter();
                for (int y=0;y < h;y++)
                    for (int x=0;x < w;x++)
                        writer.setColor(y, w-1-x, reader.getColor(x,y));

                break;

            case RIGHT :
                result= new WritableImage(h,w);
                writer= result.getPixelWriter();
                for (int y=0;y < h;y++)
                    for (int x=0;x<w;x++)
                        writer.setColor(h-1-y,x, reader.getColor(x,y));
                break;

            case HORIZONTAL:
                result= new WritableImage(w,h);
                writer= result.getPixelWriter();
                for (int y=0;y < h; y++)
                    for (int x=0; x < w;x++)
                        writer.setColor(w-1-x,y, reader.getColor(x,y));
                break;

            case VERTICAL:
                result= new WritableImage(w,h);
                writer= result.getPixelWriter();
                for (int y=0;y < h; y++)
                    for (int x=0; x<w;x++)
                        writer.setColor(x,h-1-y, reader.getColor(x,y));
                break;

        }
        return result;
    }
}
