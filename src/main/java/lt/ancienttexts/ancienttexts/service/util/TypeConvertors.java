package lt.ancienttexts.ancienttexts.service.util;

import lt.ancienttexts.ancienttexts.controller.transfer.ListItem;
import lt.ancienttexts.ancienttexts.controller.transfer.TextDetailsResponse;
import lt.ancienttexts.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.ancienttexts.service.entities.TextItemEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TypeConvertors {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    public static List<ListItem> listItemEntityToTransfer(List<ListItemEntity> entitiesToConvert){
        List<ListItem> items = new ArrayList<>();
        for(ListItemEntity srcEntity : entitiesToConvert){
            ListItem item = new ListItem();
            item.setId(srcEntity.getId());
            item.setInterpreted(srcEntity.isInterpreted());
            item.setLocation(srcEntity.getLocation());
            item.setTitle(srcEntity.getTitle());
            String strDate = DATE_FORMAT.format(srcEntity.getDateAdded());
            item.setDateAdded(strDate);
            items.add(item);
        }
        return  items;
    }

    public static TextDetailsResponse listItemEntityToTransfer(TextItemEntity srcEntity){
        TextDetailsResponse response = new TextDetailsResponse();
        response.setDateAdded(DATE_FORMAT.format(srcEntity.getDateAdded()));
        response.setTabletSource(srcEntity.getTabletSource());
        response.setInterpreted(srcEntity.isInterpreted());
        response.setDescription(srcEntity.getDescription());
        response.setId(srcEntity.getId());
        response.setLocation(srcEntity.getLocation());
        response.setOriginalPictureId(srcEntity.getOriginalPictureId());
        response.setTitle(srcEntity.getTitle());
        response.setTranslation(srcEntity.getTranslation());
        response.setSourceLink(srcEntity.getSourceLink());
        return response;
    }
}
