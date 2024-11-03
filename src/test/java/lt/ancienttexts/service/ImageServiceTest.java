package lt.ancienttexts.service;

import lt.ancienttexts.adapter.ImageAdapter;
import lt.ancienttexts.controller.exceptions.InternalServerErrorException;
import lt.ancienttexts.controller.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.NoSuchElementException;

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

        var image = imageService.fetchOriginalImage(1L);

        assertThat(image).isEqualTo(expectedImage);
    }

    @Test
    void should_throwResourceNotFoundException_when_fetchTabletImageThrowsNoSuchElementException() {
        when(imageAdapter.fetchTabletImage(any())).thenThrow(new NoSuchElementException());

        assertThrows(ResourceNotFoundException.class, () -> imageService.fetchOriginalImage(1L));
    }

    @Test
    void should_throwInternalServerErrorException_when_fetchTabletImageThrowsAnyOtherRuntimeException() {
        when(imageAdapter.fetchTabletImage(any())).thenThrow(new RuntimeException());

        assertThrows(InternalServerErrorException.class, () -> imageService.fetchOriginalImage(1L));
    }

}