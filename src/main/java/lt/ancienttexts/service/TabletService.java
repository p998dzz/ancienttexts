package lt.ancienttexts.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.ancienttexts.adapter.ImageAdapter;
import lt.ancienttexts.adapter.TabletAdapter;
import lt.ancienttexts.domain.TabletDetails;
import lt.ancienttexts.domain.TabletListResponse;
import lt.ancienttexts.domain.TabletRequest;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TabletEntity;
import lt.ancienttexts.service.util.EntityConverter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("TabletService")
@RequiredArgsConstructor
public class TabletService {

    TabletAdapter tabletAdapter;
    ImageAdapter imageAdapter;
    EntityConverter entityConverter;

    public TabletListResponse fetchAllEntries(){
        List<ListItemEntity> entriesEntities = tabletAdapter.fetchAll();
        return new TabletListResponse(entityConverter.listItemEntityToTransfer(entriesEntities));
    }

    public TabletListResponse searchEntries(String searchPhrase){
        if (Strings.isBlank(searchPhrase)) {
            throw new IllegalArgumentException("search phrase not provided");
        }
        List<ListItemEntity> entriesEntities = tabletAdapter.fetchEntries(searchPhrase);
        return new TabletListResponse(entityConverter.listItemEntityToTransfer(entriesEntities));
    }

    public TabletDetails fetchTabletDetails(Long id){
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        TabletEntity entry = tabletAdapter.fetchDetails(id);
        return entityConverter.listItemEntityToTransfer(entry);

    }

    public void updateTabletDetails(Long id, TabletRequest tabletRequest) {
        if (tabletRequest.image() != null) {
            var existingTablet = tabletAdapter.fetchDetails(id);
            updateImage(existingTablet.getImageId(), tabletRequest.image());
        }
        var tabletDetails = TabletDetails.from(id, tabletRequest, null);
        tabletAdapter.updateTablet(tabletDetails);
    }

    public void createTabletDetails(TabletRequest tabletRequest) {
        if (tabletRequest.image() == null) {
            throw new IllegalArgumentException("Missing tablet image");
        }
        var imageId = createImage(tabletRequest.image());
        var tabletDetails = TabletDetails.from(null, tabletRequest, imageId);
        tabletAdapter.createTablet(tabletDetails);
    }

    public void deleteTabletDetails(Long id) {
        var imageId = tabletAdapter.fetchDetails(id).getImageId();
        tabletAdapter.deleteTablet(id);
        deleteImage(imageId);
    }

    private long createImage(byte[] blob) {
        if (blob == null || blob.length == 0) {
            throw new IllegalArgumentException("blob must be provided");
        }
        return imageAdapter.createTabletImage(blob);
    }

    private void updateImage(Long id, byte[] blob) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        imageAdapter.updateTabletImage(id, blob);
    }

    private void deleteImage(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        imageAdapter.deleteTabletImage(id);
    }
}
