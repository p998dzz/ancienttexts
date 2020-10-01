package lt.ancienttexts.ancienttexts.service;

import lt.ancienttexts.ancienttexts.controller.transfer.ListItem;
import lt.ancienttexts.ancienttexts.controller.transfer.TextDetailsResponse;
import lt.ancienttexts.ancienttexts.controller.transfer.TextListResponse;
import lt.ancienttexts.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.ancienttexts.service.entities.TextItemEntity;
import lt.ancienttexts.ancienttexts.service.translator.TextsDatabaseTranslator;
import lt.ancienttexts.ancienttexts.service.util.TypeConvertors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("LinearBservice")
public class LinearBservice {

    @Autowired
    private TextsDatabaseTranslator textsDatabaseTranslator;

    public TextListResponse fetchAllEntries() throws Exception {
        List<ListItemEntity> entriesEntities = textsDatabaseTranslator.fetchAllEntries();
        List<ListItem> translatedItems = TypeConvertors.listItemEntityToTransfer(entriesEntities);
        TextListResponse response = new TextListResponse();
        response.setNumberOfEntries(translatedItems.size());
        response.getEntries().addAll(translatedItems);
        return response;
    }

    public TextDetailsResponse fetchTextDetails(Long id) throws Exception {
        TextItemEntity entry = textsDatabaseTranslator.fetchTextDetails(id);
        TextDetailsResponse response = TypeConvertors.listItemEntityToTransfer(entry);
        return response;
    }


    private List<ListItem> generateFakeData(){
        List<ListItem> entries = new ArrayList<>();
        for(int i = 10; i < 99; i++) {
            ListItem entry = new ListItem();
            entry.setId(1L);
            entry.setTitle("MC"+i);
            entry.setLocation("Pylos" + i);
            entry.setInterpreted(Boolean.TRUE);
            entry.setDateAdded("2020-09-" + i);
            entries.add(entry);
        }
        return entries;
    }
}
