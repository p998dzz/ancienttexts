package lt.ancienttexts.service;

import lombok.extern.slf4j.Slf4j;
import lt.ancienttexts.controller.exceptions.InternalServerErrorException;
import lt.ancienttexts.controller.exceptions.ResourceNotFoundException;
import lt.ancienttexts.domain.TextDetailsResponse;
import lt.ancienttexts.domain.TextListResponse;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TextItemEntity;
import lt.ancienttexts.service.util.TypeConvertors;
import lt.ancienttexts.adapter.TabletAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Component("TabletService")
public class TabletService {

    TabletAdapter tabletAdapter;

    public TabletService(TabletAdapter tabletAdapter) {
        this.tabletAdapter = tabletAdapter;
    }

    public TextListResponse fetchAllEntries() throws Exception {
        try {
            List<ListItemEntity> entriesEntities = tabletAdapter.fetchAllEntries();
            return new TextListResponse(TypeConvertors.listItemEntityToTransfer(entriesEntities));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new InternalServerErrorException("There was an internal error");
        }
    }

    public TextDetailsResponse fetchTextDetails(Long id) throws Exception {
        try {
            TextItemEntity entry = tabletAdapter.fetchTabletDetails(id);
            return TypeConvertors.listItemEntityToTransfer(entry);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
            throw new ResourceNotFoundException(String.format("Tablet not available for id=%d", id));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new InternalServerErrorException("There was an internal error");
        }
    }
}
