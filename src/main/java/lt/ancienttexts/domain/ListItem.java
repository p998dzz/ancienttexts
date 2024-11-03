package lt.ancienttexts.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListItem {
    private Long id;
    private String title;
    private String location;
    private Boolean interpreted;
    private String dateAdded;
}
