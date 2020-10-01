package lt.ancienttexts.ancienttexts.service.translator;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component("ImageFromHardDriveTranslator")
public class ImageFromHardDriveTranslator {

    private static final String path = System.getProperty("user.dir") + "/src/main/resources/static/images/";

    public byte[] fetchImage(Long id) throws IOException {
        //File imgPath = new File(path+id+".jpg");
        byte[] bytes = Files.readAllBytes(Paths.get(path+id+".jpg"));

        return bytes;
    }
}
