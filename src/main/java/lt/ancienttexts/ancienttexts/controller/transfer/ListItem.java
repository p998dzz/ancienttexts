package lt.ancienttexts.ancienttexts.controller.transfer;

import java.util.Date;

public class ListItem {
    private Long id;
    private String title;
    private String location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
