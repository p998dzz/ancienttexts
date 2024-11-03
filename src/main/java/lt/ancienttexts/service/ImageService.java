package lt.ancienttexts.service;

import lombok.extern.slf4j.Slf4j;
import lt.ancienttexts.controller.exceptions.InternalServerErrorException;
import lt.ancienttexts.controller.exceptions.ResourceNotFoundException;
import lt.ancienttexts.adapter.ImageAdapter;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Slf4j
@Component("ImageService")
public class ImageService {

    ImageAdapter imageAdapter;

    public ImageService(ImageAdapter imageAdapter) {
        this.imageAdapter = imageAdapter;
    }

    public byte[] fetchOriginalImage(Long id) {
        try {
            return imageAdapter.fetchTabletImage(id);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
            throw new ResourceNotFoundException(String.format("Image not available for id=%d", id));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new InternalServerErrorException("There was an internal error");
        }
    }
}
