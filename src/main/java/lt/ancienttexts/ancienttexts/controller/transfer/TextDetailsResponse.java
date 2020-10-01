package lt.ancienttexts.ancienttexts.controller.transfer;

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

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
