package lt.ancienttexts.ancienttexts.controller.transfer;

import java.util.ArrayList;
import java.util.List;

public class TextListResponse {
    private List<ListItem> entries = new ArrayList<>();
    private Integer numberOfEntries;

    public List<ListItem> getEntries() {
        return entries;
    }

    public void setEntries(List<ListItem> entries) {
        this.entries = entries;
    }

    public Integer getNumberOfEntries() {
        return numberOfEntries;
    }

    public void setNumberOfEntries(Integer numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }

}
