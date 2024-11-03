package lt.ancienttexts.adapter;

import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TextItemEntity;

import java.util.List;

public interface TabletAdapter {
    List<ListItemEntity> fetchAll();
    TextItemEntity fetchDetails(Long id) throws NoSuchFieldException;
    List<ListItemEntity> fetchEntries(String searchPhrase);
}
