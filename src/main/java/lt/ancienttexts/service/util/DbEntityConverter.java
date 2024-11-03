package lt.ancienttexts.service.util;

import lt.ancienttexts.domain.ListItem;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TextItemEntity;
import lt.ancienttexts.domain.TabletDetailsResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DbEntityConverter implements EntityConverter {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<ListItem> listItemEntityToTransfer(List<ListItemEntity> entitiesToConvert){
        List<ListItem> items = new ArrayList<>();
        for(ListItemEntity srcEntity : entitiesToConvert){
            ListItem item = new ListItem();
            item.setId(srcEntity.getId());
            item.setInterpreted(srcEntity.getInterpreted());
            item.setLocation(srcEntity.getLocation());
            item.setTitle(srcEntity.getTitle());
            String strDate = DATE_FORMAT.format(srcEntity.getDateAdded());
            item.setDateAdded(strDate);
            items.add(item);
        }
        return  items;
    }

    @Override
    public TabletDetailsResponse listItemEntityToTransfer(TextItemEntity srcEntity){
        TabletDetailsResponse response = new TabletDetailsResponse();
        response.setDateAdded(DATE_FORMAT.format(srcEntity.getDateAdded()));
        response.setTabletSource(srcEntity.getTabletSource());
        response.setInterpreted(srcEntity.getInterpreted());
        response.setDescription(srcEntity.getDescription());
        response.setId(srcEntity.getId());
        response.setLocation(srcEntity.getLocation());
        response.setOriginalPictureId(srcEntity.getOriginalPictureId());
        response.setTitle(srcEntity.getTitle());
        response.setTranslation(srcEntity.getTranslation());
        response.setSourceLink(srcEntity.getSourceLink());
        response.setDatedAt(srcEntity.getDatedAt());
        return response;
    }
}
