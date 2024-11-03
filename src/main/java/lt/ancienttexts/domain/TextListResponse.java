package lt.ancienttexts.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class TextListResponse {
    private List<ListItem> entries;
    private Integer numberOfEntries;

    public TextListResponse(List<ListItem> entries) {
        this.entries = entries;
        this.numberOfEntries = entries.size();
    }
}
