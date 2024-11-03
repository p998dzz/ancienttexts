package lt.ancienttexts.service.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TextItemEntity {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Long originalPictureId;
    private String translation;
    private String tabletSource;
    private Boolean interpreted;
    private Date dateAdded;
    private String datedAt;
    private String sourceLink;
}
