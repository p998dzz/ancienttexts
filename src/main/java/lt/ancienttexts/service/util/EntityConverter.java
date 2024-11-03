package lt.ancienttexts.service.util;

import lt.ancienttexts.domain.ListItem;
import lt.ancienttexts.domain.TabletDetailsResponse;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TextItemEntity;

import java.util.List;

public interface EntityConverter {
    List<ListItem> listItemEntityToTransfer(List<ListItemEntity> entitiesToConvert);

    TabletDetailsResponse listItemEntityToTransfer(TextItemEntity srcEntity);
}
