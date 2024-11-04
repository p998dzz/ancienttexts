package lt.ancienttexts.service;

import lt.ancienttexts.adapter.ImageAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @Mock
    ImageAdapter imageAdapter;

    @InjectMocks
    ImageService imageService;

    @Test
    void should_returnImageBytes_when_fetchTabletImageReturnsBytes() {
        var expectedImage = new byte[10];
        when(imageAdapter.fetchTabletImage(any())).thenReturn(expectedImage);

        var image = imageService.fetchImage(1L);

        assertThat(image).isEqualTo(expectedImage);
    }
}