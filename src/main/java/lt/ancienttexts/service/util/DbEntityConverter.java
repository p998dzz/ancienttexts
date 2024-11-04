package lt.ancienttexts.service.util;

import lt.ancienttexts.domain.ListItem;
import lt.ancienttexts.domain.TabletDetails;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TabletEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class DbEntityConverter implements EntityConverter {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<ListItem> listItemEntityToTransfer(List<ListItemEntity> entitiesToConvert){
        return entitiesToConvert.stream()
                .map(srcEntity -> {
                    ListItem item = new ListItem();
                    item.setId(srcEntity.getId());
                    item.setInterpreted(srcEntity.getInterpreted());
                    item.setLocation(srcEntity.getLocation());
                    item.setTitle(srcEntity.getTitle());
                    String strDate = DATE_FORMAT.format(srcEntity.getDateAdded());
                    item.setDateAdded(strDate);
                    return item;
                })
                .toList();
    }

    @Override
    public TabletDetails listItemEntityToTransfer(TabletEntity srcEntity){
        TabletDetails response = new TabletDetails();
        response.setDateAdded(DATE_FORMAT.format(srcEntity.getDateAdded()));
        response.setTabletSource(srcEntity.getTabletSource());
        response.setInterpreted(srcEntity.getInterpreted());
        response.setDescription(srcEntity.getDescription());
        response.setId(srcEntity.getId());
        response.setLocation(srcEntity.getLocation());
        response.setImageId(srcEntity.getImageId());
        response.setTitle(srcEntity.getTitle());
        response.setTranslation(srcEntity.getTranslation());
        response.setSourceLink(srcEntity.getSourceLink());
        response.setDatedAt(srcEntity.getDatedAt());
        return response;
    }
}
