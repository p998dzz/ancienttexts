package lt.ancienttexts.service.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ListItemEntity {
    private Long id;
    private String title;
    private String location;
    private Boolean interpreted;
    private Date dateAdded;
}
