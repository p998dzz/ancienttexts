package lt.ancienttexts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.ancienttexts.adapter.TabletAdapter;
import lt.ancienttexts.controller.exceptions.InternalServerErrorException;
import lt.ancienttexts.controller.exceptions.ResourceNotFoundException;
import lt.ancienttexts.domain.TabletDetailsResponse;
import lt.ancienttexts.domain.TabletListResponse;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TextItemEntity;
import lt.ancienttexts.service.util.EntityConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Component("TabletService")
@RequiredArgsConstructor
public class TabletService {

    TabletAdapter tabletAdapter;
    EntityConverter entityConverter;

    public TabletListResponse fetchAllEntries(){
        try {
            List<ListItemEntity> entriesEntities = tabletAdapter.fetchAll();
            return new TabletListResponse(entityConverter.listItemEntityToTransfer(entriesEntities));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new InternalServerErrorException("There was an internal error");
        }
    }

    public TabletListResponse searchEntries(String searchPhrase){
        try {
            List<ListItemEntity> entriesEntities = tabletAdapter.fetchEntries(searchPhrase);
            return new TabletListResponse(entityConverter.listItemEntityToTransfer(entriesEntities));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new InternalServerErrorException("There was an internal error");
        }
    }

    public TabletDetailsResponse fetchTextDetails(Long id) {
        try {
            TextItemEntity entry = tabletAdapter.fetchDetails(id);
            return entityConverter.listItemEntityToTransfer(entry);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
            throw new ResourceNotFoundException(String.format("Tablet not available for id=%d", id));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new InternalServerErrorException("There was an internal error");
        }
    }
}
