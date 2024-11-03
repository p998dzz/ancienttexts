package lt.ancienttexts.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextDetailsResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Long originalPictureId;
    private String translation;
    private String tabletSource;
    private Boolean interpreted;
    private String dateAdded;
    private String sourceLink;
    private String datedAt;
}
