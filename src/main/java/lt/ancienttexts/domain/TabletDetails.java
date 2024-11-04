package lt.ancienttexts.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TabletDetails {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Long imageId;
    private String translation;
    private String tabletSource;
    private Boolean interpreted;
    private String dateAdded;
    private String sourceLink;
    private String datedAt;

    public static TabletDetails from(Long id, TabletRequest tabletRequest, Long imageId) {
        var tabletDetails = new TabletDetails();
        tabletDetails.setId(id);
        tabletDetails.setTitle(tabletRequest.title());
        tabletDetails.setDescription(tabletRequest.description());
        tabletDetails.setLocation(tabletRequest.location());
        tabletDetails.setImageId(imageId);
        tabletDetails.setTranslation(tabletRequest.translation());
        tabletDetails.setTabletSource(tabletRequest.tabletSource());
        tabletDetails.setInterpreted(tabletRequest.interpreted());
        tabletDetails.setDateAdded(tabletRequest.dateAdded());
        tabletDetails.setSourceLink(tabletRequest.sourceLink());
        tabletDetails.setDatedAt(tabletRequest.datedAt());
        return tabletDetails;
    }
}
