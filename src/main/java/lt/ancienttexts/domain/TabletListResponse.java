package lt.ancienttexts.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class TabletListResponse {
    private List<ListItem> entries;
    private Integer numberOfEntries;

    public TabletListResponse(List<ListItem> entries) {
        this.entries = entries;
        this.numberOfEntries = entries.size();
    }
}
