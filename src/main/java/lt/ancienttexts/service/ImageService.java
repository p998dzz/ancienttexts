package lt.ancienttexts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.ancienttexts.adapter.ImageAdapter;
import org.springframework.stereotype.Component;

@Slf4j
@Component("ImageService")
@RequiredArgsConstructor
public class ImageService {

    ImageAdapter imageAdapter;

    public byte[] fetchImage(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return imageAdapter.fetchTabletImage(id);
    }
}
