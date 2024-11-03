package lt.ancienttexts.adapter.harddrive;

import lt.ancienttexts.adapter.ImageAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

public class ImageFromHardDriveTranslator implements ImageAdapter {

    private final String path;

    public ImageFromHardDriveTranslator(String subPath) {
        path = System.getProperty("user.dir") + "/src/main/resources/static/images/";
    }

    @Override
    public byte[] fetchTabletImage(Long id){
        String fullPath = path.concat(String.valueOf(id)).concat(".jpg");
        try {
            return Files.readAllBytes(Paths.get(fullPath));
        } catch (IOException e) {
            throw new NoSuchElementException(String.format("Image could not be read at path: %s", fullPath));
        }
    }
}
