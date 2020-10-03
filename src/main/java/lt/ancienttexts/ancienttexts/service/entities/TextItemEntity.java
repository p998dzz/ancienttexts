package lt.ancienttexts.ancienttexts.service.entities;

import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getOriginalPictureId() {
        return originalPictureId;
    }

    public void setOriginalPictureId(Long originalPictureId) {
        this.originalPictureId = originalPictureId;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTabletSource() {
        return tabletSource;
    }

    public void setTabletSource(String tabletSource) {
        this.tabletSource = tabletSource;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isInterpreted() {
        return interpreted;
    }

    public void setInterpreted(Boolean interpreted) {
        this.interpreted = interpreted;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getDatedAt() {
        return datedAt;
    }

    public void setDatedAt(String datedAt) {
        this.datedAt = datedAt;
    }
}
