package lt.ancienttexts.service.util;

import lt.ancienttexts.domain.ListItem;
import lt.ancienttexts.domain.TabletDetails;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TabletEntity;

import java.util.List;

public interface EntityConverter {
    List<ListItem> listItemEntityToTransfer(List<ListItemEntity> entitiesToConvert);

    TabletDetails listItemEntityToTransfer(TabletEntity srcEntity);
}
