package lt.ancienttexts.adapter.harddrive;

import lt.ancienttexts.adapter.ImageAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

/**
 * Was used 4 years ago for testing purposes
 */
@Deprecated
public class ImageFromHardDriveAdapter implements ImageAdapter {

    private final String path;

    public ImageFromHardDriveAdapter() {
        path = System.getProperty("user.dir") + "/src/main/resources/static/images/";
    }

    @Override
    public byte[] fetchTabletImage(Long id){
        var fullPath = path.concat(String.valueOf(id)).concat(".jpg");
        try {
            return Files.readAllBytes(Paths.get(fullPath));
        } catch (IOException e) {
            throw new NoSuchElementException(String.format("Image could not be read at path: %s", fullPath));
        }
    }

    @Override
    public long createTabletImage(byte[] blob) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

    @Override
    public void updateTabletImage(long id, byte[] newBlob) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

    @Override
    public void deleteTabletImage(long id) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }
}
