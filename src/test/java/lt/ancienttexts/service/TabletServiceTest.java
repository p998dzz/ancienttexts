package lt.ancienttexts.service;

import lt.ancienttexts.adapter.ImageAdapter;
import lt.ancienttexts.adapter.TabletAdapter;
import lt.ancienttexts.domain.ListItem;
import lt.ancienttexts.domain.TabletDetails;
import lt.ancienttexts.domain.TabletListResponse;
import lt.ancienttexts.domain.TabletRequest;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TabletEntity;
import lt.ancienttexts.service.util.EntityConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TabletServiceTest {

    @Mock
    private ImageAdapter imageAdapter;

    @Mock
    private TabletAdapter tabletAdapter;

    @Mock
    private EntityConverter entityConverter;

    @InjectMocks
    private TabletService tabletService;

    @Test
    void fetchAllEntries_shouldReturnTabletListResponse() {
        var mockEntityList = List.of(new ListItemEntity());
        var resultingEntryList = List.of(new ListItem());
        when(tabletAdapter.fetchAll()).thenReturn(mockEntityList);
        when(entityConverter.listItemEntityToTransfer(mockEntityList)).thenReturn(resultingEntryList);

        TabletListResponse response = tabletService.fetchAllEntries();

        assertThat(response.getEntries()).isEqualTo(resultingEntryList);
        assertThat(response.getNumberOfEntries()).isEqualTo(1);
        verify(tabletAdapter).fetchAll();
    }

    @Test
    void searchEntries_shouldReturnTabletListResponse() {
        var searchPhrase = "sample";
        var mockEntityList = List.of(new ListItemEntity());
        var resultingEntryList = List.of(new ListItem());
        when(tabletAdapter.fetchEntries(searchPhrase)).thenReturn(mockEntityList);
        when(entityConverter.listItemEntityToTransfer(mockEntityList)).thenReturn(resultingEntryList);

        TabletListResponse response = tabletService.searchEntries(searchPhrase);

        assertThat(response.getEntries()).isEqualTo(resultingEntryList);
        assertThat(response.getNumberOfEntries()).isEqualTo(1);
        verify(tabletAdapter).fetchEntries(searchPhrase);
    }

    @Test
    void fetchTextDetails_shouldReturnTabletDetailsResponse() throws NoSuchFieldException {
        var id = 1L;
        var mockEntity = new TabletEntity();
        var expectedResult = new TabletDetails();
        when(tabletAdapter.fetchDetails(id)).thenReturn(mockEntity);
        when(entityConverter.listItemEntityToTransfer(mockEntity)).thenReturn(expectedResult);

        TabletDetails response = tabletService.fetchTabletDetails(id);

        assertThat(response).isEqualTo(expectedResult);
        verify(tabletAdapter).fetchDetails(id);
    }

    @Test
    void updateTabletDetails_ShouldUpdateTablet_WhenImageIsNotNull() {
        Long id = 1L;
        byte[] image = new byte[]{1, 2, 3};
        TabletRequest tabletRequest = new TabletRequest("Title", "Description", "Location", image, "Translation", "Source", true, "2023-01-01", "Source Link", "2023-01-01");
        TabletEntity existingTablet = new TabletEntity();
        existingTablet.setId(id);
        existingTablet.setImageId(2L); // Mock existing image ID

        when(tabletAdapter.fetchDetails(id)).thenReturn(existingTablet);
        doNothing().when(tabletAdapter).updateTablet(any(TabletDetails.class));

        tabletService.updateTabletDetails(id, tabletRequest);

        verify(tabletAdapter).fetchDetails(id);
        verify(tabletAdapter).updateTablet(any(TabletDetails.class));
    }

    @Test
    void createTabletDetails_ShouldThrowException_WhenImageIsNull() {
        Long id = 1L;
        TabletRequest tabletRequest = new TabletRequest("Title", "Description", "Location", null, "Translation", "Source", true, "2023-01-01", "Source Link", "2023-01-01");

        assertThatThrownBy(() -> tabletService.createTabletDetails(tabletRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Missing tablet image");
        verifyNoInteractions(tabletAdapter);
    }

    @Test
    void createTabletDetails_ShouldCreateTablet_WhenImageIsNotNull() {
        Long id = 1L;
        byte[] image = new byte[]{1, 2, 3};
        TabletRequest tabletRequest = new TabletRequest("Title", "Description", "Location", image, "Translation", "Source", true, "2023-01-01", "Source Link", "2023-01-01");
        Long imageId = 3L;
        TabletDetails tabletDetails = new TabletDetails();
        tabletDetails.setId(id);
        tabletDetails.setTitle(tabletRequest.title());
        tabletDetails.setDescription(tabletRequest.description());
        tabletDetails.setLocation(tabletRequest.location());
        tabletDetails.setImageId(imageId);
        tabletDetails.setTranslation(tabletRequest.translation());
        tabletDetails.setTabletSource(tabletRequest.tabletSource());
        tabletDetails.setInterpreted(tabletRequest.interpreted());
        tabletDetails.setDateAdded(tabletRequest.dateAdded());
        tabletDetails.setSourceLink(tabletRequest.sourceLink());
        tabletDetails.setDatedAt(tabletRequest.datedAt());
        when(imageAdapter.createTabletImage(image)).thenReturn(imageId);

        tabletService.createTabletDetails(tabletRequest);

        verify(imageAdapter).createTabletImage(image);
        verify(tabletAdapter, times(1)).createTablet(any(TabletDetails.class));
    }

    @Test
    void deleteTabletDetails_ShouldDeleteTabletAndImage() {
        Long id = 1L;
        Long imageId = 2L;
        TabletEntity existingTablet = new TabletEntity();
        existingTablet.setId(id);
        existingTablet.setImageId(imageId); // Mock existing image ID


        when(tabletAdapter.fetchDetails(id)).thenReturn(existingTablet);
        doNothing().when(tabletAdapter).deleteTablet(id);
        doNothing().when(imageAdapter).deleteTabletImage(imageId);

        tabletService.deleteTabletDetails(id);

        verify(tabletAdapter).fetchDetails(id);
        verify(tabletAdapter).deleteTablet(id);
        verify(imageAdapter).deleteTabletImage(imageId);
    }
}
