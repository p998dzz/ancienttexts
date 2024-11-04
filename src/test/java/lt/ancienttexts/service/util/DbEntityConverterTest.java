package lt.ancienttexts.service.util;

import lt.ancienttexts.domain.ListItem;
import lt.ancienttexts.domain.TabletDetails;
import lt.ancienttexts.service.entities.ListItemEntity;
import lt.ancienttexts.service.entities.TabletEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DbEntityConverterTest {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @InjectMocks
    private DbEntityConverter converter;

    @Test
    void listItemEntityToTransfer_shouldConvertListOfEntitiesToTransferObjects() {
        ListItemEntity entity1 = new ListItemEntity();
        entity1.setId(1L);
        entity1.setInterpreted(true);
        entity1.setLocation("location 1");
        entity1.setTitle("title 1");
        entity1.setDateAdded(new Date());
        ListItemEntity entity2 = new ListItemEntity();
        entity2.setId(2L);
        entity2.setInterpreted(false);
        entity2.setLocation("location 2");
        entity2.setTitle("title 2");
        entity2.setDateAdded(new Date());
        List<ListItemEntity> entities = List.of(entity1, entity2);

        List<ListItem> items = converter.listItemEntityToTransfer(entities);

        assertThat(items).hasSize(2);
        ListItem item1 = items.get(0);
        assertThat(item1.getId()).isEqualTo(entity1.getId());
        assertThat(item1.getInterpreted()).isEqualTo(entity1.getInterpreted());
        assertThat(item1.getLocation()).isEqualTo(entity1.getLocation());
        assertThat(item1.getTitle()).isEqualTo(entity1.getTitle());
        assertThat(item1.getDateAdded()).isEqualTo(dateFormat.format(entity1.getDateAdded()));
        ListItem item2 = items.get(1);
        assertThat(item2.getId()).isEqualTo(entity2.getId());
        assertThat(item2.getInterpreted()).isEqualTo(entity2.getInterpreted());
        assertThat(item2.getLocation()).isEqualTo(entity2.getLocation());
        assertThat(item2.getTitle()).isEqualTo(entity2.getTitle());
        assertThat(item2.getDateAdded()).isEqualTo(dateFormat.format(entity2.getDateAdded()));
    }

    @Test
    void listItemEntityToTransfer_shouldHandleEmptyEntityList() {
        List<ListItemEntity> entities = new ArrayList<>();

        List<ListItem> items = converter.listItemEntityToTransfer(entities);

        assertThat(items).isEmpty();
    }

    @Test
    void listItemEntityToTransfer_shouldConvertSingleEntityToTabletDetailsResponse() {
        TabletEntity srcEntity = new TabletEntity();
        srcEntity.setId(1L);
        srcEntity.setTabletSource("tablet source");
        srcEntity.setInterpreted(true);
        srcEntity.setDescription("description");
        srcEntity.setLocation("location");
        srcEntity.setTitle("title");
        srcEntity.setTranslation("translation");
        srcEntity.setSourceLink("source link");
        srcEntity.setImageId(153542423L);
        srcEntity.setDateAdded(new Date());
        srcEntity.setDatedAt("2024-10-03");

        TabletDetails response = converter.listItemEntityToTransfer(srcEntity);

        assertThat(response.getId()).isEqualTo(srcEntity.getId());
        assertThat(response.getTabletSource()).isEqualTo(srcEntity.getTabletSource());
        assertThat(response.getInterpreted()).isEqualTo(srcEntity.getInterpreted());
        assertThat(response.getDescription()).isEqualTo(srcEntity.getDescription());
        assertThat(response.getLocation()).isEqualTo(srcEntity.getLocation());
        assertThat(response.getTitle()).isEqualTo(srcEntity.getTitle());
        assertThat(response.getTranslation()).isEqualTo(srcEntity.getTranslation());
        assertThat(response.getSourceLink()).isEqualTo(srcEntity.getSourceLink());
        assertThat(response.getImageId()).isEqualTo(srcEntity.getImageId());
        assertThat(response.getDateAdded()).isEqualTo(dateFormat.format(srcEntity.getDateAdded()));
        assertThat(response.getDatedAt()).isEqualTo(srcEntity.getDatedAt());
    }
}
