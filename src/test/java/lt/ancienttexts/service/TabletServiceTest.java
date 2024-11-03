package lt.ancienttexts.service;

import lt.ancienttexts.adapter.TabletAdapter;
import lt.ancienttexts.controller.exceptions.InternalServerErrorException;
import lt.ancienttexts.controller.exceptions.ResourceNotFoundException;
import lt.ancienttexts.domain.ListItem;
import lt.ancienttexts.domain.TabletDetailsResponse;
import lt.ancienttexts.domain.TabletListResponse;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TextItemEntity;
import lt.ancienttexts.service.util.EntityConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TabletServiceTest {

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
    void fetchAllEntries_shouldThrowInternalServerErrorException() {
        when(tabletAdapter.fetchAll()).thenThrow(new RuntimeException("Database error"));

        assertThatThrownBy(() -> tabletService.fetchAllEntries())
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("There was an internal error");
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
    void searchEntries_shouldThrowInternalServerErrorException() {
        var searchPhrase = "sample";
        when(tabletAdapter.fetchEntries(searchPhrase)).thenThrow(new RuntimeException("Database error"));

        assertThatThrownBy(() -> tabletService.searchEntries(searchPhrase))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("There was an internal error");
    }

    @Test
    void fetchTextDetails_shouldReturnTabletDetailsResponse() throws NoSuchFieldException {
        var id = 1L;
        var mockEntity = new TextItemEntity();
        var expectedResult = new TabletDetailsResponse();
        when(tabletAdapter.fetchDetails(id)).thenReturn(mockEntity);
        when(entityConverter.listItemEntityToTransfer(mockEntity)).thenReturn(expectedResult);

        TabletDetailsResponse response = tabletService.fetchTextDetails(id);

        assertThat(response).isEqualTo(expectedResult);
        verify(tabletAdapter).fetchDetails(id);
    }

    @Test
    void fetchTextDetails_shouldThrowResourceNotFoundException() throws NoSuchFieldException {
        var id = 1L;
        when(tabletAdapter.fetchDetails(id)).thenThrow(new NoSuchElementException("No such tablet"));

        assertThatThrownBy(() -> tabletService.fetchTextDetails(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Tablet not available for id=" + id);
    }

    @Test
    void fetchTextDetails_shouldThrowInternalServerErrorException() throws NoSuchFieldException {
        var id = 1L;
        when(tabletAdapter.fetchDetails(id)).thenThrow(new RuntimeException("Database error"));

        assertThatThrownBy(() -> tabletService.fetchTextDetails(id))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("There was an internal error");
    }
}
