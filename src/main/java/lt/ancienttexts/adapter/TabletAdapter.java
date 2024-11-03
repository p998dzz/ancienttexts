package lt.ancienttexts.adapter;

import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TextItemEntity;

import java.util.List;

public interface TabletAdapter {
    List<ListItemEntity> fetchAllEntries();
    TextItemEntity fetchTabletDetails(Long id) throws NoSuchFieldException;
}
