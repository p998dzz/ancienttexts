package lt.ancienttexts.adapter;

import lt.ancienttexts.domain.TabletDetails;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TabletEntity;

import java.util.List;

public interface TabletAdapter {
    List<ListItemEntity> fetchAll();
    TabletEntity fetchDetails(Long id);
    List<ListItemEntity> fetchEntries(String searchPhrase);
    void createTablet(TabletDetails tabletDetails);
    void deleteTablet(Long id);
    void updateTablet(TabletDetails tabletDetails);
}
