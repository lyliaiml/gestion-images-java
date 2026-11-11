package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.TaggableImage;
import java.io.File;
import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void save(TaggableImage image, String path) {
        try {
            mapper.writeValue(new File(path), image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
