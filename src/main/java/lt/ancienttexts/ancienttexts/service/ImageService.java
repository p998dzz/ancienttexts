package lt.ancienttexts.ancienttexts.service;

import lt.ancienttexts.ancienttexts.service.translator.ImageFromDatabaseTranslator;
import lt.ancienttexts.ancienttexts.service.translator.ImageFromHardDriveTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ImageService")
public class ImageService {

    @Autowired
    ImageFromDatabaseTranslator databaseTranslator;

    public byte[] fetchOriginalImage(Long id) {
        byte[] image = new byte[0];
        try {
            image = databaseTranslator.fetchTabletImage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
